package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Ref;

public interface RefService {
	public void saveRef();

	public List<Ref> getAll();

	public Ref findByRefTypeID(int RefTypeID);
	
	public List<Ref> findAll();
}
