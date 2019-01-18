package com.webencyclop.demo.auth.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.webencyclop.demo.auth.exception.InvalidRequestException;
import com.webencyclop.demo.auth.middlewares.Authenticate;
import com.webencyclop.demo.auth.util.JwtUtil;
import com.webencyclop.demo.auth.util.ValidateUtil;
import com.webencyclop.demo.model.Role;
import com.webencyclop.demo.model.User;
import com.webencyclop.demo.repository.RoleRepository;
import com.webencyclop.demo.repository.UserRepository;
import com.webencyclop.demo.service.*;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import com.webencyclop.demo.form.*;
@CrossOrigin
@RestController
public class UserController {
	private static final String ERROR_MESSAGE = "error1";
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserService userService;
	@Autowired
	UserServiceImp userSvIml;
	@CrossOrigin
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception{
		JSONParser parser = new JSONParser();
		
        JSONObject json = new JSONObject();
        try {
            json = (JSONObject) parser.parse(httpServletRequest.getReader());
        } catch (Exception e) {

        	throw new InvalidRequestException();
        }
        
	    String email=(String) json.get("email");
		String password=(String) json.get("password");
		if(!ValidateUtil.isValidEmailAddress(email) ||! ValidateUtil.isValidPassword(password)) throw new InvalidRequestException() ;
		
		// cần 1 hàm kiểm tra username ,password trong if ở đây 
		if(!userSvIml.isUser(email, password)) throw new InvalidRequestException();
		//cần 1 hàm đổ các dữ liệu vào đây, dữ liệu để nhận biết người dùng được nhét vào token
		
		User user = userRepository.findByContactEmail(email);
	    long id=user.getId();	
	    Set<Role> role = user.getRoles();
	    int roleId;
	    if(role.contains("ADMIN_USER")) {
	    	roleId = roleRepository.findByRole("ADMIN_USER").getId();
	    }
	    else {// if(role.contains("SITE_USER")) {
	    	roleId = roleRepository.findByRole("SITE_USER").getId();
		}
	    

	    
	    // Tạo token,set cookie
	    String iss="MISA";
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis=86400000;
        Date exp=new Date(nowMillis+expMillis);
		String token = JwtUtil.generateToken(iss,now,exp,email,id,roleId);
		// một số thông tin basic có thể trả về kèm luôn cho client tiện routing,gửi tiếp req,...,
		JSONObject obj = new JSONObject();
		obj.put("issuer", iss);
		obj.put("roleId", roleId);
		obj.put("email",email);
		obj.put("issuedAt",now);
		obj.put("expiration", exp);
		obj.put("token", token);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
	
//		
//	@RequestMapping(value = "/api/home", method = RequestMethod.GET)
//	public ResponseEntity<Object> home(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {		
//		Authenticate.Auth(httpServletRequest, httpServletResponse);
//		// tra ve du lieu home neu req duoc xac thuc nguoi dung
//		String result="this is home data for authen req";
//		
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
	
//	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
//	public ResponseEntity<Object> login(@RequestBody LoginForm loginForm) throws Exception{
//		
//	    String email= loginForm.getEmail();
//		String password= loginForm.getPassword();
//		
//		System.out.println("flag" + email + password);
//		//if(!ValidateUtil.isValidEmailAddress(email) || !ValidateUtil.isValidPassword(password)) throw new InvalidRequestException() ;
//
//		// cần 1 hàm kiểm tra username ,password trong if ở đây 
//
//		if(!userSvIml.isUser(email, password)) throw new InvalidRequestException();
//		
//		System.out.println("flag2");
//		//cần 1 hàm đổ các dữ liệu vào đây, dữ liệu để nhận biết người dùng được nhét vào token
//		User user = userRepository.findByContactEmail(email);
//    	System.out.println("flag3" + user.getContactEmail());
//	    long id=user.getId();	
//	    int roleId=1;
//
//			
//	    // Tạo token,set cookie
//	    String iss="MISA";
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//        long expMillis=86400000;
//        Date exp=new Date(nowMillis+expMillis);
//		String token = JwtUtil.generateToken(iss,now,exp,email,id,roleId);
//
//		// một số thông tin basic có thể trả về kèm luôn cho client tiện routing,gửi tiếp req,...,
//		JSONObject obj = new JSONObject();
//
//		obj.put("token", token);
//
//		return new ResponseEntity<>(obj, HttpStatus.OK);
//	}
	@RequestMapping(value = "/api/home", method = RequestMethod.GET)
	public ResponseEntity<Object> home(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){		
		Authenticate.Auth(httpServletRequest, httpServletResponse);
		// tra ve du lieu home neu req duoc xac thuc nguoi dung
		//String result="this is home data for authen req";
		String email = (String) httpServletRequest.getAttribute("email");
		JSONObject obj = new JSONObject();
		obj.put("status", "success");
		obj.put("email", email);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/register", method = RequestMethod.POST)
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
//	@RequestMapping(value = "/api/home", method = RequestMethod.GET)
//	public ResponseEntity<Object> home(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {		
//		Authenticate.Auth(httpServletRequest, httpServletResponse);
//		// tra ve du lieu home neu req duoc xac thuc nguoi dung
//		String result="this is home data for authen req";
//		
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
//	
	@RequestMapping(value = "/api/logout", method = RequestMethod.GET)
	public ResponseEntity<Object> logout(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {	
		Authenticate.Auth(httpServletRequest, httpServletResponse);
		//CookieUtil.clear(httpServletResponse);
		String result="Logout success";
		return new ResponseEntity<>(result, HttpStatus.OK);

	}
}
