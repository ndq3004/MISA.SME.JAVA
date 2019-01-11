package com.webencyclop.demo.controller;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.webencyclop.demo.model.User;
import com.webencyclop.demo.service.UserService;


@RestController
public class AuthenticationController {

	private static final String ERROR_MESSAGE = "error1";
	@Autowired
	UserService userService;
	
	@RequestMapping(value = { "/test" }, method = RequestMethod.GET)
	public ModelAndView test() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("test"); // resources/template/test.html
		return modelAndView;
	}
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Views/login"); // resources/template/login.html
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user); 
		modelAndView.setViewName("/Views/register"); // resources/template/register.html
		return modelAndView;
	}
	

	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/Views/home"); // resources/template/home.html
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity registerUser(@RequestBody User user, BindingResult bindingResult) {
		System.out.println("registering!" + user.getContactMobile() + user.getContactEmail());
		
		// Check for the validation
		if(bindingResult.hasErrors() ) {
			System.out.println("error");
			return new ResponseEntity<>(ERROR_MESSAGE, new HttpHeaders(), HttpStatus.BAD_REQUEST);

		}
		else if(userService.isUserAlreadyPresent(user)){
			System.out.println("exist");
			return new ResponseEntity<>("accountexist", new HttpHeaders(), HttpStatus.CONFLICT);		
		}
		// lưu user nếu không có lỗi
		else {		
			
			HttpHeaders httphd = new HttpHeaders();
			httphd.add("key","value");
			userService.saveUser(user);
			System.out.println("flag");
			return new ResponseEntity<>("Register success!",httphd, HttpStatus.OK);
		}

	}
}
