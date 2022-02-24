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


import com.revature.wedding_planner.daos.MealTypesDAO;
import com.revature.wedding_planner.models.MealTypes;
import com.revature.wedding_planner.exceptions.InvalidRequestException;
import com.revature.wedding_planner.exceptions.ResourcePersistenceException;

public class MealTypesServiceTestSuite {
	MealTypesService sut;
	MealTypesDAO mockMealTypesDAO;

	@Before
	public void testPrep() {
		mockMealTypesDAO = mock(MealTypesDAO.class);
		sut = new MealTypesService(mockMealTypesDAO);
	}

// add
	@Test
	public void test_addMealType_returnsTrue_givenValidMealType() {
		MealTypes validMealType = new MealTypes("valid");
		
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
		MealTypes duplicateMealType = new MealTypes("valid");

		when(mockMealTypesDAO.create(duplicateMealType)).thenReturn(null);

		boolean actualResult = sut.addMealType(duplicateMealType);

		Assert.assertFalse(actualResult);
		verify(mockMealTypesDAO, times(1)).create(duplicateMealType);

	}

// update
	@Test
	public void test_updateMealType_returnTrue_givenValidMealType() {
		MealTypes updatedMealType = new MealTypes("valid");
		when(mockMealTypesDAO.update(updatedMealType)).thenReturn(true);

		MealTypes actualResult = sut.updateMealType(updatedMealType);

		Assert.assertNotNull(actualResult);
		verify(mockMealTypesDAO, times(1)).update(updatedMealType);
	}
	@Test(expected = InvalidRequestException.class)
	public void test_updateMealType_throwsInvalidRequestException_givenInvalidMealType() {
		sut.updateMealType(null);
	}
	@Test(expected = ResourcePersistenceException.class)
	public void test_updateMealType_throwsResourcePersistenceException_givenInvalidMealType() {
		MealTypes invalidMealType = new MealTypes("invalid");
		when(mockMealTypesDAO.update(invalidMealType)).thenReturn(false);
		
		MealTypes actualResult = sut.updateMealType(invalidMealType);
		
		Assert.assertNotNull(actualResult);
		verify(mockMealTypesDAO, times(1)).update(invalidMealType);
	}

// getByID
	@Test
	public void test_getMealTypeByID_returnsNotNull_givenValidId() {
		MealTypes foundMealType = new MealTypes(1, "valid");
		when(mockMealTypesDAO.findById(foundMealType.getId())).thenReturn(foundMealType);

		MealTypes actualResult = sut.getMealTypeByID(foundMealType.getId());

		Assert.assertNotNull(actualResult);
		verify(mockMealTypesDAO, times(1)).findById(foundMealType.getId());
	}
	@Test
	public void test_getMealTypeByID_returnNull_givenInvalidId() {
		MealTypes foundMealType = new MealTypes(0, "invalid");

		when(mockMealTypesDAO.findById(foundMealType.getId())).thenReturn(null);

		MealTypes actualResult = sut.getMealTypeByID(foundMealType.getId());

		Assert.assertNull(actualResult);
		verify(mockMealTypesDAO, times(1)).findById(foundMealType.getId());
	}

// getAll
	@Test
	public void test_getAllMealTypes_returnsNotNull_givenValidMealTypeCollection() {
		List<MealTypes> allMealTypes = new ArrayList<>();

		when(mockMealTypesDAO.findAll()).thenReturn(allMealTypes);

		List<MealTypes> actualResult = sut.getAllMealTypes();

		Assert.assertNotNull(actualResult);
		verify(mockMealTypesDAO, times(1)).findAll();
	}
	@Test
	public void test_getAllMealTypes_returnNull_givenInvalidMealTypeCollection() {
		when(mockMealTypesDAO.findAll()).thenReturn(null);

		List<MealTypes> actualResult = sut.getAllMealTypes();

		Assert.assertNull(actualResult);
		verify(mockMealTypesDAO, times(1)).findAll();
	}

// delete
	@Test
	public void test_deleteMealType_returnsTrue_givenValidMealType() {
		MealTypes validMealType = new MealTypes("valid");

		when(mockMealTypesDAO.delete(validMealType)).thenReturn(true);

		boolean actualResult = sut.deleteMealType(validMealType);

		Assert.assertTrue(actualResult);
		verify(mockMealTypesDAO, times(1)).delete(validMealType);
	}
	@Test
	public void test_deleteMealType_returnsFalse_givenInvalidMealType() {
		MealTypes invalidMealType = null;

		when(mockMealTypesDAO.delete(invalidMealType)).thenReturn(false);

		boolean actualResult = sut.deleteMealType(invalidMealType);

		Assert.assertFalse(actualResult);
		verify(mockMealTypesDAO, times(1)).delete(invalidMealType);
	}

// isValid
	@Test
	public void test_isMealTypeValid_returnsTrue_givenValidMealType() {
		MealTypes validMealType = new MealTypes("valid");

		boolean actualResult = sut.isValidMealType(validMealType);

		Assert.assertTrue(actualResult);
	}

	@Test
	public void test_isMealTypeValid_returnsFalse_givenInvalidMealType() {
		MealTypes invalidMealType = null;

		boolean actualResult = sut.isValidMealType(invalidMealType);

		Assert.assertFalse(actualResult);
	}
}