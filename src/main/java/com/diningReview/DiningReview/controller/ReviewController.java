package com.diningReview.DiningReview.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.diningReview.DiningReview.repository.RestaurantRepository;
import com.diningReview.DiningReview.repository.ReviewRepository;
import com.diningReview.DiningReview.repository.UserRepository;
import com.diningReview.DiningReview.model.AdminReviewStatus;
import com.diningReview.DiningReview.model.Review;

@RestController
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    @GetMapping("/dining-review")
    public Iterable<Review> getAllDiningReview(){
        return reviewRepository.findAll();
    }

    @GetMapping("/dining-review/pending")
    public Iterable<Review> getPendingReviews(){
        return reviewRepository.findByAdminReviewStatus(AdminReviewStatus.PENDING);
    }

    @GetMapping("/dining-review/accepted/{id}")
    public Iterable<Review> getAcceptedReviewByRestaurantId(@PathVariable("id") Long id ){
        return reviewRepository.findByIdAndAdminReviewStatus(id, AdminReviewStatus.APPROVED);
    }

    @PostMapping("/dining-review")
    public Review createNewDiningreview(@RequestBody Review review){
        if(restaurantRepository.findById(review.getRestauratnId()).isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This restaurant does not exist");
        }
        if(userRepository.findByUserName(review.getReviewBy()).isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user does not exist");
        }
        return reviewRepository.save(review);
    }

    @PutMapping("/dining-review/{id}/approve")
    public Review approveReview(@PathVariable("id") Long id){
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if(reviewOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This review does not exist");
        }
        Review reviewToApprove = reviewOptional.get();
        reviewToApprove.setAdminReviewStatus(AdminReviewStatus.APPROVED);
        return reviewRepository.save(reviewToApprove);
    }

    @PutMapping("/dining-review/{id}/reject")
    public Review rejectReview(@PathVariable("id") Long id){
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if(reviewOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This review does not exist");
        }
        Review reviewToReject = reviewOptional.get();
        reviewToReject.setAdminReviewStatus(AdminReviewStatus.REJECTED);
        return reviewRepository.save(reviewToReject);
    }
    
}


    
    