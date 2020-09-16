package com.fr.Agenda.AgendaX.controller.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import com.fr.Agenda.AgendaX.controller.IUserController;
import com.fr.Agenda.AgendaX.converter.UserCreateDtoConverter;
import com.fr.Agenda.AgendaX.converter.UserUpdateDtoConverter;
import com.fr.Agenda.AgendaX.dto.UserCreateDto;
import com.fr.Agenda.AgendaX.dto.UserLoginDto;
import com.fr.Agenda.AgendaX.dto.UserUpdateDto;
import com.fr.Agenda.AgendaX.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class IUserControllerImpl implements IUserController{
	
	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService service;
	
	
	public UserCreateDto create(@Valid UserCreateDto dto) {
		log.info("Create a new user" +dto);
		return UserCreateDtoConverter.MAPPER.convert(service.create(UserCreateDtoConverter.MAPPER.convert(dto)));
	}

	
	public UserUpdateDto update(@Valid UserUpdateDto dto) {
		log.info("Update a user" +dto);
		return UserUpdateDtoConverter.MAPPER.convert(service.update(UserUpdateDtoConverter.MAPPER.convert(dto)));
	}

	
	public UserCreateDto readById(Long id) {
		log.info("Read information for a user where id is :" +id);
		return UserCreateDtoConverter.MAPPER.convert(service.readById(id));
	}
	
	public UserUpdateDto findByEmail(String email) {
		log.info("Read information for a user where email is :" +email);
		return UserUpdateDtoConverter.MAPPER.convert(service.findByEmail(email));
	}
	
	public List<UserCreateDto> findByAdress(String adress) {
		log.info("Find a user by his adress :" +adress);
		return UserCreateDtoConverter.MAPPER.convert(service.findByAdress(adress));
	}

	
	public List<UserCreateDto> findByLastName(String lastName) {
		log.info("Find a user by his lastname :" +lastName);
		return UserCreateDtoConverter.MAPPER.convert(service.findByLastName(lastName));
	}

	
	public List<UserCreateDto> findByZipCode(Integer zipCode) {
		log.info("Find a user by his zipcode :" +zipCode);
		return UserCreateDtoConverter.MAPPER.convert(service.findByZipCode(zipCode));
	}

	
	public List<UserCreateDto> findByCity(String city) {
		log.info("Find a user by his city :" +city);
		return UserCreateDtoConverter.MAPPER.convert(service.findByCity(city));
	}

	
	public List<UserCreateDto> readAll() {
		log.info("Read all user where deleted = false");
		return UserCreateDtoConverter.MAPPER.convert(service.readAll());
	}

	
	public List<UserCreateDto> readDelete() {
		log.info("Read all user where deleted = true");
		return UserCreateDtoConverter.MAPPER.convert(service.readDelete());
	}

	
	public List<UserCreateDto> readAllReal() {
		log.info("Read all user");
		return UserCreateDtoConverter.MAPPER.convert(service.readAllReal());
	}

	
	public boolean deleteById(Long id) {
		log.info("Delete a user where id is :" +id);
		return service.deleteById(id);
	}

	
	public boolean setDeletedTrue(Long id) {
		log.info("SetDeleteTrue a user where id is :" +id);
		return service.setDeletedTrue(id);
	}


	public UserLoginDto login(UserLoginDto loginDto) {
		log.info("Login user where login is" +loginDto);
		if (service.login(loginDto.getEmail(), loginDto.getPwd()) != null) {
			return loginDto;
		} else {
			return null;
		}
	}

}
