/*package com.example.micro.companies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.micro.jobs.Job;


import lombok.Data;


@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepo companyRepo;
	
	
	public CompanyService(CompanyRepo companyRepo) {
		super();
		this.companyRepo = companyRepo;
	}


	public List<Company> findAllCompanies() {
		return companyRepo.findAll();
	}
	


public Company updateCompanyById(Long id,Company company) {
	Company existingCompany = companyRepo.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
	existingCompany.setName(company.getName());
	existingCompany.setDescription(company.getDescription());

    return companyRepo.save(existingCompany);
}
	
	public void createCompany(Company company) {
		companyRepo.save(company);
	}
	
	public Company getCompanyById(final Long id) {
		
		return companyRepo.findById(id).orElse(null);
	}
	
	
	public boolean deleteCompany(Long id) {
		if(companyRepo.existsById(id)) {
		   companyRepo.deleteById(id);
		return true;	
		}else {return false;}
	}
}*/

package com.example.companyms.companies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepo companyRepo;

    public CompanyService(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    public List<Company> findAllCompanies() {
        return companyRepo.findAll();
    }
 
    public Company getCompanyById(Long id) {
        return companyRepo.findById(id).orElse(null);
    }

    public void createCompany(Company company) {
        // Ensure each Review and Job has the correct association with the Company
//        if (company.getReviews() != null) {
//            for (Review review : company.getReviews()) {
//                review.setCompany(company);
//            }
//        }
//        if (company.getJobs() != null) {
//            for (Job job : company.getJobs()) {
//                job.setCompany(company);
//            }
//        }
        companyRepo.save(company);
    }

    public Company updateCompanyById(Long id, Company company) {
        Company existingCompany = companyRepo.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
        existingCompany.setName(company.getName());
        existingCompany.setDescription(company.getDescription());

        // Update reviews and jobs
//        if (company.getReviews() != null) {
//            for (Review review : company.getReviews()) {
//                review.setCompany(existingCompany);
//            }
//            existingCompany.setReviews(company.getReviews());
//        }
//
//        if (company.getJobs() != null) {
//            for (Job job : company.getJobs()) {
//                job.setCompany(existingCompany);
//            }
//            existingCompany.setJobs(company.getJobs());
//        }

        return companyRepo.save(existingCompany);
    }

    public boolean deleteCompany(Long id) {
        if (companyRepo.existsById(id)) {
            companyRepo.deleteById(id);
            return true;    
        }
        return false;
    }
}

