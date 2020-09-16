package com.fr.Agenda.AgendaX.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.fr.Agenda.AgendaX.entity.Result;
import com.fr.Agenda.AgendaX.repository.IResultRepository;
import com.fr.Agenda.AgendaX.service.IResultService;

public class ResultServiceImpl implements IResultService{

	@Autowired
	private IResultRepository repo;

	public Result create(Result result) {
		if (repo.exists(Example.of(result))) {
			return null;
		} else {
			return repo.save(result);
		}
	}

	public Result update(Result result) {
		if (repo.existsById(result.getId())) {
			return repo.save(result);
		} else {
			return null;
		}
	}

	public Result readById(Long id) {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public List<Result> readAll() {
		return repo.findByDeletedFalse();
	}

	public List<Result> readDelete() {
		return repo.findByDeletedTrue();
	}

	public List<Result> readAllReal() {
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