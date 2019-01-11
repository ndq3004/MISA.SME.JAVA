package com.example.demo;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		// TODO Auto-generated method stub
		//User users = userRepository.findByContactEmail(user.getContactEmail());
		User users = userRepository.findByContactMobile(user.getContactMobile());
		//System.out.println(users.getContactMobile());
		try {
			if(users.getContactMobile().equals("")) return false; 
		}
		catch(NullPointerException e) {
			return false;
		}
//		try {
//			User users = userRepository.findByContactEmail(user.getContactEmail());
//		}
//		catch(NullPointerException e) {
//			return false;
//		}
		return true;
	}

}
