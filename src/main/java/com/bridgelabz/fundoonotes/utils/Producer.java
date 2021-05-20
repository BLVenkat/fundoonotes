package com.bridgelabz.fundoonotes.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.dto.Mail;

@Component
public class Producer {

	@Value("${topic.name}")
	private String topicName;
	 
	@Autowired
    private KafkaTemplate<String, Mail> kafkaTemplate;
	
	public void sendMessage(Mail message) {
		
		kafkaTemplate.send("fundoo1", message);
	}

}
