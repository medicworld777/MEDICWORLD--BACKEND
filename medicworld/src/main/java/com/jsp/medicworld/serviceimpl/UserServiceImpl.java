package com.jsp.medicworld.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.medicworld.dao.UserDao;
import com.jsp.medicworld.entity.User;
import com.jsp.medicworld.exception.PasswordInvalidException;
import com.jsp.medicworld.exception.UserAlreadyExistsException;
import com.jsp.medicworld.exception.UserNotFoundException;
import com.jsp.medicworld.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao rep;

	@Override
	public Object signupUser(User u) {
		Object obj = rep.findByEmailAndPhone(u.getEmail(), u.getPhone());
		try {
			if (obj instanceof User) {
				System.out.println(obj);
				throw new UserAlreadyExistsException("User already exist try different User");
			}
		} catch (UserAlreadyExistsException e) {
			return e.getMessage();
		}
		return rep.save(u);
	}

	@Override
	public Object loginUser(String emailorphone, String password) {
		User u1;
//		For email fetching the data
		if (emailorphone.contains("@")) {		
			u1 = rep.findByEmailOrPhone(emailorphone, 0);
		}//for phone number fetching the data
		else {
			Long phoneLong = Long.parseLong(emailorphone);
			u1 = rep.findByEmailOrPhone("", phoneLong);
		}
		if (!(u1 instanceof User)) {
			try {
				throw new UserNotFoundException("User not found for given : "+emailorphone);
			} catch (UserNotFoundException e) {
				return e.getMessage();
			}
		} else {
			if (u1.getPassword().equals(password)) {
				return u1;
			}
			try {
				throw new PasswordInvalidException("Invalid Credentials");
			} catch (PasswordInvalidException e) {
				return e.getMessage();
			}

		}
	}
	
	@Override
	public Object getAllUser() {
		java.util.List<User> l1=rep.findAll();
		if(l1.size()>0) {
			return l1;
		}
		return "No Data in the Data Base";
	}

}
