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
		// Obtains everything after the /users
		String path = req.getPathInfo();
		if (path == null)
			path = "";
		switch (path) {
		case "/ID":
			try {
				String idParam = req.getParameter("userId");
				if (idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?userId=# in your url");
					return;
				}

				String userId = idParam;

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
			String payload = mapper.writeValueAsString(users);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}
}
