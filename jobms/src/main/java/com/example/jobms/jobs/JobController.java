package com.example.jobms.jobs;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.jobms.jobs.dto.JobDTO;


@RestController
@RequestMapping("/jobs")
public class JobController {

@Autowired
private JobServiceImpl jobService;

public JobController(JobServiceImpl jobService) {
	this.jobService = jobService;
	
}
	@GetMapping
	public ResponseEntity<Iterable<JobDTO>> listJob() {
		return ResponseEntity.ok(jobService.findAllJobs());
	}
	
	@GetMapping("/{id}")
	ResponseEntity<JobDTO> jobById(@PathVariable Long id) {

		JobDTO jobDTO = jobService.getJobById(id);
		if(jobDTO != null) {
			return new ResponseEntity<>(jobDTO,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(jobDTO,HttpStatus.NOT_FOUND); 
		}
	}
	
	@PostMapping
	public String addJob(@RequestBody Job job) {
		jobService.createJob(job);
		return "seccesful adding job";
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
