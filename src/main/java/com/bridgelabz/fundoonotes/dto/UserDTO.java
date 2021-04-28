package com.bridgelabz.fundoonotes.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {

	@NotBlank(message = "first name cannot be blank")
	private String firstName;

	@NotBlank(message = "last name cannot be blank")
	private String lastName;
	
	@Email(message = "email is not proper")
	private String email;
	
	//@Min(value = 5,message = "password should be min 5 char")
	@Length(min = 5,message = "password should be min 5 char")
	private String password;
	
//	@Min(value = 10,message = "mobile number should be min 10 digits")
//	@Max(value = 10,message = "mobile number should be max 10 digits")
	//@Pattern(regexp = "^[]",)
	@Length(min = 10,message = "mobile number should be max 10 digits")
	private String mobileNumber;
	
		
}
