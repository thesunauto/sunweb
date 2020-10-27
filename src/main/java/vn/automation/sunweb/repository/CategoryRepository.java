package vn.automation.sunweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.automation.sunweb.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String>, JpaSpecificationExecutor<Category> {
    List<Category> findByCategory(Category category);
}