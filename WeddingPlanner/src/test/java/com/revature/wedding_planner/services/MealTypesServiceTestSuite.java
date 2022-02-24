package com.revature.wedding_planner.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;


import com.revature.wedding_planner.daos.MealTypeDAO;
import com.revature.wedding_planner.models.MealType;
import com.revature.wedding_planner.exceptions.InvalidRequestException;
import com.revature.wedding_planner.exceptions.ResourcePersistenceException;

public class MealTypesServiceTestSuite {
	MealTypeService sut;
	MealTypeDAO mockMealTypesDAO;

	@Before
	public void testPrep() {
		mockMealTypesDAO = mock(MealTypeDAO.class);
		sut = new MealTypeService(mockMealTypesDAO);
	}

// add
	@Test
	public void test_addMealType_returnsTrue_givenValidMealType() {
		MealType validMealType = new MealType("valid");
		
		when(mockMealTypesDAO.findByMealType(validMealType.getMealType())).thenReturn(null);
		when(mockMealTypesDAO.create(validMealType)).thenReturn(validMealType);

		boolean actualResult = sut.addMealType(validMealType);

		Assert.assertTrue(actualResult);
		verify(mockMealTypesDAO, times(1)).create(validMealType);
	}

	@Test(expected = InvalidRequestException.class)
	public void test_addMealType_throwsInvalidRequestException_givenInvalidMealType() {
		sut.addMealType(null);
	}
	@Test(expected = ResourcePersistenceException.class)
	public void test_addMealType_throwsResourcePersistenceException_givenDuplicateMealType() {
		MealType duplicateMealType = new MealType("valid");

		when(mockMealTypesDAO.create(duplicateMealType)).thenReturn(null);

		boolean actualResult = sut.addMealType(duplicateMealType);

		Assert.assertFalse(actualResult);
		verify(mockMealTypesDAO, times(1)).create(duplicateMealType);

	}

// update
	@Test
	public void test_updateMealType_returnTrue_givenValidMealType() {
		MealType updatedMealType = new MealType("valid");
		when(mockMealTypesDAO.update(updatedMealType)).thenReturn(true);

		MealType actualResult = sut.updateMealType(updatedMealType);

		Assert.assertNotNull(actualResult);
		verify(mockMealTypesDAO, times(1)).update(updatedMealType);
	}
	@Test(expected = InvalidRequestException.class)
	public void test_updateMealType_throwsInvalidRequestException_givenInvalidMealType() {
		sut.updateMealType(null);
	}
	@Test(expected = ResourcePersistenceException.class)
	public void test_updateMealType_throwsResourcePersistenceException_givenInvalidMealType() {
		MealType invalidMealType = new MealType("invalid");
		when(mockMealTypesDAO.update(invalidMealType)).thenReturn(false);
		
		MealType actualResult = sut.updateMealType(invalidMealType);
		
		Assert.assertNotNull(actualResult);
		verify(mockMealTypesDAO, times(1)).update(invalidMealType);
	}

// getByID
	@Test
	public void test_getMealTypeByID_returnsNotNull_givenValidId() {
		MealType foundMealType = new MealType(1, "valid");
		when(mockMealTypesDAO.findById(foundMealType.getId())).thenReturn(foundMealType);

		MealType actualResult = sut.getMealTypeByID(foundMealType.getId());

		Assert.assertNotNull(actualResult);
		verify(mockMealTypesDAO, times(1)).findById(foundMealType.getId());
	}
	@Test
	public void test_getMealTypeByID_returnNull_givenInvalidId() {
		MealType foundMealType = new MealType(0, "invalid");

		when(mockMealTypesDAO.findById(foundMealType.getId())).thenReturn(null);

		MealType actualResult = sut.getMealTypeByID(foundMealType.getId());

		Assert.assertNull(actualResult);
		verify(mockMealTypesDAO, times(1)).findById(foundMealType.getId());
	}

// getAll
	@Test
	public void test_getAllMealTypes_returnsNotNull_givenValidMealTypeCollection() {
		List<MealType> allMealTypes = new ArrayList<>();

		when(mockMealTypesDAO.findAll()).thenReturn(allMealTypes);

		List<MealType> actualResult = sut.getAllMealTypes();

		Assert.assertNotNull(actualResult);
		verify(mockMealTypesDAO, times(1)).findAll();
	}
	@Test
	public void test_getAllMealTypes_returnNull_givenInvalidMealTypeCollection() {
		when(mockMealTypesDAO.findAll()).thenReturn(null);

		List<MealType> actualResult = sut.getAllMealTypes();

		Assert.assertNull(actualResult);
		verify(mockMealTypesDAO, times(1)).findAll();
	}

// delete
	@Test
	public void test_deleteMealType_returnsTrue_givenValidMealType() {
		MealType validMealType = new MealType("valid");

		when(mockMealTypesDAO.delete(validMealType)).thenReturn(true);

		boolean actualResult = sut.deleteMealType(validMealType);

		Assert.assertTrue(actualResult);
		verify(mockMealTypesDAO, times(1)).delete(validMealType);
	}
	@Test
	public void test_deleteMealType_returnsFalse_givenInvalidMealType() {
		MealType invalidMealType = null;

		when(mockMealTypesDAO.delete(invalidMealType)).thenReturn(false);

		boolean actualResult = sut.deleteMealType(invalidMealType);

		Assert.assertFalse(actualResult);
		verify(mockMealTypesDAO, times(1)).delete(invalidMealType);
	}

// isValid
	@Test
	public void test_isMealTypeValid_returnsTrue_givenValidMealType() {
		MealType validMealType = new MealType("valid");

		boolean actualResult = sut.isValidMealType(validMealType);

		Assert.assertTrue(actualResult);
	}

	@Test
	public void test_isMealTypeValid_returnsFalse_givenInvalidMealType() {
		MealType invalidMealType = null;

		boolean actualResult = sut.isValidMealType(invalidMealType);

		Assert.assertFalse(actualResult);
	}
}
