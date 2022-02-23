package com.revature.wedding_planner.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
		// TODO
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
		// TODO
		MealTypes duplicateMealType = new MealTypes("valid");

		when(mockMealTypesDAO.findByMealType(duplicateMealType.getMealType())).thenReturn(duplicateMealType);
		when(mockMealTypesDAO.create(duplicateMealType)).thenReturn(duplicateMealType);

		boolean actualResult = sut.addMealType(duplicateMealType);

		Assert.assertTrue(actualResult);
		verify(mockMealTypesDAO, times(1)).create(duplicateMealType);

	}

	// update
	@Test
	public void test_updateMealType_returnsTrue_givenValidMealType() {
		// TODO
		MealTypes validMealType = new MealTypes("beef");

		MealTypes actualResult = sut.updateMealType(validMealType);

		Assert.assertNotNull(actualResult);
	}

	@Test
	public void test_updateMealType_returnFalse_givenInvalidMealType() {
		// TODO
		MealTypes invalidMealType = null;

		MealTypes actualResult = sut.updateMealType(invalidMealType);

		Assert.assertNull(actualResult);
	}

	// getByID
	@Test
	public void test_getMealTypeByID_returnsNotNull_givenValidId() {
		// TODO

		MealTypes actualResult = sut.getMealTypeByID(1);

		Assert.assertNotNull(actualResult);
	}

	@Test
	public void test_getMealTypeByID_returnNull_givenInvalidId() {
		// TODO

		MealTypes actualResult = sut.getMealTypeByID(0);

		Assert.assertNull(actualResult);
	}

	// getAll
	@Test
	public void test_getAllMealTypes_returnsNotNull_givenValidMealTypeCollection() {
		// TODO
		List<MealTypes> actualResult = sut.getAllMealTypes();

		Assert.assertNotNull(actualResult);
	}

	@Test
	public void test_getAllMealTypes_returnNull_givenInvalidMealTypeCollection() {
		// TODO

		List<MealTypes> actualResult = sut.getAllMealTypes();

		Assert.assertNull(actualResult);
	}

	// delete
	@Test
	public void test_deleteMealType_returnsTrue_givenValidMealType() {

		MealTypes validMealType = new MealTypes("beef");

		boolean actualResult = sut.deleteMealType(validMealType);

		Assert.assertTrue(actualResult);
	}

	@Test
	public void test_deleteMealType_returnsFalse_givenInvalidMealType() {

		MealTypes invalidMealType = null;

		boolean actualResult = sut.deleteMealType(invalidMealType);

		Assert.assertFalse(actualResult);
	}

	// isValid
	@Test
	public void test_isMealTypeValid_returnsTrue_givenValidMealType() {

		MealTypes validMealType = new MealTypes("beef");

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
