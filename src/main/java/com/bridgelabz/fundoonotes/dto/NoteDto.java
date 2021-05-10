package com.bridgelabz.fundoonotes.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NoteDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8904421990863760726L;

	@NotBlank(message = "title cannnot be blank")
	private String title;
	
	@NotBlank(message = "description cannot be blank")
	private String description;
	
	private String reminder;
	
	private String colour;
	
	private Boolean archive;
	
	private Boolean pin;
	
	private Boolean trash;
	
}
