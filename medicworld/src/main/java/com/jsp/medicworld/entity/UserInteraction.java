package com.jsp.medicworld.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserInteraction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int disid;
	private String bp;
	private String sugar;
	private String others;

}
