package vn.automation.sunweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.automation.sunweb.commons.CategoryResponse;
import vn.automation.sunweb.commons.FileResponse;
import vn.automation.sunweb.commons.PostResponse;
import vn.automation.sunweb.commons.UserResponse;
import vn.automation.sunweb.entity.Category;
import vn.automation.sunweb.entity.Post;
import vn.automation.sunweb.entity.User;
import vn.automation.sunweb.repository.CategoryRepository;
import vn.automation.sunweb.service.CategoryService;
import vn.automation.sunweb.service.PostService;
import vn.automation.sunweb.service.UserService;
import vn.automation.sunweb.storage.StorageService;

import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class AdminRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;
    @Autowired
    private StorageService storageService;

    //    UserAPI
    @PostMapping("/getlistuser")
    public List<UserResponse> getListUser() {
        List<UserResponse> li = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        userService.select().forEach(value -> {
            li.add(new UserResponse(value.getUsername(), value.getPassword(), value.getName(), value.getDatecreated().format(formatter), value.getPhone(), value.getEmail(), value.getRole(), value.getIsdelete().intValue()));
        });
        return li;
    }

    @PostMapping("/saveuser")
    public ResponseEntity addUser(@RequestBody UserResponse userResponse) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        User user = new User();
        user.setName(userResponse.getName());
        user.setUsername(userResponse.getUsername());
        user.setPassword(userResponse.getPassword());
        user.setEmail(userResponse.getEmail());
        user.setPhone(userResponse.getPhone());
        user.setRole(userResponse.getRole());
        user.setDatecreated(LocalDateTime.parse(userResponse.getDatecreated(), formatter));
        user.setIsdelete(userResponse.getStt());
        userService.save(user);
        return ResponseEntity.ok().body(user);
    }
//    UserAPI

//    CategoryAPI

    @PostMapping("/getlistCategory/{id}")
    public ResponseEntity getListCategory(@PathVariable(value = "id") String id) {
        List<CategoryResponse> responseList = new ArrayList<>();
        categoryService.findByIdparent(id).forEach(value -> {
            CategoryResponse response = new CategoryResponse();
            response.setId(value.getId());
            response.setTitle(value.getTitle());
            response.setImage(value.getImage());
            response.setDetail(value.getDetail());
            response.setIsShowIndex(value.getIsshowindex()?"on":"off");
            if (value.getCategory().getCategory() != null) {
                response.setHasParent(true);

                response.setIdParent(value.getCategory().getCategory().getId());


                response.setParentTitle(value.getCategory().getTitle());
            } else {
                response.setIdParent(null);
                response.setHasParent(false);
            }

            response.setIsMax(false);

            try {
                response.setIsMax(categoryService.getOne(value.getId()).getCategory().getCategory().getCategory().getCategory() == null);
            } catch (Exception e) {
            }


            responseList.add(response);
        });
        return ResponseEntity.ok().body(responseList);
    }


    @PostMapping("/addtopic/{id}")
    public ResponseEntity addTopic(@PathVariable String id, @RequestBody CategoryResponse categoryResponse) {
        if (categoryService.getOne(id).getPosts().isEmpty()) {
            categoryResponse.setIdParent(id);
            System.out.println(categoryResponse);
            return ResponseEntity.ok().body(categoryService.save(categoryResponse));
        } else {

        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/upload-file")
    @ResponseBody
    public FileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String name = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(name)
                .toUriString();

        return new FileResponse(name, uri, file.getContentType(), file.getSize());
    }

    @PostMapping("/deletetopic-{id}")
    public ResponseEntity deleteTopic(@PathVariable String id) {
        if(!id.startsWith(".")){
            return ResponseEntity.ok().body(categoryService.delete(id));
        }
        return ResponseEntity.ok().body(false);

    }

    @PostMapping("/gettopic-{id}")
    public ResponseEntity getTopic(@PathVariable String id) {
        Category category = categoryService.getOne(id);
        return ResponseEntity.ok().body(
                CategoryResponse.builder()
                        .id(category.getId())
                        .isShowIndex(category.getIsshowindex()?"on":"off")
                        .title(category.getTitle())
                        .image(category.getImage())
                        .detail(category.getDetail()).build()
        );
    }

    @PostMapping("/edittopic")
    public boolean editTopic(@RequestBody CategoryResponse categoryResponse) {
        return categoryService.edit(categoryResponse);
    }
//    CategoryAPI


//    PostAPI

    @PostMapping("/getlistPost-{page}-{limit}")
    public ResponseEntity getlistPost(@PathVariable(value = "page") Integer page, @PathVariable(value = "limit") Integer limit) {
        List<PostResponse> postResponses = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        postService.findAll(page, limit).forEach(post -> {
            Path html = storageService.load(post.getContext());
            BufferedReader bufferedReader = null;
            String context = "";
            try {
                bufferedReader = new BufferedReader(new FileReader(html.toFile()));
                context = bufferedReader.lines().collect(Collectors.joining());
            } catch (Exception e) {
                context = "";
            }
            postResponses.add(PostResponse.builder()
                    .idCategory(post.getCategory().getId())
                    .id(post.getId())
                    .title(post.getTitle())
                    .metatitle(post.getMetatitle())
                    .isshowindex(post.getIsshowindex())
                    .ispublic(post.getIspulic())
                    .dateCreated(post.getDatecreated().format(dateTimeFormatter))
                    .dateUpdated(post.getDateupdated().format(dateTimeFormatter))
                    .datepuliced(post.getDatepuliced().format(dateTimeFormatter))
                    .image(post.getImage())
                    .content(context)
                    .build());
        });

        return ResponseEntity.ok().body(postResponses);
    }

    @PostMapping("/getPageNum")
    public ResponseEntity getPageNum() {
        return ResponseEntity.ok().body(postService.findAll().size() / 10 + 1);
    }

    @PostMapping("/editPost")
    public ResponseEntity editPost(@RequestBody PostResponse postResponse) {
        Post post = postService.findById(postResponse.getId());
        post.setTitle(postResponse.getTitle());
        post.setMetatitle(postResponse.getMetatitle());
        post.setIspulic(postResponse.getIspublic());
        post.setIsshowindex(postResponse.getIsshowindex());
        post.setContext(postResponse.getContent());
        String ct = post.getContext();
        post.setContext("error");
        postService.addPost(post);
        try {
            String filename = storageService.create(post.getId() + ".html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            bw.write(ct);
            bw.close();
            postService.saveFileContextPost(post.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body("123");
    }

    @PostMapping("/deletePost-{id}")
    public ResponseEntity deletePost(@PathVariable Integer id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
        }
        return ResponseEntity.ok().body(false);
    }
//    PostAPI
}
