package com.bridgelabz.fundoonotes.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String mobileNumber;
	
	private LocalDateTime createdTimeStamp;
	
	private LocalDateTime updateTimeStamp;
	
}
