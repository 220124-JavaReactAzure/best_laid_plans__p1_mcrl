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
import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.models.UserType;
import com.revature.wedding_planner.services.UserTypeService;

public class UserTypeServlet extends HttpServlet {

	private final UserTypeService userTypeService;
	private final ObjectMapper mapper;

	public UserTypeServlet(UserTypeService userTypeService, ObjectMapper mapper) {
		super();
		this.userTypeService = userTypeService;
		this.mapper = mapper;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		String path = req.getPathInfo();
		if (path == null)
			path = "";
		switch (path) {
		// TODO
		case "/attendees":
			try {
				List<User> users = userTypeService.getAllUsersByType("attendee");
				if (users == null) {
					writer.write("Retrieval by userType not fully implemented yet");
					resp.setStatus(500);
					return;
				}
				String payload = mapper.writeValueAsString(users);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}

			writer.write("All attendees");
			resp.setStatus(200);
			break;
		// TODO
		case "/betrothed":
			try {
				List<User> users = userTypeService.getAllUsersByType("betrothed");
				if (users == null) {
					writer.write("Retrieval by userType not fully implemented yet");
					resp.setStatus(500);
					return;
				}
				String payload = mapper.writeValueAsString(users);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}

			writer.write("All betrothed");
			resp.setStatus(200);
			break;
		// TODO
		case "/staff":
			try {

				List<User> users = userTypeService.getAllUsersByType("staff");
				if (users == null) {
					writer.write("Retrieval by userType not fully implemented yet");
					resp.setStatus(500);
					return;
				}
				String payload = mapper.writeValueAsString(users);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}

			writer.write("All staff");
			resp.setStatus(200);
			break;
		default:
			List<UserType> userTypes = userTypeService.getAllUserTypes();
			String payload = "";
			for (UserType userType: userTypes) {
				payload += mapper.writeValueAsString(userType.getUserType()) + "\n";
			}
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			UserType newUserType = mapper.readValue(req.getInputStream(), UserType.class);
			boolean wasReg = userTypeService.addUserType(newUserType);
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
			userTypeService.updateUserType(updatedUserType);
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
			userTypeService.deleteUserType(deletedUserType);
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
