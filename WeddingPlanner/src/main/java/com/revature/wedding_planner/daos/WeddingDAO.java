package com.revature.wedding_planner.daos;

import java.io.IOException;
import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.wedding_planner.models.Wedding;
import com.revature.wedding_planner.util.datasource.HibernateUtil;

public class WeddingDAO {

	private final Logger logger = LogManager.getLogger();

	public Wedding create(Wedding newWedding) {
		try {
			Session session = HibernateUtil.getSession();

			// assign unique  id
			// newObject.setId(UUID.randomUUID().toString());
			Transaction transaction = session.beginTransaction();
			session.save(newWedding);
			transaction.commit();
			return newWedding;
		} catch (HibernateException | IOException e) {
			// TODO implement logging
			logger.log(logger.getLevel(), "Exception thrown while creating wedding", e);
//			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public List<Wedding> findAll() {
		try {
			Session session = HibernateUtil.getSession();
			List<Wedding> weddings = session.createQuery("FROM Wedding").list();
			return weddings;
		} catch (HibernateException | IOException e) {
			// TODO implement logging
			logger.log(logger.getLevel(), "Exception thrown while finding all weddings", e);
//			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public Wedding findById(int id) {
		try {
			Session session = HibernateUtil.getSession();
			Wedding foundWedding = session.get(Wedding.class, id);
			return foundWedding;
		} catch (HibernateException | IOException e) {
			// TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public Wedding findByWeddingDate(String weddingDate) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("SELECT FROM Wedding WHERE Wedding.wedding_date = :wedding_date ");
			query.setParameter("wedding_date", weddingDate);
			Wedding foundWedding = (Wedding) query.getSingleResult();
			transaction.commit();
			return foundWedding;
		} catch (HibernateException | IOException e) {
			// TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public Wedding findByWeddingName(String weddingName) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("SELECT FROM Wedding WHERE Wedding.wedding_name = :wedding_name ");
			query.setParameter("wedding_name", weddingName);
			Wedding foundWedding = (Wedding) query.getSingleResult();
			transaction.commit();
			return foundWedding;
		} catch (HibernateException | IOException e) {
			// TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public boolean update(Wedding updatedWedding) {
		try {
			Session session = HibernateUtil.getSession();
			// Updates and Deletes always start with a transaction and end with a commit
			Transaction transaction = session.beginTransaction();
			session.merge(updatedWedding);
			transaction.commit();
			return true;
		} catch (HibernateException | IOException e) {
			// TODO implement logging
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
			Wedding deletedWedding = this.findById(id);
			session.delete(deletedWedding);
			transaction.commit();
			return true;
		} catch (HibernateException | IOException e) {
			// TODO implement logging
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}

}
