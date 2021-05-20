package com.bridgelabz.fundoonotes.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.dto.Mail;

@Component
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendMail(Mail mailData) {
		try {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setSubject(mailData.getSubject());
		mail.setText(mailData.getContext());
		mail.setTo(mailData.getTo());
        mail.setFrom(mailData.getFrom());
		javaMailSender.send(mail);
		return true;
		}catch (Exception e) {
			return false;
		}
		
	}
}
