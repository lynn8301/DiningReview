package com.diningReview.DiningReview.repository;
import org.springframework.data.repository.CrudRepository;
import com.diningReview.DiningReview.model.Review;
import com.diningReview.DiningReview.model.AdminReviewStatus;;

public interface ReviewRepository extends CrudRepository<Review, Long>{
    Iterable<Review> findByIdAndAdminReviewStatus(Long id, AdminReviewStatus status);
    Iterable<Review> findByAdminReviewStatus(AdminReviewStatus status);
}


