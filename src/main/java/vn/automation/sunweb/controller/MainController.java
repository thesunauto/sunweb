package vn.automation.sunweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("index","hello");
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
}
