package com.fr.Agenda.AgendaX.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.fr.Agenda.AgendaX.entity.Plan;

public interface IPlanRepository extends JpaRepositoryImplementation<Plan, Long>, IAbstractRepository<Plan, Long>{

}
