package com.fr.Agenda.AgendaX.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.fr.Agenda.AgendaX.entity.Result;

@Repository
public interface IResultRepository extends JpaRepositoryImplementation<Result, Long>, IAbstractRepository<Result, Long> {

}
