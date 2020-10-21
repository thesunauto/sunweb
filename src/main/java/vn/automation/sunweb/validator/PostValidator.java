package vn.automation.sunweb.validator;

import org.thymeleaf.util.StringUtils;
import vn.automation.sunweb.entity.Post;

import java.util.Optional;

public class PostValidator {
    public  boolean isValid(Post post){
        return Optional.ofNullable(post).filter(post1 -> !StringUtils.isEmpty(post.getTitle())).filter(post1 -> !StringUtils.isEmpty(post1.getContext())).isPresent();
    }
}
