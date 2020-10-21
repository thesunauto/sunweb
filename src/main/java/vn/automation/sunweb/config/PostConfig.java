package vn.automation.sunweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.automation.sunweb.validator.PostValidator;

@Configuration
public class PostConfig {
    @Bean
    public PostValidator postValidator(){
        return new PostValidator();
    }
}
