package vn.automation.sunweb.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.automation.sunweb.entity.Category;
import vn.automation.sunweb.entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post> {
    List<Post> findAllByIsdeleted(Sort var1,Boolean isdeleted);
    List<Post> findAllByIsdeleted(Boolean isdeleted);
    List<Post> findAllByCategoryAndIsdeleted(Category category,Boolean isdeleted);
    Page<Post> findAllByIsdeleted(Pageable var1,Boolean isdeleted);

    Page<Post> findAllByIsdeletedAndCategory(Pageable var1, Boolean isdeleted, Category category);
    List<Post> findTop3ByOrderByDatecreatedDesc();
    List<Post> findTop3ByIsshowindexAndIspulicAndIsdeletedOrderByDatecreatedDesc(Boolean isshowindex,Boolean ispulic,Boolean isdeleted);
}