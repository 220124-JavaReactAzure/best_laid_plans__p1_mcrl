package com.revature.wedding_planner.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.wedding_planner.models.UserType;
import com.revature.wedding_planner.services.UserTypeService;

public class UserTypeServlet extends HttpServlet{

	private final UserTypeService userTypesService;
	private final ObjectMapper mapper;

	public UserTypeServlet(UserTypeService userTypesService, ObjectMapper mapper) {
		super();
		this.userTypesService = userTypesService;
		this.mapper = mapper;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();

		try {
			List<UserType> userTypes = userTypesService.getAllUserTypes();
			String payload = mapper.writeValueAsString(userTypes);
			writer.write(payload);
			resp.setStatus(200);
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			UserType newUserType = mapper.readValue(req.getInputStream(), UserType.class);
			boolean wasReg = userTypesService.addUserType(newUserType);
			if (wasReg) {
				resp.setStatus(201);
			} else {
				resp.setStatus(500);
				resp.getWriter().write("Database did not persist new userType.");
			}
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception.");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist userType.");
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			UserType updatedUserType = mapper.readValue(req.getInputStream(), UserType.class);
			userTypesService.updateUserType(updatedUserType);
			resp.setStatus(204);
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist userType update.");
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			UserType deletedUserType = mapper.readValue(req.getInputStream(), UserType.class);
			userTypesService.deleteUserType(deletedUserType);
			resp.setStatus(204);
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist userType deletion.");
			e.printStackTrace();
		}
	}
}
