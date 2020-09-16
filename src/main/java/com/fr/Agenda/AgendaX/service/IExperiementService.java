package com.fr.Agenda.AgendaX.service;

import java.util.List;

import com.fr.Agenda.AgendaX.entity.Experiment;

public interface IExperiementService extends IAbstractEntityService<Experiment, Long> {

	public List<String> readManipulations(Long id);
}
