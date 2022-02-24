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

import com.revature.wedding_planner.daos.WeddingDAO;
import com.revature.wedding_planner.exceptions.InvalidRequestException;
import com.revature.wedding_planner.exceptions.ResourcePersistenceException;
import com.revature.wedding_planner.models.Wedding;

public class WeddingServiceTestSuite {
	WeddingService sut;
	WeddingDAO mockWeddingDAO;

	@Before
	public void testPrep() {
		mockWeddingDAO = mock(WeddingDAO.class);
		sut = new WeddingService(mockWeddingDAO);
	}

// add
	@Test
	public void test_addWedding_returnsTrue_givenValidWedding() {
		Wedding validWedding = new Wedding("validDate", "valid");

		when(mockWeddingDAO.create(validWedding)).thenReturn(validWedding);

		boolean actualResult = sut.addWedding(validWedding);

		Assert.assertTrue(actualResult);
		verify(mockWeddingDAO, times(1)).create(validWedding);
	}

	@Test(expected = InvalidRequestException.class)
	public void test_addWedding_throwsInvalidRequestException_givenInvalidWedding() {
		sut.addWedding(null);
	}

	@Test(expected = ResourcePersistenceException.class)
	public void test_addWedding_throwsResourcePersistenceException_givenDuplicateWedding() {
		Wedding duplicateWedding = new Wedding("valid", "valid");

		when(mockWeddingDAO.create(duplicateWedding)).thenReturn(null);

		boolean actualResult = sut.addWedding(duplicateWedding);

		Assert.assertFalse(actualResult);
		verify(mockWeddingDAO, times(1)).create(duplicateWedding);

	}

// update
	@Test
	public void test_updateWedding_returnTrue_givenValidWedding() {
		Wedding updatedWedding = new Wedding("valid", "valid");
		when(mockWeddingDAO.update(updatedWedding)).thenReturn(true);

		Wedding actualResult = sut.updateWedding(updatedWedding);

		Assert.assertNotNull(actualResult);
		verify(mockWeddingDAO, times(1)).update(updatedWedding);
	}

	@Test(expected = InvalidRequestException.class)
	public void test_updateWedding_throwsInvalidRequestException_givenInvalidWedding() {
		sut.updateWedding(null);
	}

	@Test(expected = ResourcePersistenceException.class)
	public void test_updateWedding_throwsResourcePersistenceException_givenInvalidWedding() {
		Wedding invalidWedding = new Wedding();
		when(mockWeddingDAO.update(invalidWedding)).thenReturn(false);

		Wedding actualResult = sut.updateWedding(invalidWedding);

		Assert.assertNotNull(actualResult);
		verify(mockWeddingDAO, times(1)).update(invalidWedding);
	}

// getByID
	@Test
	public void test_getWeddingByID_returnsNotNull_givenValidId() {
		Wedding foundWedding = new Wedding("valid", "valid");
		when(mockWeddingDAO.findById(foundWedding.getId())).thenReturn(foundWedding);

		Wedding actualResult = sut.getWeddingByID(foundWedding.getId());

		Assert.assertNotNull(actualResult);
		verify(mockWeddingDAO, times(1)).findById(foundWedding.getId());
	}

	@Test
	public void test_getWeddingByID_returnNull_givenInvalidId() {
		Wedding foundWedding = new Wedding(0, "valid", "valid");

		when(mockWeddingDAO.findById(foundWedding.getId())).thenReturn(null);

		Wedding actualResult = sut.getWeddingByID(foundWedding.getId());

		Assert.assertNull(actualResult);
		verify(mockWeddingDAO, times(1)).findById(foundWedding.getId());
	}

// getAll
	@Test
	public void test_getAllWeddings_returnsNotNull_givenValidWeddingCollection() {
		List<Wedding> allWeddings = new ArrayList<>();

		when(mockWeddingDAO.findAll()).thenReturn(allWeddings);

		List<Wedding> actualResult = sut.getAllWeddings();

		Assert.assertNotNull(actualResult);
		verify(mockWeddingDAO, times(1)).findAll();
	}

	@Test
	public void test_getAllWeddings_returnNull_givenInvalidWeddingCollection() {
		when(mockWeddingDAO.findAll()).thenReturn(null);

		List<Wedding> actualResult = sut.getAllWeddings();

		Assert.assertNull(actualResult);
		verify(mockWeddingDAO, times(1)).findAll();
	}

// delete
	@Test
	public void test_deleteWedding_returnsTrue_givenValidWedding() {
		Wedding validWedding = new Wedding("valid", "valid");

		when(mockWeddingDAO.delete(validWedding)).thenReturn(true);

		boolean actualResult = sut.deleteWedding(validWedding);

		Assert.assertTrue(actualResult);
		verify(mockWeddingDAO, times(1)).delete(validWedding);
	}

	@Test
	public void test_deleteWedding_returnsFalse_givenInvalidWedding() {
		Wedding invalidWedding = null;

		when(mockWeddingDAO.delete(invalidWedding)).thenReturn(false);

		boolean actualResult = sut.deleteWedding(invalidWedding);

		Assert.assertFalse(actualResult);
		verify(mockWeddingDAO, times(1)).delete(invalidWedding);
	}

// isValid
	@Test
	public void test_isWedding_returnsTrue_givenValidWedding() {
		Wedding validWedding = new Wedding("valid", "valid");

		boolean actualResult = sut.isValidWedding(validWedding);

		Assert.assertTrue(actualResult);
	}

	@Test
	public void test_isWeddingValid_returnsFalse_givenInvalidWedding() {
		Wedding invalidWedding = null;

		boolean actualResult = sut.isValidWedding(invalidWedding);

		Assert.assertFalse(actualResult);
	}
}
