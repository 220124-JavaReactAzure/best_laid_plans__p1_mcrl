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
import com.revature.wedding_planner.models.MealTypes;
import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.services.MealTypesService;

public class MealTypesServlet extends HttpServlet {

	private final MealTypesService mealTypesService;
	private final ObjectMapper mapper;

	public MealTypesServlet(MealTypesService mealTypesService, ObjectMapper mapper) {
		super();
		this.mealTypesService = mealTypesService;
		this.mapper = mapper;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();

		try {
			List<MealTypes> mealTypes = mealTypesService.getAllMealTypes();
			String payload = mapper.writeValueAsString(mealTypes);
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
			MealTypes newMealType = mapper.readValue(req.getInputStream(), MealTypes.class);
			boolean wasReg = mealTypesService.addMealType(newMealType);
			if (wasReg) {
				resp.setStatus(201);
			} else {
				resp.setStatus(500);
				resp.getWriter().write("Database did not persist new mealType.");
			}
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception.");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist mealType.");
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			MealTypes updatedMealType = mapper.readValue(req.getInputStream(), MealTypes.class);
			mealTypesService.updateMealType(updatedMealType);
			resp.setStatus(204);
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist mealType update.");
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			MealTypes deletedMealType = mapper.readValue(req.getInputStream(), MealTypes.class);
			mealTypesService.deleteMealType(deletedMealType);
			resp.setStatus(204);
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist mealType deletion.");
			e.printStackTrace();
		}
	}

}
