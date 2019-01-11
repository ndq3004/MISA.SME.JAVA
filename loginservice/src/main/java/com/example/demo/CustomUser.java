package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;



public class CustomUser extends User {

	private static final long serialVersionUID = 1L;
	private int id;
	private String contactMobile;
	private String status;
	//chuyen role  sang authority
	 private static Collection<GrantedAuthority> getAuthorities(com.example.demo.User user) {
         Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
         for (com.example.demo.Role role : user.getRoles()) {
             grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
         }
         return grantedAuthorities;
     }
	
	public CustomUser(com.example.demo.User user) {
		super(user.getContactEmail(), user.getPassword(),getAuthorities(user));

		this.id = user.getId();
		this.contactMobile = user.getContactMobile();
		this.status = user.getStatus();
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	
	
	
}
