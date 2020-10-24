package vn.automation.sunweb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.automation.sunweb.entity.Category;
import vn.automation.sunweb.entity.Post;
import vn.automation.sunweb.service.CategoryService;
import vn.automation.sunweb.service.PostService;
import vn.automation.sunweb.storage.StorageService;

import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private StorageService storageService;

    public AdminController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping()
    public String index(Model model){
        Post post = new Post();
        model.addAttribute("category","chungtoi");
        model.addAttribute("post",post);
        return "admin/index";
    }

    @PostMapping("/getCategory")
    public @ResponseBody
    Map<String,String> getCategory(@RequestParam("id") String id){
        Map<String ,String > categoryValues = new HashMap<>();
        List<Category> categories = categoryService.findByIdparent(id);
        for(Category category:categories){
            categoryValues.put(category.getId(),category.getTitle());
        }
        return categoryValues;
    }

    @PostMapping()
    public String addPost(Model model, @ModelAttribute Post post){
        post.setDatecreated(LocalDateTime.now());
        post.setDatepuliced(LocalDateTime.now());
        post.setDateupdated(LocalDateTime.now());
        post.setIdcategory("tech");
        post.setIsdeleted(0);
        post.setIspulic(1);
        post.setIsshowindex(0);
        post.setUsercreated("admin");
        String ct = post.getContext();
        post.setContext("error");
        model.addAttribute("message", Optional.ofNullable(postService.addPost(post)).map(t->"thành công").orElse("thất bại"));
        try {
            String filename = storageService.create(post.getId()+".html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            bw.write(ct);
            bw.close();
            postService.saveFileContextPost(post.getId());
        }catch (Exception e){
            e.printStackTrace();
        }

        return "admin/index";
    }

    @GetMapping("/selectCategory")
    public String selectCategory(Model model,@RequestParam(value = "id",required = true) String id){
        return "admin/index";
    }

    @GetMapping("/manageUser")
    public String user(Model model){model.addAttribute("pageN",1);
        return "admin/userManage";
    }
    @GetMapping("/manageTopic")
    public String topic(Model model){model.addAttribute("pageN",2);
        return "admin/topicManage";
    }

}
