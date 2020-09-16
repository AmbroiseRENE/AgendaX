package com.fr.Agenda.AgendaX.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.Agenda.AgendaX.entity.User;
import com.fr.Agenda.AgendaX.repository.IUserRepository;
import com.fr.Agenda.AgendaX.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository repo;

	public User create(User user) {
		if (repo.existsByEmail(user.getEmail())) {
			return null;
		} else {
			return repo.save(user);
		}
	}

	public User update(User user) {
		if (user.getId() != null && readById(user.getId()) != null) {
			repo.save(user);
			return user;
		} else {
			return null;
		}
	}

	public User readById(Long id) {
		if (id != null) {
			return repo.findById(id).orElse(null);
		} else {
			return null;
		}

	}

	public List<User> readAll() {
		return repo.findByDeletedFalse();
	}

	public boolean deleteById(Long id) {
		if (id != null && readById(id) != null) {
			repo.deleteById(id);
			return true;
		} else {
			return false;
		}

	}

	public User findByEmail(String email) {
		if (email != null && repo.existsByEmail(email)) {
			return repo.findByEmail(email);
		} else {
			return null;
		}
	}

	public List<User> findByAdress(String adress) {
		return repo.findByAdress(adress);
	}

	public List<User> findByLastName(String lastName) {
		return repo.findByLastName(lastName);
	}

	public List<User> findByZipCode(Integer zipCode) {
		return repo.findByZipCode(zipCode);
	}

	public List<User> findByCity(String city) {
		return repo.findByCity(city);
	}

	public boolean setDeletedTrue(Long id) {
		if (id != null && readById(id) != null) {
			repo.fakeDelete(id);
			return true;
		} else {
			return false;
		}
	}

	public User login(String email, String pwd) {

		return repo.findByEmailAndPwd(email, pwd);
	}

	public List<User> readDelete() {
		return repo.findByDeletedTrue();
	}

	public List<User> readAllReal() {
		return repo.findAll();
	}

}