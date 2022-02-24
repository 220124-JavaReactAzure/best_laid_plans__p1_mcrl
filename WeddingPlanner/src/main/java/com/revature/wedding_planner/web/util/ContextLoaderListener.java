package com.revature.wedding_planner.web.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.wedding_planner.daos.WeddingDAO;
import com.revature.wedding_planner.daos.MealTypeDAO;
import com.revature.wedding_planner.daos.UserDAO;
import com.revature.wedding_planner.daos.UserTypeDAO;
import com.revature.wedding_planner.daos.VendorDAO;
import com.revature.wedding_planner.daos.VendorTypesDAO;
import com.revature.wedding_planner.services.WeddingService;
import com.revature.wedding_planner.services.MealTypeService;
import com.revature.wedding_planner.services.UserService;
import com.revature.wedding_planner.services.UserTypeService;
import com.revature.wedding_planner.services.VendorService;
import com.revature.wedding_planner.services.VendorTypesService;
import com.revature.wedding_planner.web.servlets.AuthServlet;
import com.revature.wedding_planner.web.servlets.MealTypeServlet;
import com.revature.wedding_planner.web.servlets.UserServlet;
import com.revature.wedding_planner.web.servlets.UserTypeServlet;
import com.revature.wedding_planner.web.servlets.VendorServlet;
import com.revature.wedding_planner.web.servlets.VendorTypesServlet;
import com.revature.wedding_planner.web.servlets.WeddingServlet;

@WebListener
public class ContextLoaderListener implements ServletContextListener{
	
	private final Logger logger = LogManager.getLogger();
	

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("Application is initiliazing.....");
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate5Module());
		
		UserDAO userDAO = new UserDAO();
		WeddingDAO weddingDAO = new WeddingDAO();
		VendorDAO vendorDAO = new VendorDAO();
		MealTypeDAO mealTypesDAO = new MealTypeDAO();
		VendorTypesDAO vendorTypesDAO = new VendorTypesDAO();
		UserTypeDAO userTypesDAO = new UserTypeDAO();
		
		UserService userService = new UserService(userDAO);
		WeddingService weddingService = new WeddingService(weddingDAO);
		VendorService vendorService = new VendorService(vendorDAO);
		VendorTypesService vendorTypesService = new VendorTypesService(vendorTypesDAO);
		MealTypeService mealTypesService = new MealTypeService(mealTypesDAO);
		UserTypeService userTypesService = new UserTypeService(userTypesDAO);
		//TODO activate remaining services/daos
		
		WeddingServlet weddingServlet = new WeddingServlet(weddingService, mapper);
		UserServlet userServlet = new UserServlet(userService, mapper);
		VendorServlet vendorServlet = new VendorServlet(vendorService, mapper);
		VendorTypesServlet vendorTypesServlet = new VendorTypesServlet(vendorTypesService, mapper);
		MealTypeServlet mealTypesServlet = new MealTypeServlet(mealTypesService, mapper);
		UserTypeServlet userTypesServlet = new UserTypeServlet(userTypesService, mapper);
		
		//AuthServlet authServlet = new AuthServlet(userService, mapper);
		//TODO initiate remaining servlets
		
		ServletContext context = sce.getServletContext();
		context.addServlet("WeddingServlet", weddingServlet).addMapping("/weddings/*");
		context.addServlet("UserServlet", userServlet).addMapping("/users/*");
		context.addServlet("VendorServlet", vendorServlet).addMapping("/vendors/*");
		context.addServlet("VendorTypesServlet", vendorTypesServlet).addMapping("/vendor-types/*");
		context.addServlet("MealTypesServlet", mealTypesServlet).addMapping("/meal-types/*");
		context.addServlet("UserTypesServlet", userTypesServlet).addMapping("/user-types/*");

		//context.addServlet("AuthServlet", authServlet).addMapping("/auth");
		//TODO add remaining servlets to the context
		
		
		//TODO implement/update logging
		logger.info("Application initiliazed!!! We do did it!~WOOO~");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}
	
	
}