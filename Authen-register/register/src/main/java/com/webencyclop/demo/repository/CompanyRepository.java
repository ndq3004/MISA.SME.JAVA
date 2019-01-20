package com.webencyclop.demo.repository;

import java.util.Set;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webencyclop.demo.model.Company;

import antlr.collections.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	@Query(value="Select c from Company c inner join user_company u ON c.ma_so_thue=u.ma_so_thue"
			+ "inner join user a ON u.auth_user_id = a.auth_user_id where a.email = :email",nativeQuery=true)
	public List<String> findByCompanyTaxNumber1(@Param("contactEmail")String email);
	
	public Company findByCompanyTaxNumber(String companyTaxNumber);
}
