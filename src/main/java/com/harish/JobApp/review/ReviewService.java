package com.harish.JobApp.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId, Review review);
    boolean updateReview(Review updatedReview, Long companyId, Long reviewId);
    boolean deleteReviewById(Long companyId, Long reviewId);
    Review getReview(Long companyId, Long reviewId);

}

