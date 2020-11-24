package vn.automation.sunweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.automation.sunweb.commons.CategoryResponse;
import vn.automation.sunweb.commons.PostResponse;
import vn.automation.sunweb.entity.Category;
import vn.automation.sunweb.entity.Post;
import vn.automation.sunweb.service.CategoryService;
import vn.automation.sunweb.service.PostService;
import vn.automation.sunweb.service.UserService;
import vn.automation.sunweb.storage.StorageService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class MainRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;
    @Autowired
    private StorageService storageService;

    @PostMapping("/getlistpostbycategory-{page}-{limit}")
    public ResponseEntity getlistPost(@PathVariable(value = "page") Integer page, @PathVariable(value = "limit") Integer limit, @RequestParam(value = "idcategory") String idcategory) {
        List<PostResponse> postResponses = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        postService.findAll(page, limit, idcategory).forEach(post -> {
            Path html = storageService.load(post.getContext());
            BufferedReader bufferedReader = null;
            String context = "";
            try {
                bufferedReader = new BufferedReader(new FileReader(html.toFile()));
                context = bufferedReader.lines().collect(Collectors.joining());
            } catch (Exception e) {
                context = "";
            }
            if(post.getIspulic()){
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
                    .user(post.getUser().getName())
                    .build());}
        });

        return ResponseEntity.ok().body(postResponses);
    }

    @PostMapping("/getpagenumbycategory")
    public ResponseEntity getPageNum(@RequestParam(value = "idcategory") String idcategory) {
        return ResponseEntity.ok().body(postService.findAll(idcategory).size() / 10 + 1);
    }

    @PostMapping("/getTop3PostNew")
    public ResponseEntity getTop3PostNew() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<PostResponse> postResponses = new ArrayList<>();
        postService.getTop3new().forEach(post -> {
            postResponses.add(PostResponse.builder()
                    .id(post.getId())
                    .idCategory(post.getCategory().getId())
                    .title(post.getTitle())
                    .metatitle(post.getMetatitle())
                    .isshowindex(post.getIsshowindex())
                    .ispublic(post.getIsshowindex())
                    .dateCreated(post.getDatecreated().format(dateTimeFormatter))
                    .dateUpdated(post.getDateupdated().format(dateTimeFormatter))
                    .datepuliced(post.getDatepuliced().format(dateTimeFormatter))
                    .image(post.getImage())
                    .user(post.getUser().getName())
                    .build());
        });
        return ResponseEntity.ok().body(postResponses);
    }

    @PostMapping("/getTreeCategory")
    public ResponseEntity getTreeCategory(@RequestParam(value = "idcategory") String idcategory) {
        List<String> caTr = new ArrayList<>();
        Category category = categoryService.getOne(idcategory);
        caTr.add(category.getTitle());
        if (category.getCategory() == null) {
            Collections.reverse(caTr);
            return ResponseEntity.ok().body(caTr);
        }else{
            caTr.add(category.getCategory().getTitle());
            if(category.getCategory().getCategory()==null){   Collections.reverse(caTr);
                return ResponseEntity.ok().body(caTr);
            }else{
                caTr.add(category.getCategory().getCategory().getTitle());
                if(category.getCategory().getCategory().getCategory()==null){   Collections.reverse(caTr);
                    return ResponseEntity.ok().body(caTr);
                }else{
                    caTr.add(category.getCategory().getCategory().getCategory().getTitle());
                    if(category.getCategory().getCategory().getCategory().getCategory()==null){   Collections.reverse(caTr);
                        return ResponseEntity.ok().body(caTr);
                    }else{
                        caTr.add(category.getCategory().getCategory().getCategory().getCategory().getTitle());
                        if(category.getCategory().getCategory().getCategory().getCategory().getCategory()==null){   Collections.reverse(caTr);
                            return ResponseEntity.ok().body(caTr);
                        }else{
                            return ResponseEntity.ok().body(null);
                        }
                    }
                }
            }
        }

    }

    @PostMapping("/getPost/{id}")
    public ResponseEntity getPost(@PathVariable Integer id){
        Post post = postService.findById(id);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Path html = storageService.load(post.getContext());
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(html.toFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String context = bufferedReader.lines().collect(Collectors.joining());
        return ResponseEntity.ok().body(PostResponse.builder()
                .user(post.getUser().getName())
                .image(post.getImage())
                .datepuliced(post.getDatepuliced().format(dateTimeFormatter))
                .dateUpdated(post.getDateupdated().format(dateTimeFormatter))
                .dateCreated(post.getDatecreated().format(dateTimeFormatter))
                .content(context)
                .ispublic(post.getIspulic())
                .isshowindex(post.getIsshowindex())
                .metatitle(post.getMetatitle())
                .title(post.getTitle())
                .idCategory(post.getCategory().getTitle())
                .id(post.getId())
                .build());
    }

    @PostMapping("/getCategoryShowingIndex")
    public ResponseEntity getCategoryShowingIndex(){
        List<CategoryResponse> responseList = new ArrayList<>();
        categoryService.findAll(null).forEach(category -> {
            if(category.getIsdeleted()==false){
                if(category.getId().startsWith(".")){
                    
                }
            }
        });
        return ResponseEntity.ok().body(null);
    }
}
