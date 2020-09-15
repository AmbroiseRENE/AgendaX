package com.fr.Agenda.AgendaX.service;

import java.util.List;

import com.fr.Agenda.AgendaX.entity.User;

public interface IUserService extends IAbstractEntityService<User, Long>{

	public List<User> findByAdress(String adress);
	public List<User> findByLastName(String lastName);
	public List<User> findByZipCode(Integer zipCode);
	public List<User> findByCity(String city);
	User login(String email, String pwd);
	public User findByEmail(String email);
}
