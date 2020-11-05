package vn.automation.sunweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.automation.sunweb.entity.Category;
import vn.automation.sunweb.entity.Post;
import vn.automation.sunweb.entity.User;
import vn.automation.sunweb.repository.CategoryRepository;
import vn.automation.sunweb.repository.PostRepository;
import vn.automation.sunweb.repository.UserRepository;
import vn.automation.sunweb.service.CategoryService;
import vn.automation.sunweb.service.PostService;

import java.time.LocalDateTime;

@SpringBootTest
class SunwebApplicationTests {
    @Autowired private PostRepository postRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private CategoryService categoryService;
    @Autowired private PostService postService;

    @Test
    void contextLoads() {
        System.out.println(postService.findAll(0,10));
        System.out.println(postService.findAll(1,10));
        System.out.println(postService.findAll(2,10));
    }

}
