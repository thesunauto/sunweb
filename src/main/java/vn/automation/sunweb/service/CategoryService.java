package vn.automation.sunweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.automation.sunweb.commons.CategoryResponse;
import vn.automation.sunweb.commons.UserResponse;
import vn.automation.sunweb.entity.Category;
import vn.automation.sunweb.entity.User;
import vn.automation.sunweb.repository.CategoryRepository;
import vn.automation.sunweb.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private UserRepository userRepository;
    public List<Category> findAll(Integer limit){
        return Optional.ofNullable(limit).map(value -> categoryRepository.findAll(PageRequest.of(0,limit)).getContent()).orElseGet(()->categoryRepository.findAll());
    }
    public List<Category> findByIdparent(Category category){
        return Optional.ofNullable(category).map(value->categoryRepository.findByCategory(value)).orElseGet(()->categoryRepository.findByCategory(null));
    }
    public Collection<Category> findByIdparent(String category){
        return Optional.ofNullable(category).map(value->categoryRepository.getOne(value).getCategories()).orElseGet(()->categoryRepository.findByCategory(null));
    }
    public Category getOne(String id){
        return categoryRepository.findById(id).orElse(new Category());
    }

    public boolean save(CategoryResponse categoryResponse){
        try{
            Category category = new Category();
            category.setId(categoryResponse.getId());
            category.setTitle(categoryResponse.getTitle());
            category.setMetatitle(categoryResponse.getMetatitle());
            category.setDetail(categoryResponse.getDetail());
            category.setDatecreated(LocalDateTime.now());
            category.setCategory(categoryRepository.getOne(categoryResponse.getIdParent()));
            category.setIsdeleted(0);
            category.setUser(userRepository.findById("admin").get());
            categoryRepository.save(category);
            return true;
        }catch (Exception e){}
        return false;
    }

    public boolean delete(String id){
        try{categoryRepository.delete(getOne(id)); return true;}catch (Exception e){e.printStackTrace();}
        return false;
    }

    public boolean edit(CategoryResponse categoryResponse) {

        try {
            Category category = getOne(categoryResponse.getId());
            category.setTitle(categoryResponse.getTitle());
            category.setMetatitle(categoryResponse.getMetatitle());
            category.setDetail(categoryResponse.getDetail());
            categoryRepository.save(category);
            return true;
        }catch (Exception e){
            System.out.println("edit category");
            e.printStackTrace();
        }
        return false;
    }
}
