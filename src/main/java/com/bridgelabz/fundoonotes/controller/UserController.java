package com.bridgelabz.fundoonotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/register")
	private ResponseEntity<Response> register(@RequestBody UserDTO userDto){
		
		userService.register(userDto);
		
		return new ResponseEntity<Response>(new Response(HttpStatus.CREATED.value(), "User Registered", ""), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/verify/{token}")
	private ResponseEntity<Response> verifyEmail(@PathVariable String token){
		
		userService.verifyEmail(token);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Email Verified ", ""), HttpStatus.OK);
	}
	
}
