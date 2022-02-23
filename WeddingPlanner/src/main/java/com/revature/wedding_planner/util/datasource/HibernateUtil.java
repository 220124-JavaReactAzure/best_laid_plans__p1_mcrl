package com.revature.wedding_planner.util.datasource;


import java.io.IOException;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.revature.wedding_planner.models.MealTypes;
import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.models.UserTypes;
import com.revature.wedding_planner.models.Wedding;

// TODO: Implement me
public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static Session session;
	
	public static Session getSession() throws IOException {
		if(sessionFactory == null) {
			Configuration configuration = new Configuration();
			Properties props = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			props.load(loader.getResourceAsStream("hibernate.properties"));
			
			configuration.setProperties(props);

//			configuration.addAnnotatedClass(User.class);
//			configuration.addAnnotatedClass(Vendor.class);
//			configuration.addAnnotatedClass(Wedding.class);
			configuration.addAnnotatedClass(MealTypes.class);
//			configuration.addAnnotatedClass(UserTypes.class);
//			configuration.addAnnotatedClass(VendorType.class);
	
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		
		if(session == null) {
			session = sessionFactory.openSession();
		}
		
		return session;
	}
	
	public static void closeSession() {
		session.close(); 
		session = null;
		
	}
	
}
