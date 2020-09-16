package com.fr.Agenda.AgendaX.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.fr.Agenda.AgendaX.entity.Experiment;
import com.fr.Agenda.AgendaX.repository.IExperimentRepository;
import com.fr.Agenda.AgendaX.service.IExperiementService;

public class ExperimentServiceImpl implements IExperiementService {

	@Autowired
	private IExperimentRepository repo;

	public Experiment create(Experiment experiment) {
		if (repo.exists(Example.of(experiment))) {
			return null;
		} else {
			return repo.save(experiment);
		}
	}

	public Experiment update(Experiment experiment) {
		if (repo.existsById(experiment.getId())) {
			return repo.save(experiment);
		} else {
			return null;
		}
	}

	public Experiment readById(Long id) {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public List<Experiment> readAll() {
		return repo.findByDeletedFalse();
	}

	public List<Experiment> readDelete() {
		return repo.findByDeletedTrue();
	}

	public List<Experiment> readAllReal() {
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

	public List<String> readManipulations(Long id) {
		return repo.ManipulationList(id);
	}

}
