package com.revature.wedding_planner.daos;

import java.util.List;

public interface CrudDAO<T> {

	// CRUD: Create, Read, Update, Delete

	// Create
	T create(T newObj);
	
	// Read
	List<T> findAll();
	T findById(String id);
	
	// Update
	boolean update(T updatedObj);
	
	// Delete
	boolean delete(String id);
}
