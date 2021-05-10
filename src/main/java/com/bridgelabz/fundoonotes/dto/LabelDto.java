package com.bridgelabz.fundoonotes.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LabelDto {

	@NotBlank(message = "label name cannot be blank")
	private String labelName;
}
