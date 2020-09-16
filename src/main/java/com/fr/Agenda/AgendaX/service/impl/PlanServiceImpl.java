package com.fr.Agenda.AgendaX.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.fr.Agenda.AgendaX.entity.Plan;
import com.fr.Agenda.AgendaX.repository.IPlanRepository;
import com.fr.Agenda.AgendaX.service.IPlanService;

public class PlanServiceImpl implements IPlanService{

	@Autowired
	private IPlanRepository repo;

	public Plan create(Plan plan) {
		if (repo.exists(Example.of(plan))) {
			return null;
		} else {
			return repo.save(plan);
		}
	}

	public Plan update(Plan plan) {
		if (repo.existsById(plan.getId())) {
			return repo.save(plan);
		} else {
			return null;
		}
	}

	public Plan readById(Long id) {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public List<Plan> readAll() {
		return repo.findByDeletedFalse();
	}

	public List<Plan> readDelete() {
		return repo.findByDeletedTrue();
	}

	public List<Plan> readAllReal() {
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
