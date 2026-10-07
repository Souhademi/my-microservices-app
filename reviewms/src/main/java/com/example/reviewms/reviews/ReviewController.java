package com.example.reviewms.reviews;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
	@Autowired
	public ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}
	
	
	@GetMapping
	public ResponseEntity<List<Review>> listReviews(@RequestParam Long companyId){
		return new ResponseEntity<>(reviewService.getAllReviews(companyId),HttpStatus.OK);
	}
	
	@GetMapping("/{reviewId}")
	public ResponseEntity<Review> reviewById(@PathVariable Long reviewId) {
		return new ResponseEntity<>(reviewService.getReviewById(reviewId),HttpStatus.OK);
	
	}
	
	@PostMapping
	public ResponseEntity<String> addNewReview(@RequestParam Long companyId,@RequestBody Review review) {
		boolean isReveiwSaved=reviewService.addReview(companyId,review);
		if(isReveiwSaved) {
		return new ResponseEntity<>("Adding review succesfuly",HttpStatus.OK);
		}else {		
			return new ResponseEntity<>("Review not saved",HttpStatus.NOT_FOUND);
			}
	}
	
	@PutMapping("/{reviewId}")
	public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
											   @RequestBody Review review) {
		boolean isReviewupdated = reviewService.updateReview(reviewId, review);
		if (isReviewupdated) {
			return new ResponseEntity<>("Sueccussful update",HttpStatus.OK);
		}return new ResponseEntity<>("Review not updated",HttpStatus.NOT_FOUND); 
	}
	
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
		boolean isReviewdeleted =reviewService.deleteReview(reviewId);
		if(isReviewdeleted) {
			return new ResponseEntity<>("Review delete succesfuly",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Review not found",HttpStatus.NOT_FOUND);
		}
		
	}

}
