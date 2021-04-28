package com.bridgelabz.fundoonotes.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Response {

	private int statusCode;
	
	private String statusMessage;
	
	private Object data;
	
	
}
