package vn.automation.sunweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.automation.sunweb.entity.Category;
import vn.automation.sunweb.repository.CategoryRepository;
import vn.automation.sunweb.service.CategoryService;
import vn.automation.sunweb.service.UserService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class MainController {
    @Autowired private UserService userService;
    @Autowired private CategoryService categoryService;
    @Autowired private CategoryRepository categoryRepository;
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("index", "hello");
        List<Category> li1 = new CopyOnWriteArrayList<>();

        return "client/index";
    }

    @GetMapping("/listPost")
    public String listPost() {
        return "client/listPost";
    }

    @GetMapping("/singlePost")
    public String singlePost() {
        return "client/singlePost";
    }

    @GetMapping("/listUser")
    public String listUser(Model model, @RequestParam(value = "limit",required = false) Integer limit) {
        model.addAttribute("listUser",userService.findAll(limit));
        return "listUser";
    }
}
