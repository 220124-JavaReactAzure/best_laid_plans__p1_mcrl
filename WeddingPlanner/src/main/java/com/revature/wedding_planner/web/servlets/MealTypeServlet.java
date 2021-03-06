package com.revature.wedding_planner.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.wedding_planner.models.MealType;
import com.revature.wedding_planner.services.MealTypeService;

public class MealTypeServlet extends HttpServlet {

	private final MealTypeService mealTypesService;
	private final ObjectMapper mapper;
	private static final Logger logger = LogManager.getLogger(MealTypeServlet.class);

	public MealTypeServlet(MealTypeService mealTypesService, ObjectMapper mapper) {
		super();
		this.mealTypesService = mealTypesService;
		this.mapper = mapper;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();

		try {
			List<MealType> mealTypes = mealTypesService.getAllMealTypes();
			String payload = "";
			for (MealType mealType: mealTypes) {
				payload += mapper.writeValueAsString(mealType) + "\n";
			}
			
			writer.write(payload);
			resp.setStatus(200);
		} catch (StreamReadException | DatabindException e) {
			logger.log(Level.ALL,"Exception thrown while retrieving mealType(s)", e);
			resp.setStatus(400);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			MealType newMealType = mapper.readValue(req.getInputStream(), MealType.class);
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
			logger.log(Level.DEBUG,"Exception thrown while persisting mealType(s)", e);
//			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist mealType.");
			logger.log(Level.ALL,"Exception thrown while persisting mealType(s)", e);
//			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			MealType updatedMealType = mapper.readValue(req.getInputStream(), MealType.class);
			mealTypesService.updateMealType(updatedMealType);
			resp.setStatus(204);
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist mealType update.");
			logger.log(Level.ALL,"Exception thrown while updating mealType(s)", e);
//			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			MealType deletedMealType = mapper.readValue(req.getInputStream(), MealType.class);
			mealTypesService.deleteMealType(deletedMealType);
			resp.setStatus(204);
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception");
			logger.log(Level.DEBUG,"Exception thrown while deleting mealType(s)", e);
//			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist mealType deletion.");
			
			logger.log(Level.ALL,"Exception thrown while deleting mealType(s)", e);
//			e.printStackTrace();
		}
	}

}
