package com.fr.Agenda.AgendaX.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.fr.Agenda.AgendaX.entity.Chief;
import com.fr.Agenda.AgendaX.repository.IChiefRepository;
import com.fr.Agenda.AgendaX.service.IChiefService;

@Service
public class ChiefServiceImpl implements IChiefService {

	@Autowired
	private IChiefRepository repo;

	public Chief create(Chief chief) {
		if (repo.exists(Example.of(chief))) {
			return null;
		} else {
			return repo.save(chief);
		}
	}

	public Chief update(Chief chief) {
		if (repo.existsById(chief.getId())) {
			return repo.save(chief);
		} else {
			return null;
		}
	}

	public Chief readById(Long id) {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public List<Chief> readAll() {
		return repo.findByDeletedFalse();
	}

	public List<Chief> readDelete() {
		return repo.findByDeletedTrue();
	}

	public List<Chief> readAllReal() {
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
