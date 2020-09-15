package com.fr.Agenda.AgendaX.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.fr.Agenda.AgendaX.entity.Manipulation;

@Repository
public interface IManipulationRepository extends JpaRepositoryImplementation<Manipulation, Long>, IAbstractRepository<Manipulation, Long>{

}
