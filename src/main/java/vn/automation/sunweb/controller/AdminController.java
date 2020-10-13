package vn.automation.sunweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.automation.sunweb.entity.Post;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping()
    public String index(Model model){
        model.addAttribute("pageN",0);
        model.addAttribute("post",new Post());
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
