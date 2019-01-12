package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Reason;

@Repository
public interface ReasonRepository extends JpaRepository<Reason, Integer> {
//	public Reason findByReasonType(int reasonTypeID); 
}
