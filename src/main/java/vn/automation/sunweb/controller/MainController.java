package vn.automation.sunweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.automation.sunweb.entity.Category;
import vn.automation.sunweb.entity.Post;
import vn.automation.sunweb.repository.CategoryRepository;
import vn.automation.sunweb.repository.PostRepository;
import vn.automation.sunweb.service.CategoryService;
import vn.automation.sunweb.service.PostService;
import vn.automation.sunweb.service.UserService;
import vn.automation.sunweb.storage.StorageService;

import javax.servlet.http.HttpServletRequest;
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
   @Autowired private PostRepository postRepository;


    @GetMapping("/")
    public String index() {
        return "client/index";
    }

    @GetMapping("/view-wherecategory={idcategory}")
    public String viewCategory(@PathVariable String idcategory, HttpServletRequest request) {
        Category category = categoryService.getOne(idcategory);
        if(postService.findAll(category.getId()).size()==1){
            return "redirect:/singlePost?id="+category.getPosts().iterator().next().getId();
        }
        if(postService.findAll(category.getId()).size()==0){
            return "client/noPost";
        }
        return "redirect:/listPost?idcategory="+category.getId();
    }

    @GetMapping("/listPost")
    public String listPost(Model model,@RequestParam(value = "idcategory",required = true) String idcategory){
        model.addAttribute("category",categoryService.getOne(idcategory));
        return "client/listPost";
    }

    @GetMapping("/singlePost")
    public String singlePost(Model model,@RequestParam(value = "id",required = true) Integer id) {
        try {
            Post post =postService.findById(id);
            Path html = storageService.load(post.getContext());
            BufferedReader bufferedReader = new BufferedReader(new FileReader(html.toFile()));
            String context = bufferedReader.lines().collect(Collectors.joining());
            model.addAttribute("context",context);model.addAttribute("post",post);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "client/singlePost";
    }

    @GetMapping("/test")
    public String listUser(Model model) {
        model.addAttribute("post",new Post());
        return "test";
    }
    @PostMapping("/test")
    public String listUser(Model model, @ModelAttribute(value = "post") Post post) {
        postRepository.save(post);
        return "test";
    }
}
