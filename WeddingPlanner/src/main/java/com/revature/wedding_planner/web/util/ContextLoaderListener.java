package com.revature.wedding_planner.web.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.wedding_planner.daos.WeddingDAO;
import com.revature.wedding_planner.daos.UserDAO;
import com.revature.wedding_planner.services.WeddingService;
import com.revature.wedding_planner.services.UserService;
import com.revature.wedding_planner.web.servlets.AuthServlet;
import com.revature.wedding_planner.web.servlets.WeddingServlet;

@WebListener
public class ContextLoaderListener implements ServletContextListener{
	
	private final Logger logger = LogManager.getLogger();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("Application is initiliazing.....");
		ObjectMapper mapper = new ObjectMapper();
		
		UserDAO userDAO = new UserDAO();
		WeddingDAO weddingDAO = new WeddingDAO();
		UserService userService = new UserService(userDAO);
		WeddingService weddingService = new WeddingService(weddingDAO, userService);
		
		WeddingServlet weddingServlet = new WeddingServlet(weddingService, mapper);
		AuthServlet authServlet = new AuthServlet(userService, mapper);
		
		ServletContext context = sce.getServletContext();
		context.addServlet("WeddingServlet", weddingServlet).addMapping("/weddings/*");
		context.addServlet("AuthServlet", authServlet).addMapping("/auth");
		
		logger.info("Application initiliazed!!! We do did it!~WOOO~");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}
	
	
}