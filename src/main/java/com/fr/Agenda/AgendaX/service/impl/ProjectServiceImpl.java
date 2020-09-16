package com.fr.Agenda.AgendaX.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.fr.Agenda.AgendaX.entity.Project;
import com.fr.Agenda.AgendaX.repository.IProjectRepository;
import com.fr.Agenda.AgendaX.service.IProjectService;

public class ProjectServiceImpl implements IProjectService{

	@Autowired
	private IProjectRepository repo;

	public Project create(Project project) {
		if (repo.exists(Example.of(project))) {
			return null;
		} else {
			return repo.save(project);
		}
	}

	public Project update(Project project) {
		if (repo.existsById(project.getId())) {
			return repo.save(project);
		} else {
			return null;
		}
	}

	public Project readById(Long id) {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public List<Project> readAll() {
		return repo.findByDeletedFalse();
	}

	public List<Project> readDelete() {
		return repo.findByDeletedTrue();
	}

	public List<Project> readAllReal() {
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