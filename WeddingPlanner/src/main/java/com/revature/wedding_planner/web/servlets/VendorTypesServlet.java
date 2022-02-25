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
import com.revature.wedding_planner.models.VendorType;

import com.revature.wedding_planner.services.VendorTypesService;

public class VendorTypesServlet extends HttpServlet{
	private final VendorTypesService vendorTypesService;
	private final ObjectMapper mapper;
	
	public VendorTypesServlet(VendorTypesService vendorTypesService, ObjectMapper mapper) {
		super();
		this.vendorTypesService = vendorTypesService;
		this.mapper = mapper;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();

		try {
			List<VendorType> vendorTypes = VendorTypesService.getAllVendorTypes();
			String payload = "";
			for (VendorType vendorType: vendorTypes) {
				payload += mapper.writeValueAsString(vendorType.getVendorType()) + "\n";
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
			VendorType newVendorType = mapper.readValue(req.getInputStream(), VendorType.class);
			boolean wasReg = vendorTypesService.addVendorType(newVendorType);
			if (wasReg) {
				resp.setStatus(201);
			} else {
				resp.setStatus(500);
				resp.getWriter().write("Database did not persist new vendorType.");
			}
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception.");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist vendor Type.");
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			VendorType updatedVendorType = mapper.readValue(req.getInputStream(), VendorType.class);
			vendorTypesService.updateVendor(updatedVendorType);
			resp.setStatus(204);
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist Vendor Type update.");
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			VendorType  deletedVendorType = mapper.readValue(req.getInputStream(), VendorType.class);
			vendorTypesService.deleteVendorType(deletedVendorType);
			resp.setStatus(204);
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist vendor Type deletion.");
			e.printStackTrace();
		}
	}

}

