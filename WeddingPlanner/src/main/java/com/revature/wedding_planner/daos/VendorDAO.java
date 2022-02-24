package com.revature.wedding_planner.daos;

import java.io.IOException;
import java.util.List;


import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.revature.wedding_planner.models.Vendor;
import com.revature.wedding_planner.util.datasource.HibernateUtil;

public class VendorDAO {
	public Vendor create(Vendor newVendor) {
		try {
			Session session = HibernateUtil.getSession();			
			
			//assign unique vendor id
		//	newVendor.setId(UUID.randomUUID());//.toString());
			Transaction transaction = session.beginTransaction();
			session.save(newVendor);
			transaction.commit();
			return newVendor;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public List<Vendor> findAll() {
		try {
			Session session = HibernateUtil.getSession();
			List<Vendor> vendors = session.createQuery("FROM Vendor").list();
			return vendors;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public Vendor findById(int id) {
		try {
			Session session = HibernateUtil.getSession();
			Vendor foundVendor = session.get(Vendor.class, id); 
			return foundVendor;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	//TODO: syntax is the same as find by id...does hibernate look at each column for a match?
	public Vendor findByVendorName(String vendorName) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("SELECT FROM Vendor WHERE Vendor.vendorName = :vendorName ");
			query.setParameter("vendorName", vendorName);
			Vendor foundVendor = (Vendor) query.getSingleResult();
			transaction.commit();
			return foundVendor;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public boolean update(Vendor updatedVendor) {
		try {
			Session session = HibernateUtil.getSession();
			// Updates and Deletes always start with a transaction and end with a commit
			Transaction transaction = session.beginTransaction();
			session.merge(updatedVendor);
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
			Vendor deletedVendor = this.findById(id);
			session.delete(deletedVendor);
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
