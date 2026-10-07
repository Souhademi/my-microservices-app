package com.example.companyms.companies;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepo extends JpaRepository<Company,Long>{

	List<Company> findAllById(Long companyId);


}
