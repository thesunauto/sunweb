package vn.automation.sunweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.automation.sunweb.entity.Category;
import vn.automation.sunweb.entity.User;
import vn.automation.sunweb.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {
    @Autowired private CategoryRepository categoryRepository;

    public List<Category> findAll(Integer limit){
        return Optional.ofNullable(limit).map(value -> categoryRepository.findAll(PageRequest.of(0,limit)).getContent()).orElseGet(()->categoryRepository.findAll());
    }
    public List<Category> findByIdparent(String idparent){
        return Optional.ofNullable(idparent).map(value->categoryRepository.findByIdparent(value)).orElseGet(()->categoryRepository.findByIdparent(null));

    }
}
