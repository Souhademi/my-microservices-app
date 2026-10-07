package com.example.jobms.mapper;

import java.util.List;

import com.example.jobms.jobs.Job;
import com.example.jobms.jobs.dto.JobDTO;
import com.example.jobms.jobs.external.Company;
import com.example.jobms.jobs.external.Review;


public class JobMapper {
	public static JobDTO mapToJobWithCompanyDTO(Job job,Company company,List<Review> reviews) {
		JobDTO jobDTO= new JobDTO();
		jobDTO.setId(job.getId());
		jobDTO.setDescription(job.getDescription());
		jobDTO.setLocation(job.getLocation());
		jobDTO.setMaxSalary(job.getMaxSalary());
		jobDTO.setMinSalary(job.getMinSalary());
		jobDTO.setTitle(job.getTitle());
		jobDTO.setCompany(company);
		jobDTO.setReview(reviews);
	
		return jobDTO;
	}
	

}
