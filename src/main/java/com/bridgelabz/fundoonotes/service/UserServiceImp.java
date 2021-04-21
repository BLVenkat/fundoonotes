package com.bridgelabz.fundoonotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.bridgelabz.fundoonotes.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public void register(UserDTO userDto) {
		
	}

}
