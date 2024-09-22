package com.harish.JobApp.review;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/companies/{companyId}/reviews")
@RestController
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
        boolean addSuccess = reviewService.addReview(companyId, review);
        if (addSuccess){
            return new ResponseEntity<>("Review for company with id: "+ review.getId() + " added successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("id doesn't match a company, can't add review",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(companyId, reviewId),HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId,@RequestBody Review review) {
        boolean updateSuccess = reviewService.updateReview(review, companyId, reviewId);
        if (updateSuccess) {
            return new ResponseEntity<>("Review updated successfully for company id: " + companyId, HttpStatus.OK);
        }
        return new ResponseEntity<>("Unable to update review, company id or review id doesn't match", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean deleteSuccess = reviewService.deleteReviewById(companyId, reviewId);

        if (deleteSuccess){
            return new ResponseEntity<>("Review successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("company or review id doesn't match", HttpStatus.NOT_FOUND);
    }



}