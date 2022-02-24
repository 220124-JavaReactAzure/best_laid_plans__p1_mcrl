package com.revature.wedding_planner.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.wedding_planner.daos.UserDAO;
import com.revature.wedding_planner.exceptions.InvalidRequestException;
import com.revature.wedding_planner.exceptions.ResourcePersistenceException;
import com.revature.wedding_planner.models.User;

public class UserServiceTestSuite {
	UserService sut;
	UserDAO mockUserDAO;

	@Before
	public void testPrep() {
		mockUserDAO = mock(UserDAO.class);
		sut = new UserService(mockUserDAO);
	}

// add
	@Test
	public void test_addUser_returnsTrue_givenValidUser() {
		User validUser = new User();

		when(mockUserDAO.create(validUser)).thenReturn(validUser);

		boolean actualResult = sut.addUser(validUser);

		Assert.assertTrue(actualResult);
		verify(mockUserDAO, times(1)).create(validUser);
	}

	@Test(expected = InvalidRequestException.class)
	public void test_addUser_throwsInvalidRequestException_givenInvalidUser() {
		sut.addUser(null);
	}

	@Test(expected = ResourcePersistenceException.class)
	public void test_addUser_throwsResourcePersistenceException_givenDuplicateUser() {
		User duplicateUser = new User();

		when(mockUserDAO.create(duplicateUser)).thenReturn(null);

		boolean actualResult = sut.addUser(duplicateUser);

		Assert.assertFalse(actualResult);
		verify(mockUserDAO, times(1)).create(duplicateUser);

	}

// update
	@Test
	public void test_updateUser_returnTrue_givenValidUser() {
		User updatedUser = new User();
		when(mockUserDAO.update(updatedUser)).thenReturn(true);

		User actualResult = sut.updateUser(updatedUser);

		Assert.assertNotNull(actualResult);
		verify(mockUserDAO, times(1)).update(updatedUser);
	}

	@Test(expected = InvalidRequestException.class)
	public void test_updateUser_throwsInvalidRequestException_givenInvalidUser() {
		sut.updateUser(null);
	}

	@Test(expected = ResourcePersistenceException.class)
	public void test_updateUser_throwsResourcePersistenceException_givenInvalidUser() {
		User invalidUser = new User();
		when(mockUserDAO.update(invalidUser)).thenReturn(false);

		User actualResult = sut.updateUser(invalidUser);

		Assert.assertNotNull(actualResult);
		verify(mockUserDAO, times(1)).update(invalidUser);
	}

// getByID
	@Test
	public void test_getUserByID_returnsNotNull_givenValidId() {
		User foundUser = new User();
		when(mockUserDAO.findById(foundUser.getId())).thenReturn(foundUser);

		User actualResult = sut.getUserByID(foundUser.getId());

		Assert.assertNotNull(actualResult);
		verify(mockUserDAO, times(1)).findById(foundUser.getId());
	}

	@Test
	public void test_getUserByID_returnNull_givenInvalidId() {
		User foundUser = new User();

		when(mockUserDAO.findById(foundUser.getId())).thenReturn(null);

		User actualResult = sut.getUserByID(foundUser.getId());

		Assert.assertNull(actualResult);
		verify(mockUserDAO, times(1)).findById(foundUser.getId());
	}

// getAll
	@Test
	public void test_getAllUser_returnsNotNull_givenValidUserCollection() {
		List<User> allUsers = new ArrayList<>();

		when(mockUserDAO.findAll()).thenReturn(allUsers);

		List<User> actualResult = sut.getAllUsers();

		Assert.assertNotNull(actualResult);
		verify(mockUserDAO, times(1)).findAll();
	}

	@Test
	public void test_getAllUsers_returnNull_givenInvalidUserCollection() {
		when(mockUserDAO.findAll()).thenReturn(null);

		List<User> actualResult = sut.getAllUsers();

		Assert.assertNull(actualResult);
		verify(mockUserDAO, times(1)).findAll();
	}

// delete
	@Test
	public void test_deleteUser_returnsTrue_givenValidUser() {
		User validUser = new User();

		when(mockUserDAO.delete(validUser)).thenReturn(true);

		boolean actualResult = sut.deleteUser(validUser);

		Assert.assertTrue(actualResult);
		verify(mockUserDAO, times(1)).delete(validUser);
	}

	@Test
	public void test_deleteUser_returnsFalse_givenInvalidUser() {
		User invalidUser = null;

		when(mockUserDAO.delete(invalidUser)).thenReturn(false);

		boolean actualResult = sut.deleteUser(invalidUser);

		Assert.assertFalse(actualResult);
		verify(mockUserDAO, times(1)).delete(invalidUser);
	}

// isValid
	@Test
	public void test_isUser_returnsTrue_givenValidUser() {
		User validUser = new User();

		boolean actualResult = sut.isValidUser(validUser);

		Assert.assertTrue(actualResult);
	}

	@Test
	public void test_isUserValid_returnsFalse_givenInvalidUser() {
		User invalidUser = null;

		boolean actualResult = sut.isValidUser(invalidUser);

		Assert.assertFalse(actualResult);
	}
}
