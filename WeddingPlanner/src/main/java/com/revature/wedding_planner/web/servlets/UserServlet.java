package com.revature.wedding_planner.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.wedding_planner.models.MealType;
import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.services.UserService;

public class UserServlet extends HttpServlet {
	private final UserService userService;
	private final ObjectMapper mapper;

	public UserServlet(UserService userService, ObjectMapper mapper) {
		super();
		this.userService = userService;
		this.mapper = mapper;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		String path = req.getPathInfo();
		if (path == null)
			path = "";
		switch (path) {
		case "/user":
			try {
				String idParam = req.getParameter("username");
				if (idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?username=# in your url");
					return;
				}
				//changed to int type to support change to serial generated int id type
				int userId = Integer.parseInt(idParam);

				User user = userService.getUserByID(userId);
				if (user == null) {
					resp.setStatus(500);
					return;
				}
				String payload = mapper.writeValueAsString(user);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;
		default:
			List<User> users = userService.getAllUsers();
			String payload = "";
			for (User user: users) {
				payload += mapper.writeValueAsString(user.getName());
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
			User newUser = mapper.readValue(req.getInputStream(), User.class);
			boolean wasReg = userService.addUser(newUser);
			if (wasReg) {
				resp.setStatus(201);
			} else {
				resp.setStatus(500);
				resp.getWriter().write("Database did not persist user.");
			}
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception.");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
//			logger.log(Level.FINEST, "Exception thrown while creating user", e);
			resp.getWriter().write("Some other random exception--did not persist user.");
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			User updatedUser = mapper.readValue(req.getInputStream(), User.class);
			userService.updateUser(updatedUser);
			resp.setStatus(204);
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception.");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist user update.");
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//add parameter options for delete by Id, currently only works with fully formed objects
			User deletedUser = mapper.readValue(req.getInputStream(), User.class);
			userService.deleteUser(deletedUser);
			resp.setStatus(204);
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception.");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist user deletion.");
			e.printStackTrace();
		}
	}
}
