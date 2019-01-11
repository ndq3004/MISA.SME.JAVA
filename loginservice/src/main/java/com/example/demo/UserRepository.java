package com.example.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ComponentScan
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByContactEmail(String contactEmail);
	public User findByContactMobile(String contactMobile);
}
