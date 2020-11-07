package vn.automation.sunweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.automation.sunweb.entity.Post;
import vn.automation.sunweb.repository.PostRepository;
import vn.automation.sunweb.validator.PostValidator;

import java.util.List;
import java.util.Optional;
@Service
public class PostService {
    @Autowired private PostRepository postRepository;
    @Autowired private PostValidator postValidator;

    public List<Post> findAll(Integer limit){
        return Optional.ofNullable(limit).map(value->postRepository.findAll(PageRequest.of(0,limit)).getContent()).orElseGet(()->postRepository.findAll());
    }

    public List<Post> findAll(Integer page, Integer limit){
        return postRepository.findAll(PageRequest.of(page,limit)).getContent();
    }



    public Post findById(Integer id){
        return Optional.ofNullable(id).map(value -> postRepository.findById(id).get()).orElseGet(()->postRepository.findById(1).get());
    }

    public Post addPost(Post post){
        if(postValidator.isValid(post)){
            return postRepository.save(post);
        }
        return null;
    }
    public Post saveFileContextPost(Integer idPost){
        Post post = postRepository.findById(idPost).get();
        post.setContext(idPost+".html");
        return  postRepository.save(post);
    }

    public Post deletePost(Integer id){
        Post post = findById(id);
        post.setIsdeleted(true);
        return postRepository.save(post);
    }
}
