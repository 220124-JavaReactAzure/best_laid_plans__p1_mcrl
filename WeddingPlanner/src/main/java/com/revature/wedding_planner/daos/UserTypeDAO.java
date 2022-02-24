package com.revature.wedding_planner.daos;

import java.io.IOException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.wedding_planner.models.UserType;
import com.revature.wedding_planner.util.datasource.HibernateUtil;

public class UserTypeDAO {
	 
//	static Logger logger;
	
	public UserType create(UserType newUserType) {
		try {
			Session session = HibernateUtil.getSession();			
			
			//assign unique user id
			//newUser.setId(UUID.randomUUID().toString());
			Transaction transaction = session.beginTransaction();
			session.save(newUserType);
			transaction.commit();
			return newUserType;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
//			logger.log(Level.FINEST, "Exception thrown while creating userType", e);
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<UserType> findAll() {
		try {
			Session session = HibernateUtil.getSession();
			List<UserType> userTypes = session.createQuery("FROM UserType").list();
			return userTypes;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
//			logger.log(Level.FINEST, "Exception thrown while finding all userTypes", e);
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public UserType findById(int id) {
		try {
			Session session = HibernateUtil.getSession();
			UserType foundUserType = session.get(UserType.class, id); 
			return foundUserType;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	public UserType findByUserType(String userType) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("SELECT FROM UserTypes WHERE UserTypes.userType = :userType ");
			query.setParameter("userType", userType);
			UserType foundUserType =  (UserType) query.getSingleResult();
			transaction.commit();
			return foundUserType;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}	
	public boolean update(UserType updatedUserType) {
		try {
			Session session = HibernateUtil.getSession();
			// Updates and Deletes always start with a transaction and end with a commit
			Transaction transaction = session.beginTransaction();
			session.merge(updatedUserType);
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
	public boolean delete(UserType deletedUserType) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.delete(deletedUserType);
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
}
