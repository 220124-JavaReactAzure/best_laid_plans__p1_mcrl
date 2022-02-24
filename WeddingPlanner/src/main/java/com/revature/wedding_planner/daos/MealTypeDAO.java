package com.revature.wedding_planner.daos;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Query;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.wedding_planner.models.MealType;
import com.revature.wedding_planner.util.datasource.HibernateUtil;

public class MealTypeDAO {
	
//	static Logger logger;
	
	public MealType create(MealType newMealType) {
		try {
			Session session = HibernateUtil.getSession();			
			
			//assign unique user id
			//newUser.setId(UUID.randomUUID().toString());
			Transaction transaction = session.beginTransaction();
			session.save(newMealType);
			transaction.commit();
			return newMealType;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
//			logger.log(Level.FINEST, "Exception thrown while creating mealType", e);
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<MealType> findAll() {
		try {
			Session session = HibernateUtil.getSession();
			List<MealType> mealTypes = session.createQuery("FROM MealTypes").list();
			return mealTypes;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
//			logger.log(Level.FINEST, "Exception thrown while finding all mealTypes", e);
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public MealType findById(int id) {
		try {
			Session session = HibernateUtil.getSession();
			MealType foundMealType = session.get(MealType.class, id); 
			return foundMealType;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	public MealType findByMealType(String mealType) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("SELECT FROM MealTypes WHERE MealTypes.mealType = :mealType ");
			query.setParameter("mealType", mealType);
			MealType foundMealType =  (MealType) query.getSingleResult();
			transaction.commit();
			return foundMealType;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}	
	public boolean update(MealType updatedMealType) {
		try {
			Session session = HibernateUtil.getSession();
			// Updates and Deletes always start with a transaction and end with a commit
			Transaction transaction = session.beginTransaction();
			session.merge(updatedMealType);
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
	public boolean delete(MealType deletedMealType) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.delete(deletedMealType);
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
