package com.revature.wedding_planner.daos;

import java.util.List;
import java.util.UUID;
import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.models.Wedding;
import com.revature.wedding_planner.util.HibernateUtil;

public class UserDAO implements CrudDAO<User>{

	@Override
	public User create(User newUser) {
		try {
			Session session = HibernateUtil.getSession();			
			//assign unique user id
			newUser.setId(UUID.randomUUID().toString());
			
			session.save(newUser);
			return newUser;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<User> findAll() {
		try {
			Session session = HibernateUtil.getSession();
			List<User> users = session.createQuery("from users").list();
			return users;
		} catch (HibernateException | IOException e) {
			//TODO implement logging
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
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

	@Override
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

	@Override
	public boolean delete(String id) {
		try {
			Session session = HibernateUtil.getSession();
			User deletedUser = this.findById(id);
			session.delete(deletedUser);
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
			User foundUser = session.get(User.class, username);
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
			User foundUser = session.get(User.class, email);
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

