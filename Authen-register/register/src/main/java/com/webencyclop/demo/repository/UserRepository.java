package com.webencyclop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webencyclop.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByContactEmail(String contactEmail);
	//public User findByEmail(String contactEmail);
	public User findByContactMobile(String contactMobile);
}
