package com.example.micro.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.micro.companies.Company;



@RestController
public class JobController {

@Autowired
private JobServiceImpl jobService;

public JobController(JobServiceImpl jobService) {
	this.jobService = jobService;
	
}
	@GetMapping
	public List<Job> listCompany() {
		return jobService.findAllJobs();
	}
	
	@GetMapping("/{id}")
	ResponseEntity<Job> jobById(@PathVariable Long id) {

		Job job = jobService.getJobById(id);
		if(job != null) {
			return new ResponseEntity<>(job,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(job,HttpStatus.NOT_FOUND); 
		}
	}
	
	@PostMapping("/jobs")
	public String addJob(@RequestBody Job job) {
		jobService.createJob(job);
		return "seccesful addilng job";
	}
	
	@PutMapping("/{id}")
	 public void updateJob(@PathVariable("id")Long id,@RequestBody Job job){
		jobService.updateJob(id,job);
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
		boolean isdeleted =	jobService.deleteJob(id);
		if(isdeleted) {
			return new ResponseEntity<>("Job delete succesfuly",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Job not found",HttpStatus.NOT_FOUND);
		}
		
	}
	

}
