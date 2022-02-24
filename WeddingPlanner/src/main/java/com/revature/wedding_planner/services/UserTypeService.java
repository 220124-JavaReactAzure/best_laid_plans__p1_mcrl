package com.revature.wedding_planner.services;

import java.util.List;

import com.revature.wedding_planner.daos.UserTypeDAO;
import com.revature.wedding_planner.exceptions.InvalidRequestException;
import com.revature.wedding_planner.exceptions.ResourcePersistenceException;
import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.models.UserType;

public class UserTypeService {

	private final UserTypeDAO userTypesDAO;

	public UserTypeService(UserTypeDAO userTypesDAO) {
		this.userTypesDAO = userTypesDAO;
	}

	public boolean addUserType(UserType userType) {
		if (!isValidUserType(userType)) {
			throw new InvalidRequestException("Invalid userType data provided");
		}

		UserType persistedUserType = userTypesDAO.create(userType);

		if (persistedUserType == null) {
			throw new ResourcePersistenceException("The userType could not be persisted");
		}

		return true;
	}

	public UserType updateUserType(UserType updatedUserType) {
		if (!isValidUserType(updatedUserType)) {
			throw new InvalidRequestException("Invalid userType data provided");
		}
		if (!userTypesDAO.update(updatedUserType)) {
			throw new ResourcePersistenceException("Failure updating userType.");
		}
		return updatedUserType;
	}

	public UserType getUserTypeByID(int userTypeId) {

		UserType foundUserType = userTypesDAO.findById(userTypeId);

		if (foundUserType != null) {
			return foundUserType;
		} else {
			return null;
		}
	}

	public List<UserType> getAllUserTypes() {
		List<UserType> allUserTypes = userTypesDAO.findAll();

		if (allUserTypes == null) {
			return null;
		} else {
			return allUserTypes;
		}
	}

	public boolean deleteUserType(UserType userType) {
		return userTypesDAO.delete(userType);
	}

	public boolean isValidUserType(UserType userType) {
		// TODO expand validity checking
		if (userType != null) {
			return true;
		}
		return false;

	}

	public List<User> getAllUsersByType(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
