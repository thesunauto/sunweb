package vn.automation.sunweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.automation.sunweb.entity.Category;
import vn.automation.sunweb.entity.Post;
import vn.automation.sunweb.repository.CategoryRepository;
import vn.automation.sunweb.repository.PostRepository;
import vn.automation.sunweb.validator.PostValidator;

import java.util.List;
import java.util.Optional;
@Service
public class PostService {
    @Autowired private PostRepository postRepository;
    @Autowired private PostValidator postValidator;
@Autowired private CategoryRepository categoryRepository;
    public List<Post> findAll(Integer limit){
        return Optional.ofNullable(limit).map(value->postRepository.findAll(PageRequest.of(0,limit)).getContent()).orElseGet(()->postRepository.findAll());
    }

    public List<Post> findAll(){
        return postRepository.findAllByIsdeleted(false);
    }

    public List<Post> findAll(String idcategory){
        return postRepository.findAllByCategoryAndIsdeleted(categoryRepository.getOne(idcategory),false);
    }

    public List<Post> findAll(Integer page, Integer limit){
        return postRepository.findAllByIsdeleted(PageRequest.of(page,limit),false).getContent();
    }

    public List<Post> findAll(Integer page, Integer limit, String idCategory){
        return postRepository.findAllByIsdeletedAndCategory(PageRequest.of(page,limit),false,categoryRepository.getOne(idCategory)).getContent();
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

    public List<Post> getTop3new(){
       return postRepository.findTop3ByOrderByDatecreatedDesc();
    }

    public List<Post> getTop3newIndex(){
        return postRepository.findTop3ByIsshowindexAndIspulicAndIsdeletedOrderByDatecreatedDesc(true,true,true);
    }
}
