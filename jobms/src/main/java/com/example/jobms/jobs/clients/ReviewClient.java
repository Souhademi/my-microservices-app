package com.example.jobms.jobs.clients;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.jobms.jobs.external.Review;


@FeignClient(name="REVIEW-SERVICE")
public interface ReviewClient {
	@GetMapping("/reviews")
	List<Review> getReviews(@RequestParam("companyId") Long companyId);
}
