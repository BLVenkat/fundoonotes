package com.bridgelabz.fundoonotes.service;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.UserDTO;

public interface UserService {

	public void register(UserDTO userDto);
	
	public void verifyEmail(String token);
	
	public String login(LoginDto loginDto);
	
	public void forgotPassword(String email);
	
	public void resetPassword(String token,String password);
	
	public String uploadProfileImage(String token,MultipartFile file	);
}

