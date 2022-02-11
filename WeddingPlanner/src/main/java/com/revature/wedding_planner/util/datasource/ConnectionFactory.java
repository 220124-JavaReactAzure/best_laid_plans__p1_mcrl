package com.revature.wedding_planner.util.datasource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * Singleton Design Pattern
 * 	- Creational Pattern
 *  - Restricts that only a single instance of this class can be made within the application
 *  - Constructor cannot be invoked outside of the class
 *  - Eager or lazy singletons
 *  
 * Factory Design Pattern
 * 	- Creational Pattern
 * 	- used to abstract away the creation/instantiate 
 */

public class ConnectionFactory {
	
	// Singleton - it's eager singleton, because the object is Instantitated at declaration
	private static final ConnectionFactory connectionFactory = new ConnectionFactory();
	
	// This is used for our database authentication, using the db.properties
	private Properties prop = new Properties();
	
	// This is checking for the library to connect to our database
	static {
		try {
			Class.forName("org.postgresql.Driver");
			// Testing for Azure SQL
			// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Privatized Constructor 
	private ConnectionFactory() {
		// Using .properties for DB credentials (this is to obfuscate)
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			prop.load(loader.getResourceAsStream("db.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// This to request that SINGLE instance of the ConnectionFactory Object 
	public static ConnectionFactory getInstance() {
		return connectionFactory;
	}
	
	
	// Try and establish that connection
	public Connection getConnection() {
		Connection conn = null;

		try {
			
			// Why are we using properties? Why not hard code?
			// Don't want peopole to have access - obfuscation
			// MAKE SURE TO PUT YOUR db.properties in your .gitignore!!!!!!
			conn = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("admin"),prop.getProperty("password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	

}
