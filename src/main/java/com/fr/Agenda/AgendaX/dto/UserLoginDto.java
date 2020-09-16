package com.fr.Agenda.AgendaX.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter 
@ToString 
@NoArgsConstructor
public class UserLoginDto {

	@Email
	@NotNull
	@NotBlank
	@ApiModelProperty(required = true, example = "admin@gmail.com")
	protected String email;
	
	@NotNull
	@NotEmpty
	@ApiModelProperty(required = true, example = "PasswOrd")
	protected String pwd;
}
