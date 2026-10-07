package com.example.micro.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.micro.companies.Company;

import lombok.Data;


@Data
@Service
public class JobServiceImpl{
	
	
	@Autowired
	private final JobRepo repo;
	
	 public JobServiceImpl(JobRepo repo) {
			super();
			this.repo = repo;
	}

	 public Job getJobById(final Long id) {
		return repo.findById(id).orElse(null);
	}
	
	public List<Job> findAllJobs() {
		return repo.findAll();
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
		    return repo.save(existingJob);
		  
	}

	
	public boolean deleteJob(Long id) {
		if(repo.existsById(id)) {
		   repo.deleteById(id);
		return true;	
		}else {return false;}
	}
	
}
