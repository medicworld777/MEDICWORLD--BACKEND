package com.jsp.medicworld.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int uid;
	private String username;
	private String email;
	private long password;
	private long phone;
	private int age;
	private String bp;
	private String sugar;
	private String address;
	private int pincode;

}
