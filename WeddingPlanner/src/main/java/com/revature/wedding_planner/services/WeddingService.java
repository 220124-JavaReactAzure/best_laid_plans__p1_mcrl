package com.revature.wedding_planner.services;

import java.util.List;

import com.revature.wedding_planner.daos.WeddingDAO;
import com.revature.wedding_planner.exceptions.InvalidRequestException;
import com.revature.wedding_planner.exceptions.ResourcePersistenceException;
import com.revature.wedding_planner.models.Wedding;

public class WeddingService {

private final WeddingDAO weddingDAO;
	
	public WeddingService(WeddingDAO weddingDAO) {
		this.weddingDAO = new WeddingDAO();
	}
	
	public boolean addWedding(Wedding wedding) {
		if(!isValidWedding(wedding)) {
			throw new InvalidRequestException("Invalid Wedding data provided");
		}
		
		Wedding persistedWedding = weddingDAO.create(wedding);
		
		if (persistedWedding == null) {
			throw new ResourcePersistenceException("The Wedding could not be persisted");
		}
		
		return true;
	}
	
	public Wedding updateWedding(Wedding updatedWedding) {
		if (!weddingDAO.update(updatedWedding)) {
			throw new ResourcePersistenceException("Failure updating Wedding.");
		}
		return updatedWedding;
	}
	public Wedding getWeddingByID(int weddingId) {

		Wedding foundWedding = weddingDAO.findById(weddingId);

		if (foundWedding != null) {
			return foundWedding;
		} else {
			return null;
		}
	}

	public List<Wedding> getAllWeddings() {
		List<Wedding> allWeddings = weddingDAO.findAll();
		
		if (allWeddings == null) {
			return null;
		} else {
			return allWeddings;
		}
	}
	
	public void deleteWedding(Wedding wedding) {
		weddingDAO.delete(wedding.getId());
	}
	private boolean isValidWedding(Wedding wedding) {
		// TODO expand validity checking
		if(wedding != null) {
			return true;
		}
		return false;
	}
}
