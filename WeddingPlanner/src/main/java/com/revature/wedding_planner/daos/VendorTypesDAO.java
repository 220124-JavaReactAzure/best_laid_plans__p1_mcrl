package com.revature.wedding_planner.daos;

import java.io.IOException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.wedding_planner.models.Vendor;
import com.revature.wedding_planner.models.VendorType;
import com.revature.wedding_planner.util.datasource.HibernateUtil;

public abstract class VendorTypesDAO implements CrudDAO<VendorType>{
	@Override
	public VendorType create(VendorType newVendorType) {
		try {
			Session session = HibernateUtil.getSession();			
			
			//assign unique vendor id
		//	newVendor.setId(UUID.randomUUID());//.toString());
			Transaction transaction = session.beginTransaction();
			session.save(newVendorType);
			transaction.commit();
			return newVendorType;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}


	@Override
	public List<VendorType> findAll() {
		try {
			Session session = HibernateUtil.getSession();
			List<VendorType> vendorTypes = session.createQuery("FROM VendorType").list();
			return vendorTypes;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public VendorType findById(int id) {
		try {
			Session session = HibernateUtil.getSession();
			VendorType foundVendorType = session.get(VendorType.class, id); 
			return foundVendorType;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	//TODO: syntax is the same as find by id...does hibernate look at each column for a match?
	public VendorType findByVendorName(String vendorTypeName) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("SELECT FROM Vendor WHERE VendorType.vendorTypeName = :vendorTypeName ");
			query.setParameter("vendorTypeName", vendorTypeName);
			VendorType foundVendorType = (VendorType) query.getSingleResult();
			transaction.commit();
			return foundVendorType;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	@Override
	public boolean update(VendorType updatedVendorType) {
		try {
			Session session = HibernateUtil.getSession();
			// Updates and Deletes always start with a transaction and end with a commit
			Transaction transaction = session.beginTransaction();
			session.merge(updatedVendorType);
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

	@Override
	public boolean delete(int id) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			VendorType deletedVendorType = this.findById(id);
			session.delete(deletedVendorType);
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
