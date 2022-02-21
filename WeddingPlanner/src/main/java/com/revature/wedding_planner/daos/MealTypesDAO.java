package com.revature.wedding_planner.daos;

import java.io.IOException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.wedding_planner.models.MealTypes;
import com.revature.wedding_planner.util.datasource.HibernateUtil;

public class MealTypesDAO {
	
	
	public MealTypes create(MealTypes newMealType) {
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
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<MealTypes> findAll() {
		try {
			Session session = HibernateUtil.getSession();
			List<MealTypes> mealTypes = session.createQuery("FROM MealTypes").list();
			return mealTypes;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public MealTypes findById(int id) {
		try {
			Session session = HibernateUtil.getSession();
			MealTypes foundMealType = session.get(MealTypes.class, id); 
			return foundMealType;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	public MealTypes findByMealType(String mealType) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("SELECT FROM MealTypes WHERE MealTypes.mealType = :mealType ");
			query.setParameter("mealType", mealType);
			MealTypes foundMealType =  (MealTypes) query.getSingleResult();
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
	public boolean update(MealTypes updatedMealType) {
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
	public boolean delete(int id) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			MealTypes deletedMealType = this.findById(id);
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
