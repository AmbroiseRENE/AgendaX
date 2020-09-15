package com.fr.Agenda.AgendaX.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fr.Agenda.AgendaX.entity.AbstractEntity;

@Repository
public interface IAbstractRepository<E extends AbstractEntity, ID> {

	@Transactional
	@Modifying
	@Query(value = "UPDATE #{#entityName} SET deleted = true WHERE id = :id")
	public int fakeDelete(ID id);
	
	public List<E> findByDeletedTrue();

	public List<E> findByDeletedFalse();	
}
