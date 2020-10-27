package vn.automation.sunweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.automation.sunweb.entity.Category;
import vn.automation.sunweb.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SelectRestController {
    @Autowired
    private CategoryService categoryService;

}
