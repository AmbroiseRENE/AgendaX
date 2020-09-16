package com.fr.Agenda.AgendaX.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.fr.Agenda.AgendaX.entity.Laboratory;
import com.fr.Agenda.AgendaX.repository.ILaboratoryRepository;
import com.fr.Agenda.AgendaX.service.ILaboratyService;

public class LaboratoryServiceImpl implements ILaboratyService {

	@Autowired
	private ILaboratoryRepository repo;

	public Laboratory create(Laboratory laboratory) {
		if (repo.exists(Example.of(laboratory))) {
			return null;
		} else {
			return repo.save(laboratory);
		}
	}

	public Laboratory update(Laboratory laboratory) {
		if (repo.existsById(laboratory.getId())) {
			return repo.save(laboratory);
		} else {
			return null;
		}
	}

	public Laboratory readById(Long id) {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public List<Laboratory> readAll() {
		return repo.findByDeletedFalse();
	}

	public List<Laboratory> readDelete() {
		return repo.findByDeletedTrue();
	}

	public List<Laboratory> readAllReal() {
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

	@Override
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