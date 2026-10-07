package com.example.reviewms.reviews;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface ReviewRepo extends JpaRepository<Review,Long>{
	List<Review> findByCompanyId(Long companyId);
	
	

}
