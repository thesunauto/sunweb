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

import java.time.LocalDateTime;

@SpringBootTest
class SunwebApplicationTests {
    @Autowired private PostRepository postRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private CategoryService categoryService;
    @Test
    void contextLoads() {
        System.out.println(categoryService.getOne("tech").getCategory().getCategory().getCategory().getCategory()==null);
    }

}
