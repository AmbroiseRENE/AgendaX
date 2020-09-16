package com.fr.Agenda.AgendaX.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateDto {

	@ApiModelProperty(required = true)
	protected Long id;
	
	@Email
	@NotNull
	@NotBlank
	@ApiModelProperty(required = true, example = "mail@gmail.com")
	protected String email;
	
	@NotNull
	@ApiModelProperty(required = true, example = "Billy")
	protected String lastName;
	
	@ApiModelProperty(required = false)
	protected String firstName;
	
	@NotNull
	@ApiModelProperty(required = true, example = "14 rue des vignerons")
	protected String adress;
	
	@NotNull
	@ApiModelProperty(required = true, example = "69000")
	protected Integer zipCode;
	
	@NotNull
	@ApiModelProperty(required = true, example = "Lyon")
	protected String city;
	
	@NotNull
	@NotEmpty
	@ApiModelProperty(required = true, example = "Password")
	protected String pwd;
	
	@ApiModelProperty(required = false)
	protected String phone;
	
	@NotNull
	@NotEmpty
	@ApiModelProperty(required = true, example = "CNRS")
	protected String laboratory;

	public UserUpdateDto(Long id, @Email @NotNull @NotBlank String email, @NotNull String lastName,
			String firstName, @NotNull String adress, @NotNull Integer zipCode, @NotNull String city,
			@NotNull @NotEmpty String pwd, String phone, String laboratory) {
		super();
		this.id = id;
		this.email = email;
		this.lastName = lastName;
		this.firstName = firstName;
		this.adress = adress;
		this.zipCode = zipCode;
		this.city = city;
		this.pwd = pwd;
		this.phone = phone;
		this.laboratory = laboratory;
	}

	@Override
	public String toString() {
		return "UpdateCustomerDto [id=" + id + ", email=" + email + ", lastName=" + lastName + ", firstName="
				+ firstName + ", adress=" + adress + ", zipCode=" + zipCode + ", city=" + city + ", pwd=" + pwd
				+ ", phone=" + phone + "laboratory="+laboratory +"]";
	}

}

	