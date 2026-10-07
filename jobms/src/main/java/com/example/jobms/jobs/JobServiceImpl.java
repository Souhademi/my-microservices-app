package com.example.jobms.jobs;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.jobms.jobs.clients.CompanyClient;
import com.example.jobms.jobs.clients.ReviewClient;
import com.example.jobms.jobs.dto.JobDTO;
import com.example.jobms.jobs.external.Company;
import com.example.jobms.jobs.external.Review;
import com.example.jobms.mapper.JobMapper;


@Service
public class JobServiceImpl{
	
	private final JobRepo repo;
	
	@Autowired
	
	RestTemplate restTemplate;
	private CompanyClient companyClient;
	private ReviewClient reviewClient;
	
	
	 public JobServiceImpl(JobRepo repo, CompanyClient companyClient, ReviewClient reviewClient) {
		// super();
		this.repo = repo;
		this.companyClient = companyClient;
		this.reviewClient = reviewClient;
	}


	public JobDTO getJobById(Long id) {
		Job job = repo.findById(id).orElse(null);
		return convertToDto(job);
	}
	

	public Iterable<JobDTO> findAllJobs() {
		List<Job> jobs = repo.findAll();
		List<JobDTO> jobDTOs= new ArrayList<>();
		return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	 private JobDTO convertToDto(Job job) {		
		Company company = companyClient.getCompany(job.getCompanyId()); 
		// Company company= restTemplate.getForObject("http://COMPANY-SERVICE:8083/companies/" + job.getCompanyId(),Company.class);
		
	    List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
	    
		//		ResponseEntity<List<Review>> reviewResponce=restTemplate.exchange(
		//				"http://REVIEW-SERVICE:8084/reviews?companyId="+ job.getCompanyId(),
		//				HttpMethod.GET,
		//				null,
		//				new ParameterizedTypeReference<List<Review>>() {}
		//				);
				
		//		List<Review> reviews=reviewResponce.getBody();
		
	    JobDTO jobDTO=JobMapper.mapToJobWithCompanyDTO(job, company,reviews);
		//	jobDTO.setCompany(company);
				
		return jobDTO;
	}

	
	public void createJob(Job job) {
		repo.save(job);
	}

	
	public Job updateJob(Long id,Job job) {
		 Job existingJob = repo.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));Job existingCompany = repo.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
		 existingJob.setTitle(job.getTitle());
		 existingJob.setDescription(job.getDescription());
		 existingJob.setMaxSalary(job.getMaxSalary());
		 existingJob.setMinSalary(job.getMinSalary());
		 existingJob.setLocation(job.getLocation());
		 return repo.save(existingJob);
		  
	}

	
	public boolean deleteJob(Long id) {
		if(repo.existsById(id)) {
		   repo.deleteById(id);
		return true;	
		}else {return false;}
	}
	
}
