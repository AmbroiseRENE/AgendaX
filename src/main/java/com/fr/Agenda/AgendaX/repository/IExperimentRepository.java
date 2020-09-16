package com.fr.Agenda.AgendaX.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.Agenda.AgendaX.entity.Experiment;

@Repository
public interface IExperimentRepository	extends JpaRepositoryImplementation<Experiment, Long>, IAbstractRepository<Experiment, Long> {

	
	@Query(value = "Select distinct manipulation.name from manipulation,experiment where manipulation.id=experiment.id_experiment and manipulation.id =:id" ,nativeQuery = true)
	public List<String> ManipulationList(@Param(value = "id")Long idManipulation);
}
