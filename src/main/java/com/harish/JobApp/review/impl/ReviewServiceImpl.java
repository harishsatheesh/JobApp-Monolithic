package com.harish.JobApp.review.impl;

import com.harish.JobApp.company.Company;
import com.harish.JobApp.company.CompanyService;
import com.harish.JobApp.job.JobRepository;
import com.harish.JobApp.job.JobService;
import com.harish.JobApp.review.Review;
import com.harish.JobApp.review.ReviewRepository;
import com.harish.JobApp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;
    private final JobRepository jobRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService, JobRepository jobRepository) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
        this.jobRepository = jobRepository;
    }


    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateReview(Review updatedReview, Long companyId, Long reviewId) {
        if (companyService.getCompanyById(companyId)!=null){
            updatedReview.setCompany(companyService.getCompanyById(companyId  ));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId) {
        if (companyService.getCompanyById(companyId)!=null && reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = companyService.getCompanyById(companyId);
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(company, companyId);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }
}
