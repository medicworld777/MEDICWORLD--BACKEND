package com.jsp.medicworld.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jsp.medicworld.entity.User;


@Repository

public interface UserDao extends JpaRepository<User, Integer> {

	@Query(value = "select * from user where email=?1 and phone=?2", nativeQuery = true)
	User findByEmailAndPhone(String username, long phone);

	User findByEmailOrPhone(String email,long phone);
	
	@Query("SELECT u FROM User u WHERE u.username=:query OR u.email=:query")
	User findUser(String query);
	Optional<User> findByemail(String email);

	
}
