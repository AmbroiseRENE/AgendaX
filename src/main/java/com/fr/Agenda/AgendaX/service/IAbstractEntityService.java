package com.fr.Agenda.AgendaX.service;

import java.util.List;

public interface IAbstractEntityService <ENTITY, ID> {

	public ENTITY create(ENTITY entity);
	
	public ENTITY update(ENTITY entity);
	
	public ENTITY readById(ID id);
	
	// Deleted = false
	public List<ENTITY> readAll();
	
	// Deleted = true
	public List<ENTITY> readDelete();
	
	// All
	public List<ENTITY> readAllReal();
	
	public boolean deleteById(ID id);
	
	public boolean setDeletedTrue(ID id);
}
