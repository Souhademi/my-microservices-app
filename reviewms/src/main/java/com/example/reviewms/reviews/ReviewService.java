package com.example.reviewms.reviews;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReviewService {
	
	
	@Autowired
	public final  ReviewRepo reviewRepo;
	
	public ReviewService(ReviewRepo reviewRepo) {
		this.reviewRepo = reviewRepo;	
	}

	
	public List<Review> getAllReviews(Long reiewId) {
		List<Review> reviews=reviewRepo.findByCompanyId(reiewId);
		return reviews;
	}

	public Review getReviewById(final Long reviewId) {
		return reviewRepo.findById(reviewId).orElse(null);
	}

	public boolean addReview(Long companyId,Review review) {
		if(companyId != null && review != null) {
			review.setCompanyId(companyId);
			reviewRepo.save(review);
			return true;
		}return false;
	}
	
	public boolean updateReview(Long reviewId,Review updatedReview) {
		Review review = reviewRepo.findById(reviewId).orElseThrow(null);
		if(review != null) {
			review.setDescription(updatedReview.getDescription());
			review.setTitle(updatedReview.getTitle());
			review.setRating(updatedReview.getRating());
			review.setCompanyId(updatedReview.getCompanyId());
			reviewRepo.save(review);
			return true;
		}return false;
	}


	public boolean deleteReview(Long reviewId) {
		Review review = reviewRepo.findById(reviewId).orElse(null);
		if(reviewId != null) {
		reviewRepo.deleteById(reviewId);
		return true;
		}
		return false;
	}
}
