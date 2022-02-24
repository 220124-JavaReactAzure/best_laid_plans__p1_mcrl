package com.revature.wedding_planner.daos;

import java.util.List;

import com.revature.wedding_planner.models.Vendor;

public interface CrudDAO<T> {

	// CRUD: Create, Read, Update, Delete

	// Create
	T create(T newObj);
	
	// Read
	List<T> findAll();
	T findById(int id);
	
	// Update
	boolean update(T updatedObj);
	
	// Delete
	boolean delete(String id);
	boolean delete(int id);


}
