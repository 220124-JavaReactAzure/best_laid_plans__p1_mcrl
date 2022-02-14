package com.revature.wedding_planner.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.revature.wedding_planner.exceptions.AuthenticationException;
import com.revature.wedding_planner.exceptions.InvalidRequestException;
import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.services.UserService;
import com.revature.wedding_planner.web.dto.LoginCredentials;

// @WebServlet, why don't we do this? Because we are going to define a constructor. 
public class AuthServlet extends HttpServlet{

	private final UserService userService;
	private final ObjectMapper mapper;
	
	public AuthServlet(UserService userService, ObjectMapper mapper) {
		this.userService = userService;
		this.mapper = mapper;
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		try {
			LoginCredentials loginCreds = mapper.readValue(req.getInputStream(), LoginCredentials.class);
			User authenticatedUser = userService.authenticateUser(loginCreds.getUsername(), loginCreds.getPassword());
			HttpSession httpSession = req.getSession(true);
			httpSession.setAttribute("authUser", authenticatedUser);
		} catch (InvalidRequestException | UnrecognizedPropertyException e) {
			// TODO: handle exception
			resp.setStatus(400);
		} catch (AuthenticationException e) {
			// TODO: handle exception
			resp.setStatus(401);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resp.setStatus(500);
		}
		
	}
	
}