package com.fr.Agenda.AgendaX.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.fr.Agenda.AgendaX.entity.Team;

@Repository
public interface ITeamRepository extends JpaRepositoryImplementation<Team, Long>, IAbstractRepository<Team, Long>{

}
