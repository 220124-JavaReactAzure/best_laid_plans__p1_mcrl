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

import com.revature.wedding_planner.daos.UserTypeDAO;
import com.revature.wedding_planner.exceptions.InvalidRequestException;
import com.revature.wedding_planner.exceptions.ResourcePersistenceException;
import com.revature.wedding_planner.models.UserType;

public class UserTypeServiceTestSuite {
	UserTypeService sut;
	UserTypeDAO mocUserTypeDAO;

	@Before
	public void testPrep() {
		mocUserTypeDAO = mock(UserTypeDAO.class);
		sut = new UserTypeService(mocUserTypeDAO);
	}

// add
	@Test
	public void test_addUserType_returnsTrue_givenValidUserType() {
		UserType validUserType = new UserType("valid");

		when(mocUserTypeDAO.create(validUserType)).thenReturn(validUserType);

		boolean actualResult = sut.addUserType(validUserType);

		Assert.assertTrue(actualResult);
		verify(mocUserTypeDAO, times(1)).create(validUserType);
	}

	@Test(expected = InvalidRequestException.class)
	public void test_addUserType_throwsInvalidRequestException_givenInvalidUserType() {
		sut.addUserType(null);
	}

	@Test(expected = ResourcePersistenceException.class)
	public void test_addUserType_throwsResourcePersistenceException_givenDuplicateUserType() {
		UserType duplicateUserType = new UserType("valid");

		when(mocUserTypeDAO.create(duplicateUserType)).thenReturn(null);

		boolean actualResult = sut.addUserType(duplicateUserType);

		Assert.assertFalse(actualResult);
		verify(mocUserTypeDAO, times(1)).create(duplicateUserType);

	}

// update
	@Test
	public void test_updateUserType_returnTrue_givenValidUserType() {
		UserType updatedUserType = new UserType("valid");
		when(mocUserTypeDAO.update(updatedUserType)).thenReturn(true);

		UserType actualResult = sut.updateUserType(updatedUserType);

		Assert.assertNotNull(actualResult);
		verify(mocUserTypeDAO, times(1)).update(updatedUserType);
	}

	@Test(expected = InvalidRequestException.class)
	public void test_updateUserType_throwsInvalidRequestException_givenInvalidUserType() {
		sut.updateUserType(null);
	}

	@Test(expected = ResourcePersistenceException.class)
	public void test_updateUserType_throwsResourcePersistenceException_givenInvalidUserType() {
		UserType invalidUserType = new UserType();
		when(mocUserTypeDAO.update(invalidUserType)).thenReturn(false);

		UserType actualResult = sut.updateUserType(invalidUserType);

		Assert.assertNotNull(actualResult);
		verify(mocUserTypeDAO, times(1)).update(invalidUserType);
	}

// getByID
	@Test
	public void test_getUserTypeByID_returnsNotNull_givenValidId() {
		UserType foundUserType = new UserType("valid");
		when(mocUserTypeDAO.findById(foundUserType.getId())).thenReturn(foundUserType);

		UserType actualResult = sut.getUserTypeByID(foundUserType.getId());

		Assert.assertNotNull(actualResult);
		verify(mocUserTypeDAO, times(1)).findById(foundUserType.getId());
	}

	@Test
	public void test_getUserTypeByID_returnNull_givenInvalidId() {
		UserType foundUserType = new UserType();

		when(mocUserTypeDAO.findById(foundUserType.getId())).thenReturn(null);

		UserType actualResult = sut.getUserTypeByID(foundUserType.getId());

		Assert.assertNull(actualResult);
		verify(mocUserTypeDAO, times(1)).findById(foundUserType.getId());
	}

// getAll
	@Test
	public void test_getAllUserType_returnsNotNull_givenValidUserTypeCollection() {
		List<UserType> allUserTypes = new ArrayList<>();

		when(mocUserTypeDAO.findAll()).thenReturn(allUserTypes);

		List<UserType> actualResult = sut.getAllUserTypes();

		Assert.assertNotNull(actualResult);
		verify(mocUserTypeDAO, times(1)).findAll();
	}

	@Test
	public void test_getAllUserTypes_returnNull_givenInvalidUserTypeCollection() {
		when(mocUserTypeDAO.findAll()).thenReturn(null);

		List<UserType> actualResult = sut.getAllUserTypes();

		Assert.assertNull(actualResult);
		verify(mocUserTypeDAO, times(1)).findAll();
	}

// delete
	@Test
	public void test_deleteUserType_returnsTrue_givenValidUserType() {
		UserType validUserType = new UserType("valid");

		when(mocUserTypeDAO.delete(validUserType)).thenReturn(true);

		boolean actualResult = sut.deleteUserType(validUserType);

		Assert.assertTrue(actualResult);
		verify(mocUserTypeDAO, times(1)).delete(validUserType);
	}

	@Test
	public void test_deleteUserType_returnsFalse_givenInvalidUserType() {
		UserType invalidUserType = null;

		when(mocUserTypeDAO.delete(invalidUserType)).thenReturn(false);

		boolean actualResult = sut.deleteUserType(invalidUserType);

		Assert.assertFalse(actualResult);
		verify(mocUserTypeDAO, times(1)).delete(invalidUserType);
	}

// isValid
	@Test
	public void test_isUserType_returnsTrue_givenValidUserType() {
		UserType validUserType = new UserType("valid");

		boolean actualResult = sut.isValidUserType(validUserType);

		Assert.assertTrue(actualResult);
	}

	@Test
	public void test_isUserTypeValid_returnsFalse_givenInvalidUserType() {
		UserType invalidUserType = null;

		boolean actualResult = sut.isValidUserType(invalidUserType);

		Assert.assertFalse(actualResult);
	}
}
