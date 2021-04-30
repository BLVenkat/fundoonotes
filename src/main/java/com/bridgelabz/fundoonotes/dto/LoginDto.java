package com.bridgelabz.fundoonotes.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginDto {

    @ApiModelProperty(notes = "provide email",required = true)	
	private String email;
	
	private String password;
}
