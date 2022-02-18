package com.revature.wedding_planner.services;

import com.revature.wedding_planner.exceptions.AuthenticationException;
import com.revature.wedding_planner.exceptions.InvalidRequestException;
import com.revature.wedding_planner.exceptions.ResourcePersistenceException;

import java.util.List;

import com.revature.wedding_planner.daos.UserDAO;
import com.revature.wedding_planner.models.User;

public class UserService {
	private final UserDAO userDAO;

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public boolean addUser(User newUser) {
		if (!isValidUser(newUser)) {
			throw new InvalidRequestException("Invalid user data provided");
		}

		// logic that verifies the new users information isn't duplicated in the system
		boolean usernameAvailable = userDAO.findByUsername(newUser.getUsername()) == null;
		boolean emailAvailable = userDAO.findByEmail(newUser.getEmail()) == null;

		if (!usernameAvailable || !emailAvailable) {
			if (emailAvailable) {
				throw new ResourcePersistenceException("The provided username was already taken in the database");
			} else if (usernameAvailable) {
				throw new ResourcePersistenceException("The provided email was already taken in the database");
			} else {
				throw new ResourcePersistenceException(
						"The provided username and email were already taken in the database");
			}
		}

		User persistedUser = userDAO.create(newUser);

		if (persistedUser == null) {
			throw new ResourcePersistenceException("The user could not be persisted");
		}

		return true;
	}

	public User authenticateUser(String username, String password) {
		if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
			throw new InvalidRequestException(
					"Either username or password is an invalid entry. Please try logging in again.");
		}

		User authenticatedUser = userDAO.findByUsername(username);

		if (authenticatedUser == null) {
			throw new AuthenticationException(
					"Unauthenticated user, information provided was not found in our database.");
		}

		if (!authenticatedUser.getPassword().equals(password)) {
			return null;
		} else {
			return authenticatedUser;
		}
	}

	public boolean isValidUser(User user) {
		// TODO check if each field is valid depending on the user type
		if (user != null) {
			return true;
		}
		return false;
	}

	public void logout() {
		// TODO or not to do?
	}

	public User updateUser(User updatedUser) {
		if (!userDAO.update(updatedUser)) {
			throw new ResourcePersistenceException("Failure updating user.");
		}
		return updatedUser;
	}

	public User getUserByID(String userId) {

		User foundUser = userDAO.findById(userId);

		if (foundUser != null) {
			return foundUser;
		} else {
			return null;
		}
	}

	public List<User> getAllUsers() {
		List<User> allUsers = userDAO.findAll();
		
		if (allUsers == null) {
			return null;
		} else {
			return allUsers;
		}
	}
	
	public void deleteUser(User user) {
		userDAO.delete(user.getId());
	}
}
