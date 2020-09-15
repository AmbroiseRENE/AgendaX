package com.fr.Agenda.AgendaX.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.fr.Agenda.AgendaX.entity.Project;

@Repository
public interface IProjectRepository extends JpaRepositoryImplementation<Project, Long>, IAbstractRepository<Project, Long>{

}
