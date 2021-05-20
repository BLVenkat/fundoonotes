package com.bridgelabz.fundoonotes.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.dto.Mail;

@Component
public class Consumer {

//	@Value("${topic.name}")
//	private String topicName;
//	 
//	@Value("${kafka.groupid}")
//	private String groupId;
//	 
	@Autowired
	EmailService emailServie;
	
	@KafkaListener(topics  = "fundoo1", groupId = "group")
    public void consume(Mail message) 
    {
    	emailServie.sendMail(message);
    }
}
