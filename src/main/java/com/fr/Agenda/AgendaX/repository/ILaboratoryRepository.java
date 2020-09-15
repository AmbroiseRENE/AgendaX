package com.fr.Agenda.AgendaX.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.fr.Agenda.AgendaX.entity.Laboratory;

@Repository
public interface ILaboratoryRepository extends JpaRepositoryImplementation<Laboratory, Long>, IAbstractRepository<Laboratory, Long>{

}
