package com.example.jobms.jobs.clients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.jobms.jobs.external.Company;



@FeignClient(name="COMPANY-SERVICE")
public interface CompanyClient {
	@GetMapping("/companies/{id}")
	Company getCompany(@PathVariable("id") Long id);
}
