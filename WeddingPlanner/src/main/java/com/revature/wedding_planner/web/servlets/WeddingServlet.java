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
import com.revature.wedding_planner.models.Wedding;
import com.revature.wedding_planner.services.WeddingService;

public class WeddingServlet extends HttpServlet{

	private final WeddingService weddingService;
	private final ObjectMapper mapper;

	public WeddingServlet(WeddingService weddingService, ObjectMapper mapper) {
		super();
		this.weddingService = weddingService;
		this.mapper = mapper;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();

		try {
			//TODO add parameterized options for specific weddings
			List<Wedding> weddings = weddingService.getAllWeddings();
			String payload = "";
			for (Wedding wedding: weddings) {
				payload += mapper.writeValueAsString(wedding.getName());
			}
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
			Wedding newWedding = mapper.readValue(req.getInputStream(), Wedding.class);
			boolean wasReg = weddingService.addWedding(newWedding);
			if (wasReg) {
				resp.setStatus(201);
			} else {
				resp.setStatus(500);
				resp.getWriter().write("Database did not persist new wedding.");
			}
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception.");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist wedding.");
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Wedding updatedWedding = mapper.readValue(req.getInputStream(), Wedding.class);
			weddingService.updateWedding(updatedWedding);
			resp.setStatus(204);
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist wedding update.");
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Wedding deletedWedding = mapper.readValue(req.getInputStream(), Wedding.class);
			weddingService.deleteWedding(deletedWedding);
			resp.setStatus(204);
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist wedding deletion.");
			e.printStackTrace();
		}
	}
}
