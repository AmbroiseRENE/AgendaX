package com.fr.Agenda.AgendaX.service;

import java.util.List;

import com.fr.Agenda.AgendaX.entity.Experiment;
import com.fr.Agenda.AgendaX.entity.Manipulation;

public interface IExperiementService extends IAbstractEntityService<Experiment, Long> {

	public List<Manipulation> readManipulations(Long id);
}
