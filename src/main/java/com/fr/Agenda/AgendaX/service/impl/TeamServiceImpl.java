package com.fr.Agenda.AgendaX.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.fr.Agenda.AgendaX.entity.Team;
import com.fr.Agenda.AgendaX.repository.ITeamRepository;
import com.fr.Agenda.AgendaX.service.ITeamService;

public class TeamServiceImpl implements ITeamService{

	@Autowired
	private ITeamRepository repo;

	public Team create(Team team) {
		if (repo.exists(Example.of(team))) {
			return null;
		} else {
			return repo.save(team);
		}
	}

	public Team update(Team team) {
		if (repo.existsById(team.getId())) {
			return repo.save(team);
		} else {
			return null;
		}
	}

	public Team readById(Long id) {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public List<Team> readAll() {
		return repo.findByDeletedFalse();
	}

	public List<Team> readDelete() {
		return repo.findByDeletedTrue();
	}

	public List<Team> readAllReal() {
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