package vn.automation.sunweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.automation.sunweb.commons.UserResponse;
import vn.automation.sunweb.entity.User;
import vn.automation.sunweb.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@RequestMapping("/api")
@RestController
public class AdminRestController {

    @Autowired private UserService userService;

    @PostMapping("/getlistuser")
    public List<UserResponse> getListUser(){
        List<UserResponse> li = new ArrayList<>();
        userService.select().forEach(value -> {li.add(new UserResponse(value.getUsername(),value.getPassword(),value.getName(),value.getDatecreated().getDayOfMonth()+"/"+value.getDatecreated().getMonthValue()+"/"+value.getDatecreated().getYear(),value.getPhone(),value.getEmail(),value.getRole(),value.getIsdelete()));});
        return li;
    }

    @PostMapping("/save")
    public ResponseEntity  addUser(@RequestBody UserResponse userResponse){
        User user = new User();
        user.setName(userResponse.getName());
        user.setUsername(userResponse.getUsername());
        user.setPassword(userResponse.getPassword());
        user.setEmail(userResponse.getEmail());
        user.setPhone(userResponse.getPhone());
        user.setRole(userResponse.getRole());
        user.setDatecreated(LocalDateTime.now());
        user.setIsdelete(0);
        if(userService.save(user)){
        return ResponseEntity.ok().body("toastr.success('Thêm bài viết thành công', '', {positionClass: 'md-toast-top-right'});$('#toast-container').attr('class','md-toast-top-right');");
    }else{
            return ResponseEntity.ok().body("toastr.danger('Thêm bài viết thất bại', '', {positionClass: 'md-toast-top-right'});$('#toast-container').attr('class','md-toast-top-right');");

        }
    }
}
