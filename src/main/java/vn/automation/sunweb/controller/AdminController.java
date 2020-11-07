package vn.automation.sunweb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.automation.sunweb.entity.Category;
import vn.automation.sunweb.entity.Post;
import vn.automation.sunweb.entity.User;
import vn.automation.sunweb.repository.CategoryRepository;
import vn.automation.sunweb.repository.UserRepository;
import vn.automation.sunweb.service.CategoryService;
import vn.automation.sunweb.service.PostService;
import vn.automation.sunweb.service.UserService;
import vn.automation.sunweb.storage.StorageService;

import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
    @Autowired private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
@Autowired
private UserService userService;
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("postOB", new Post());
        return "admin/index";
    }


    @PostMapping()
    public String addPost(Model model, @ModelAttribute(value = "postOB") Post post,@RequestParam(value = "category") String categoryId) {
        post.setDatecreated(LocalDateTime.now());
        post.setDatepuliced(LocalDateTime.now());
        post.setDateupdated(LocalDateTime.now());
        post.setIsdeleted(false);
        post.setCategory(categoryRepository.getOne(categoryId));
        post.setUser(userRepository.getOne("admin"));
        String ct = post.getContext();
        post.setContext("error");

        model.addAttribute("message", Optional.ofNullable(postService.addPost(post)).map(t -> "thành công").orElse("thất bại"));
        try {
            String filename = storageService.create(post.getId() + ".html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            bw.write(ct);
            bw.close();
            postService.saveFileContextPost(post.getId());
        } catch (Exception e) {
            model.addAttribute("message","toastr.danger('Thêm bài viết thất bại', '', {positionClass: 'md-toast-top-right'});$('#toast-container').attr('class','md-toast-top-right');");
            e.printStackTrace();
        }
        model.addAttribute("message","toastr.success('Thêm bài viết thành công', '', {positionClass: 'md-toast-top-right'});$('#toast-container').attr('class','md-toast-top-right');");
        model.addAttribute("postOB",new Post());
        return "admin/index";
    }

    @PostMapping("/getCategory")
    public @ResponseBody
    Map<String, String> getCategory(@RequestParam("id") String id) {
        Map<String, String> categoryValues = new HashMap<>();
        List<Category> categories = categoryRepository.findByCategoryAndIsdeleted(categoryRepository.findById(id).get(),false);
        for (Category category : categories) {
            categoryValues.put(category.getId(), category.getTitle());
        }
        return categoryValues;
    }


    @GetMapping("/selectCategory")
    public String selectCategory(Model model, @RequestParam(value = "id", required = true) String id) {
        return "admin/index";
    }

    @GetMapping("/manageUser")
    public String user(Model model) {
        return "admin/userManage";
    }

    @GetMapping("/manageTopic")
    public String topic(Model model) {
        return "admin/topicManage";
    }

    @GetMapping("/managePost")
    public String post(Model model) {
        return "admin/postManage";
    }

}
