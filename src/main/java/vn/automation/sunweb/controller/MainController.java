package vn.automation.sunweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.automation.sunweb.entity.Category;
import vn.automation.sunweb.entity.Post;
import vn.automation.sunweb.repository.CategoryRepository;
import vn.automation.sunweb.service.CategoryService;
import vn.automation.sunweb.service.PostService;
import vn.automation.sunweb.service.UserService;
import vn.automation.sunweb.storage.StorageService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Controller
public class MainController {
    private StorageService storageService;
    public MainController(StorageService storageService) {
        this.storageService = storageService;
    }
    @Autowired private CategoryService categoryService;
    @Autowired private PostService postService;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private UserService userService;
    @GetMapping("/")
    public String index() {
        return "client/index";
    }

    @GetMapping("/listPost")
    public String listPost() {
        return "client/listPost";
    }

    @GetMapping("/singlePost")
    public String singlePost(Model model,@RequestParam(value = "id",required = true) Integer id) {
        try {
            Post post =postService.findById(id);
            Path html = storageService.load(post.getContext());
            BufferedReader bufferedReader = new BufferedReader(new FileReader(html.toFile()));
            String context = bufferedReader.lines().collect(Collectors.joining());
            model.addAttribute("post",postService.findById(id));
            model.addAttribute("context",context);
            model.addAttribute("usercreated",userService.findById(post.getUsercreated()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "client/singlePost";
    }

    @GetMapping("/test")
    public String listUser(Model model, @RequestParam(value = "limit",required = false) Integer limit) {
        model.addAttribute("listUser",categoryService.findByIdparent(null));
        categoryService.findByIdparent(null).forEach(value->{
            model.addAttribute(value.getId(),categoryRepository.findByIdparent(value.getId()));
        });
        return "test";
    }
}
