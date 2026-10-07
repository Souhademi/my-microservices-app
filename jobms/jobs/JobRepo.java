package com.example.micro.jobs;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface JobRepo extends JpaRepository<Job,Long>{
	//void deleteById(Long id);
	//void findAll(Job job);
	//Optional<Job> findById(Long id);

}