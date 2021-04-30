package com.bridgelabz.fundoonotes.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginDto {

    @ApiModelProperty(notes = "provide email",required = true,example = "admin@gmail.com")	
	private String email;
	
	private String password;
}
