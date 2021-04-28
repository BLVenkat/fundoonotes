package com.bridgelabz.fundoonotes.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.exception.FundooException;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.utils.EmailService;
import com.bridgelabz.fundoonotes.utils.TokenService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private TokenService tokenService;
	
	@Value("${verify.url}")
	private String verifyUrl;
	
	@Override
	public void register(UserDTO userDto) {
		
		Optional<User> userCheck =  userRepo.findByEmail(userDto.getEmail());
		if(userCheck.isPresent())
			throw  new FundooException(HttpStatus.CONFLICT.value(),"User Already Exist");
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User savedUser = userRepo.save(user);
		String token = tokenService.createToken(savedUser.getId());
		emailService.sendMail(savedUser.getEmail(), "verfiy email", verifyUrl+token);
	}

	@Override
	public void verifyEmail(String token) {
		Long id = tokenService.decodeToken(token);	
		User user = userRepo.findById(id).orElseThrow(() -> new FundooException(HttpStatus.NOT_FOUND.value(), "User Not Found"));
		user.setIsVerified(true);
		userRepo.save(user);
	}

}
