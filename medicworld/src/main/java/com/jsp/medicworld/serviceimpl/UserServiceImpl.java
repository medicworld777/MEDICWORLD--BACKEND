package com.jsp.medicworld.serviceimpl;

import java.util.Optional;

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
	
	String emailid;

	@Override
	public Object signupUser(User u) 
	{
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

		if (emailorphone.contains("@")) {		
			u1 = rep.findByEmailOrPhone(emailorphone, 0);
		}
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

	
	@Override
	public String findByemail(String email) {
		Optional<User> optionalUser = rep.findByemail(email);
		if (optionalUser.isPresent()) {
		 rep.findByemail(email);
		 this.emailid=email;
	     return email;}
	     else {
				try {
					throw new UserNotFoundException("Account with email " + email + " does not exist.");
				} catch (UserNotFoundException anf) {
					return anf.getMessage();
				}

	     }
	}
	
	@Override
	public String changePwdService(String password, String confirm)	 {

		Optional<User> optionalUser = rep.findByemail(emailid);
		if (optionalUser.isPresent()) {
			if (password.equals(confirm)) 
			{
				String valid = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$";
				if (password.matches(valid)) {
					User user = optionalUser.get();
					user.setPassword(password);
					rep.save(user);
					return "Password reset successful";
				} else {
					try {
						throw new PasswordInvalidException(
								"Invalid password. Password must be between 8 to 20 characters long and should contain at least one digit, one lowercase, one uppercase and one special character.");
					} catch (PasswordInvalidException e) {
						return e.getMessage();
					}

				}
			} else {

				return "New password and confirm password does not match.";
			}

		} else {
			try {
				throw new PasswordInvalidException("Account with email " + emailid + " does not exist.");
			} catch (PasswordInvalidException anf) {
				return anf.getMessage();
			}

		}
	}

	
}
