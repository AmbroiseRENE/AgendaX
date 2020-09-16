package com.fr.Agenda.AgendaX.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.Agenda.AgendaX.constants.AbstractConstants;
import com.fr.Agenda.AgendaX.dto.UserCreateDto;
import com.fr.Agenda.AgendaX.dto.UserLoginDto;
import com.fr.Agenda.AgendaX.dto.UserUpdateDto;

@RequestMapping
@CrossOrigin
public interface IUserController {

	@PostMapping(path = AbstractConstants.User.CREATE)
	public UserCreateDto create(@Valid @RequestBody UserCreateDto dto);

	@PutMapping(path = AbstractConstants.User.UPDATE)
	public UserUpdateDto update(@Valid @RequestBody UserUpdateDto dto);

	@GetMapping(path = AbstractConstants.User.READ_ID)
	public UserCreateDto readById(@PathVariable Long id);

	@GetMapping(path = AbstractConstants.User.READ_EMAIL)
	public UserUpdateDto findByEmail(@PathVariable String email);

	@GetMapping(path = AbstractConstants.User.GET_ADRESS)
	public List<UserCreateDto> findByAdress(@PathVariable String adress);

	@GetMapping(path = AbstractConstants.User.GET_LASTNAME)
	public List<UserCreateDto> findByLastName(@PathVariable String lastName);

	@GetMapping(path = AbstractConstants.User.GET_ZIPCODE)
	public List<UserCreateDto> findByZipCode(@PathVariable Integer zipCode);

	@GetMapping(path = AbstractConstants.User.GET_CITY)
	public List<UserCreateDto> findByCity(@PathVariable String city);

	@GetMapping(path = AbstractConstants.User.READ_ALL)
	public List<UserCreateDto> readAll();

	@GetMapping(path = AbstractConstants.User.READ_DELETE)
	public List<UserCreateDto> readDelete();

	@GetMapping(path = AbstractConstants.User.READ_ALL_REAL)
	public List<UserCreateDto> readAllReal();

	@DeleteMapping(path = AbstractConstants.User.DELETE_ID)
	public boolean deleteById(@PathVariable Long id);

	@PutMapping(path = AbstractConstants.User.SET_DELETED_TRUE)
	public boolean setDeletedTrue(@PathVariable Long id);

	@PostMapping(path = AbstractConstants.User.LOGIN)
	public UserLoginDto login(@Valid @RequestBody UserLoginDto loginDto);
}
