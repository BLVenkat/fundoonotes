package com.bridgelabz.fundoonotes.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendMail(String to,String subject, String context) {
		try {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setSubject(subject);
		mail.setText(context);
		mail.setTo(to);
        mail.setFrom("venky70662@gmail.com");
		javaMailSender.send(mail);
		return true;
		}catch (Exception e) {
			return false;
		}
		
	}
}
