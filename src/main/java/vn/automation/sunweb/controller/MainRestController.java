package vn.automation.sunweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.automation.sunweb.commons.PostResponse;
import vn.automation.sunweb.service.CategoryService;
import vn.automation.sunweb.service.PostService;
import vn.automation.sunweb.service.UserService;
import vn.automation.sunweb.storage.StorageService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    public ResponseEntity getlistPost(@PathVariable(value = "page") Integer page, @PathVariable(value = "limit") Integer limit,@RequestParam(value = "idcategory") String idcategory) {
        List<PostResponse> postResponses = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        postService.findAll(page, limit,idcategory).forEach(post -> {
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
                    .user(post.getUser().getName())
                    .build());
        });

        return ResponseEntity.ok().body(postResponses);
    }

    @PostMapping("/getpagenumbycategory")
    public ResponseEntity getPageNum(@RequestParam(value = "idcategory") String idcategory) {
        return ResponseEntity.ok().body(postService.findAll(idcategory).size() / 10 + 1);
    }
}
