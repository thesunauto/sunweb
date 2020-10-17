package vn.automation.sunweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.automation.sunweb.entity.Post;
import vn.automation.sunweb.repository.PostRepository;

import java.util.List;
import java.util.Optional;
@Service
public class PostService {
    @Autowired private PostRepository postRepository;

    public List<Post> findAll(Integer limit){
        return Optional.ofNullable(limit).map(value->postRepository.findAll(PageRequest.of(0,limit)).getContent()).orElseGet(()->postRepository.findAll());
    }
}
