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
import com.revature.wedding_planner.models.Vendor;
import com.revature.wedding_planner.services.VendorService;

public class VendorServlet extends HttpServlet {
	private final VendorService vendorService;
	private final ObjectMapper mapper;

	public VendorServlet(VendorService vendorService, ObjectMapper mapper) {
		super();
		this.vendorService = vendorService;
		this.mapper = mapper;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();

		try {
			List<Vendor> vendors = vendorService.getAllVendors();
			String payload = "";
			for (Vendor vendor: vendors) {
				payload += mapper.writeValueAsString(vendor) + "\n";
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
			Vendor newVendor = mapper.readValue(req.getInputStream(), Vendor.class);
			boolean wasReg = vendorService.addVendor(newVendor);
			if (wasReg) {
				resp.setStatus(201);
			} else {
				resp.setStatus(500);
				resp.getWriter().write("Database did not persist vendor.");
			}
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception.");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
//			logger.log(Level.FINEST, "Exception thrown while creating user", e);
			resp.getWriter().write("Some other random exception--did not persist vendor.");
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Vendor updatedVendor = mapper.readValue(req.getInputStream(), Vendor.class);
			vendorService.updateVendor(updatedVendor);
			resp.setStatus(204);
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception.");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception--did not persist vendor update.");
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// add parameter options for delete by Id, currently only works with fully
			// formed objects
			Vendor deletedVendor = mapper.readValue(req.getInputStream(), Vendor.class);
			vendorService.deleteVendor(deletedVendor);
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
