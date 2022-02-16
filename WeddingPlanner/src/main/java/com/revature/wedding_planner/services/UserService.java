package com.revature.wedding_planner.services;

import com.revature.wedding_planner.exceptions.AuthenticationException;
import com.revature.wedding_planner.exceptions.InvalidRequestException;
import com.revature.wedding_planner.exceptions.ResourcePersistenceException;
import com.revature.wedding_planner.daos.UserDAO;
import com.revature.wedding_planner.models.User;

public class UserService {
	private final UserDAO userDAO;

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User registerNewUser(User newUser) {
		if (!isValidUser(newUser)) {
			throw new InvalidRequestException("Invalid user data provider");
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
				throw new ResourcePersistenceException(	"The provided username and email were already taken in the database");
			}
		}

		User persistedUser = userDAO.create(newUser);

		if (persistedUser == null) {
			throw new ResourcePersistenceException("The user could not be persisted");
		}

		return persistedUser;
	}

	public User authenticateUser(String username, String password) {
		if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
			throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again.");
		}

		User authenticatedUser = userDAO.findByUsername(username);

		if (authenticatedUser == null) {
			throw new AuthenticationException("Unauthenticated user, information provided was not found in our database.");
		}

		if (!authenticatedUser.getPassword().equals(password)) {
			return null;
		} else {
			return authenticatedUser;
		}
	}

	public boolean isValidUser(User user) {
		// TODO check if each field is valid depending on the user type

		return false;
	}
}
