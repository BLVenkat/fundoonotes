package com.bridgelabz.fundoonotes.service;

import com.bridgelabz.fundoonotes.dto.UserDTO;

public interface UserService {

	public void register(UserDTO userDto);
	
	public void verifyEmail(String token);
	//public String login();
}
