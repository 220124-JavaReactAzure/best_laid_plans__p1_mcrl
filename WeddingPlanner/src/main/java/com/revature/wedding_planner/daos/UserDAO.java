package com.revature.wedding_planner.daos;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Query;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.util.datasource.HibernateUtil;

public class UserDAO {

//	static Logger logger;
	

	public User create(User newUser) {
		try {
			Session session = HibernateUtil.getSession();			
			
			//assign unique user id
			//newUser.setId(UUID.randomUUID().toString());
			Transaction transaction = session.beginTransaction();
			session.save(newUser);
			transaction.commit();
			return newUser;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
//			logger.log(Level.FINEST, "Exception thrown while creating user", e);
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public List<User> findAll() {
		try {
			Session session = HibernateUtil.getSession();
			List<User> users = session.createQuery("FROM User").list();
			return users;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public User findById(String id) {
		try {
			Session session = HibernateUtil.getSession();
			User foundUser = session.get(User.class, id); 
			return foundUser;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	//added int overload to test with serial generated int id type
	public User findById(int id) {
		try {
			Session session = HibernateUtil.getSession();
			User foundUser = session.get(User.class, id); 
			return foundUser;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public boolean update(User updatedUser) {
		try {
			Session session = HibernateUtil.getSession();
			// Updates and Deletes always start with a transaction and end with a commit
			Transaction transaction = session.beginTransaction();
			session.merge(updatedUser);
			transaction.commit();
			return true;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public boolean delete(String id) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			User deletedUser = this.findById(id);
			session.delete(deletedUser);
			transaction.commit();
			return true;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	
	//added int overload to test with serial generated int id type
	public boolean delete(User deletedUser) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.delete(deletedUser);
			transaction.commit();
			return true;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	//TODO: syntax is the same as find by id...does hibernate look at each column for a match?
	public User findByUsername(String username) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("SELECT FROM User WHERE User.username = :username ");
			query.setParameter("username", username);
			User foundUser = (User) query.getSingleResult();
			transaction.commit();
			return foundUser;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public User findByEmail(String email) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("SELECT FROM User WHERE User.email = :email ");
			query.setParameter("email", email);
			User foundUser = (User) query.getSingleResult();
			transaction.commit();
			return foundUser;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

//	public User findByWedding(Wedding wedding) {
//		try {
//			Session session = HibernateUtil.getSession();
//			User foundUser = session.get(User.class, wedding);
//			return foundUser;
//		} catch (HibernateException | IOException e) {
//			//TODO implement logging
//			e.printStackTrace();
//			return null;
//		} finally {
//			HibernateUtil.closeSession();
//		}
//	}

}

