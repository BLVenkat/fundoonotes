package com.bridgelabz.fundoonotes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.configuration.ApplicationConfig;
import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping(value = "/register")
	public ResponseEntity<Response> register(@Valid @RequestBody UserDTO userDto,BindingResult result){
		
		if(result.hasErrors()) {
			return new ResponseEntity<Response>(new Response(HttpStatus.UNPROCESSABLE_ENTITY.value(), result.getAllErrors().get(0).getDefaultMessage(), ""), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		userService.register(userDto);
		
		return new ResponseEntity<Response>(new Response(HttpStatus.CREATED.value(), ApplicationConfig.getMessageAccessor().getMessage("100"), ""), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/verify/{token}")
	public ResponseEntity<Response> verifyEmail(@PathVariable String token){
		
		userService.verifyEmail(token);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(),  ApplicationConfig.getMessageAccessor().getMessage("101"), ""), HttpStatus.OK);
	}
	
}
