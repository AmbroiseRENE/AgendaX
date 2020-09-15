package com.fr.Agenda.AgendaX.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.fr.Agenda.AgendaX.entity.Experiment;

@Repository
public interface IExperienceRepository	extends JpaRepositoryImplementation<Experiment, Long>, IAbstractRepository<Experiment, Long> {

}
