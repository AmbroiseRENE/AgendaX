package com.fr.Agenda.AgendaX.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.fr.Agenda.AgendaX.entity.Image;

@Repository
public interface IImageRepository extends JpaRepositoryImplementation<Image, Long>, IAbstractRepository<Image, Long> {

}
