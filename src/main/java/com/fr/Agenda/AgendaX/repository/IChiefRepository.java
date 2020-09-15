package com.fr.Agenda.AgendaX.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.Agenda.AgendaX.entity.Chief;

@Repository
public interface IChiefRepository extends JpaRepository<Chief, Long>, IAbstractRepository<Chief, Long>{

}
