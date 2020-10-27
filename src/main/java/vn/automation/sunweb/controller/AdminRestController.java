package vn.automation.sunweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.automation.sunweb.entity.User;
import vn.automation.sunweb.service.UserService;

import java.util.List;
@RequestMapping("/api")
@RestController
public class AdminRestController {

    @Autowired private UserService userService;

    @PostMapping("/getlistuser")
    public List<User> getListUser(){
        return userService.select();
    }
}
