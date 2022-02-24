package com.revature.wedding_planner.services;

import java.util.List;

import com.revature.wedding_planner.daos.UserTypesDAO;
import com.revature.wedding_planner.exceptions.InvalidRequestException;
import com.revature.wedding_planner.exceptions.ResourcePersistenceException;
import com.revature.wedding_planner.models.UserTypes;

public class UserTypesService {

	private final UserTypesDAO userTypesDAO;

	public UserTypesService(UserTypesDAO userTypesDAO) {
		this.userTypesDAO = userTypesDAO;
	}

	public boolean addUserType(UserTypes userType) {
		if (!isValidUserType(userType)) {
			throw new InvalidRequestException("Invalid userType data provided");
		}

		UserTypes persistedUserType = userTypesDAO.create(userType);

		if (persistedUserType == null) {
			throw new ResourcePersistenceException("The userType could not be persisted");
		}

		return true;
	}

	public UserTypes updateUserType(UserTypes updatedUserType) {
		if (!isValidUserType(updatedUserType)) {
			throw new InvalidRequestException("Invalid userType data provided");
		}
		if (!userTypesDAO.update(updatedUserType)) {
			throw new ResourcePersistenceException("Failure updating userType.");
		}
		return updatedUserType;
	}

	public UserTypes getUserTypeByID(int userTypeId) {

		UserTypes foundUserType = userTypesDAO.findById(userTypeId);

		if (foundUserType != null) {
			return foundUserType;
		} else {
			return null;
		}
	}

	public List<UserTypes> getAllUserTypes() {
		List<UserTypes> allUserTypes = userTypesDAO.findAll();

		if (allUserTypes == null) {
			return null;
		} else {
			return allUserTypes;
		}
	}

	public boolean deleteUserType(UserTypes userType) {
		return userTypesDAO.delete(userType);
	}

	private boolean isValidUserType(UserTypes userType) {
		// TODO expand validity checking
		if (userType != null) {
			return true;
		}
		return false;

	}

}
