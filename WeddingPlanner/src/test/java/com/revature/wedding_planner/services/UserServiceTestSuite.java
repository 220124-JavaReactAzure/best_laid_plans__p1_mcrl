package com.revature.wedding_planner.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.wedding_planner.daos.UserDAO;
import com.revature.wedding_planner.models.User;

public class UserServiceTestSuite {
	UserService sut;
	UserDAO mockUserDAO;

	@Before
	public void testPrep() {
		mockUserDAO = mock(UserDAO.class);
		sut = new UserService(mockUserDAO);
	}

	@Test
	public void test_isUserValid_returnsTrue_givenValidUser() {
		//TODO
		User validUser = new User();
		
		boolean actualResult = sut.isValidUser(validUser);
	}
}
