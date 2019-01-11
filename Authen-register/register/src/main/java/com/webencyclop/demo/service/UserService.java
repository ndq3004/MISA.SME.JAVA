package com.webencyclop.demo.service;

import java.lang.reflect.InvocationTargetException;

import com.webencyclop.demo.model.User;
public interface UserService {
	public void saveUser(User user);
	//public void saveRole(User user);
	
	public boolean isUserAlreadyPresent(User user); 
	public boolean isUser(String email, String password);
}
