package it.unical.demacs.informatica.digitales.app.dao;

import java.util.Set;

public interface DAO<T> {

	// CREATE
	String create(T t);
	
	// UPDATE
	String update(T t);
	
	// RETRIEVE
	long findId(T t);
	T findById(long id);
	//Set<T> findAll();
	
	// DELETE
	//boolean delete(T t);
	
}
