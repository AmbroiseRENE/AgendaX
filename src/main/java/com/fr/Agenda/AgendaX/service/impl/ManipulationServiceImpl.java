package com.fr.Agenda.AgendaX.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.fr.Agenda.AgendaX.entity.Manipulation;
import com.fr.Agenda.AgendaX.repository.IManipulationRepository;
import com.fr.Agenda.AgendaX.service.IManipulationService;

public class ManipulationServiceImpl implements IManipulationService{

	@Autowired
	private IManipulationRepository repo;

	public Manipulation create(Manipulation manipulation) {
		if (repo.exists(Example.of(manipulation))) {
			return null;
		} else {
			return repo.save(manipulation);
		}
	}

	public Manipulation update(Manipulation manipulation) {
		if (repo.existsById(manipulation.getId())) {
			return repo.save(manipulation);
		} else {
			return null;
		}
	}

	public Manipulation readById(Long id) {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public List<Manipulation> readAll() {
		return repo.findByDeletedFalse();
	}

	public List<Manipulation> readDelete() {
		return repo.findByDeletedTrue();
	}

	public List<Manipulation> readAllReal() {
		return repo.findAll();
	}

	public boolean deleteById(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public boolean setDeletedTrue(Long id) {
		if (repo.existsById(id)) {
			if (repo.fakeDelete(id) == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}