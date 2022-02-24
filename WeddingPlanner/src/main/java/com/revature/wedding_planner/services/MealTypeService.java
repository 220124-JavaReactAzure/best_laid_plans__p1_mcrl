package com.revature.wedding_planner.services;

import java.util.List;

import com.revature.wedding_planner.daos.MealTypeDAO;
import com.revature.wedding_planner.exceptions.InvalidRequestException;
import com.revature.wedding_planner.exceptions.ResourcePersistenceException;
import com.revature.wedding_planner.models.MealType;


public class MealTypeService {
	private final MealTypeDAO mealTypesDAO;
	
	public MealTypeService(MealTypeDAO mealTypesDAO) {
		this.mealTypesDAO = mealTypesDAO;
	}
	
	public boolean addMealType(MealType mealType) {
		if(!isValidMealType(mealType)) {
			throw new InvalidRequestException("Invalid mealType data provided");
		}
		
		MealType persistedMealType = mealTypesDAO.create(mealType);
		
		if (persistedMealType == null) {
			throw new ResourcePersistenceException("The mealType could not be persisted");
		}
		
		return true;
	}
	
	public MealType updateMealType(MealType updatedMealType) {
		if(!isValidMealType(updatedMealType)) {
			throw new InvalidRequestException("Invalid mealType data provided");
		}
		if (!mealTypesDAO.update(updatedMealType)) {
			throw new ResourcePersistenceException("Failure updating mealType.");
		}
		return updatedMealType;
	}
	public MealType getMealTypeByID(int mealTypeId) {

		MealType foundMealType = mealTypesDAO.findById(mealTypeId);

		if (foundMealType != null) {
			return foundMealType;
		} else {
			return null;
		}
	}

	public List<MealType> getAllMealTypes() {
		List<MealType> allMealTypes = mealTypesDAO.findAll();
		
		if (allMealTypes == null) {
			return null;
		} else {
			return allMealTypes;
		}
	}
	
	public boolean deleteMealType(MealType mealType) {
		return mealTypesDAO.delete(mealType);
	}
	public boolean isValidMealType(MealType mealType) {
		// TODO expand validity checking
		if(mealType != null) {
			return true;
		}
		return false;
	}
}
