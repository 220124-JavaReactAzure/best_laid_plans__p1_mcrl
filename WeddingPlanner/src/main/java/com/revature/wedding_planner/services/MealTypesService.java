package com.revature.wedding_planner.services;

import java.util.List;

import com.revature.wedding_planner.daos.MealTypesDAO;
import com.revature.wedding_planner.exceptions.InvalidRequestException;
import com.revature.wedding_planner.exceptions.ResourcePersistenceException;
import com.revature.wedding_planner.models.MealTypes;


public class MealTypesService {
	private final MealTypesDAO mealTypesDAO;
	
	public MealTypesService(MealTypesDAO mealTypesDAO) {
		this.mealTypesDAO = new MealTypesDAO();
	}
	
	public boolean addMealType(MealTypes mealType) {
		if(!isValidMealType(mealType)) {
			throw new InvalidRequestException("Invalid mealType data provided");
		}
		
		MealTypes persistedMealType = mealTypesDAO.create(mealType);
		
		if (persistedMealType == null) {
			throw new ResourcePersistenceException("The mealType could not be persisted");
		}
		
		return true;
	}
	
	public MealTypes updateMealType(MealTypes updatedMealType) {
		if(!isValidMealType(updatedMealType)) {
			throw new InvalidRequestException("Invalid mealType data provided");
		}
		if (!mealTypesDAO.update(updatedMealType)) {
			throw new ResourcePersistenceException("Failure updating mealType.");
		}
		return updatedMealType;
	}
	public MealTypes getMealTypeByID(int mealTypeId) {

		MealTypes foundMealType = mealTypesDAO.findById(mealTypeId);

		if (foundMealType != null) {
			return foundMealType;
		} else {
			return null;
		}
	}

	public List<MealTypes> getAllMealTypes() {
		List<MealTypes> allMealTypes = mealTypesDAO.findAll();
		
		if (allMealTypes == null) {
			return null;
		} else {
			return allMealTypes;
		}
	}
	
	public boolean deleteMealType(MealTypes mealType) {
		return mealTypesDAO.delete(mealType);
	}
	public boolean isValidMealType(MealTypes mealType) {
		// TODO expand validity checking
		if(mealType != null) {
			return true;
		}
		return false;
	}
}
