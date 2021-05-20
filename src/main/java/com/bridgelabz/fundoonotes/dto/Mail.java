package com.bridgelabz.fundoonotes.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail implements Serializable {

	private String to;
	
	private String subject;
	
	private String context;
	
	private String from;
	
}
