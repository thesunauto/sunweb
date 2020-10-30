package vn.automation.sunweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.automation.sunweb.commons.CategoryResponse;
import vn.automation.sunweb.commons.UserResponse;
import vn.automation.sunweb.entity.Category;
import vn.automation.sunweb.entity.User;
import vn.automation.sunweb.repository.CategoryRepository;
import vn.automation.sunweb.service.CategoryService;
import vn.automation.sunweb.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class AdminRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    //    UserAPI
    @PostMapping("/getlistuser")
    public List<UserResponse> getListUser() {
        List<UserResponse> li = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        userService.select().forEach(value -> {
            li.add(new UserResponse(value.getUsername(), value.getPassword(), value.getName(), value.getDatecreated().format(formatter), value.getPhone(), value.getEmail(), value.getRole(), value.getIsdelete().intValue()));
        });
        return li;
    }

    @PostMapping("/saveuser")
    public ResponseEntity addUser(@RequestBody UserResponse userResponse) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        User user = new User();
        user.setName(userResponse.getName());
        user.setUsername(userResponse.getUsername());
        user.setPassword(userResponse.getPassword());
        user.setEmail(userResponse.getEmail());
        user.setPhone(userResponse.getPhone());
        user.setRole(userResponse.getRole());
        user.setDatecreated(LocalDateTime.parse(userResponse.getDatecreated(), formatter));
        user.setIsdelete(userResponse.getStt());
        userService.save(user);
        return ResponseEntity.ok().body(user);
    }
//    UserAPI

//    CategoryAPI

    @PostMapping("/getlistCategory/{id}")
    public ResponseEntity getListCategory(@PathVariable(value = "id") String id) {
        List<CategoryResponse> responseList = new ArrayList<>();
        categoryService.findByIdparent(id).forEach(value -> {
            CategoryResponse response = new CategoryResponse();
            response.setId(value.getId());
            response.setTitle(value.getTitle());
            response.setMetatitle(value.getMetatitle());
            response.setDetail(value.getDetail());
            if(value.getCategory().getCategory()!=null){
                response.setHasParent(true);

                    response.setIdParent(value.getCategory().getCategory().getId());


                response.setParentTitle(value.getCategory().getTitle());
            }else {response.setIdParent(null);
                response.setHasParent(false);
            }
            response.setHasChild(!value.getCategories().isEmpty());
            responseList.add(response);
        });
        return ResponseEntity.ok().body(responseList);
    }

//    CategoryAPI
}
