package com.jsp.medicworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.medicworld.entity.User;
import com.jsp.medicworld.service.UserService;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService ser;

	
	@PostMapping("signup")
	public ResponseEntity<Object> signupUser(@RequestBody User u) {
		Object obj1 = ser.signupUser(u);
		if (obj1 instanceof User) {
			return ResponseEntity.status(HttpStatus.CREATED).body(obj1);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(obj1);
	}

	@GetMapping("login")
	public ResponseEntity<Object> loginUser(@RequestHeader String emailorphone, @RequestHeader String password) {
		Object obj2 = ser.loginUser(emailorphone, password);
		if (obj2 instanceof User) {
			return ResponseEntity.status(HttpStatus.OK).body(obj2);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(obj2);
	}
	
	@PutMapping("update/{id}")
	public Object updateUser(@PathVariable int id,@RequestBody User update) {
		return null;
	}
	
	@GetMapping("getall")
	public ResponseEntity<Object> getAllUser(){
		if(ser.getAllUser() instanceof User) {
			return ResponseEntity.status(HttpStatus.OK).body(ser.getAllUser());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ser.getAllUser());
	}

}


