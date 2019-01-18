package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Ref;

@Repository
public interface RefRepository extends JpaRepository<Ref, Integer> {
	public Ref findByRefType(int reftype);
}
