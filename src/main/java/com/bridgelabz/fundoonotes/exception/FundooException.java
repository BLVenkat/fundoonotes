package com.bridgelabz.fundoonotes.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FundooException extends RuntimeException {
	
	private static final long serialVersionUID = -3407636098889460334L;
	
	private int statusCode;
	
	private String statusMessage;
}
