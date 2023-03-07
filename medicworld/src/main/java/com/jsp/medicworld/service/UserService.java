package com.jsp.medicworld.service;

import com.jsp.medicworld.entity.User;



public interface UserService {
	
	Object signupUser(User u);
	
	Object loginUser(String email,String password);
	
	Object getAllUser();

}