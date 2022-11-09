package org.tech.hms.user.persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.user.USR001;
import org.tech.hms.user.User;
import org.tech.hms.user.persistence.interfaces.IUserDAO;
import org.tech.java.component.persistence.BasicDAO;
import org.tech.java.component.persistence.exception.DAOException;

@Repository("UserDAO")
public class UserDAO extends BasicDAO implements IUserDAO {

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<USR001> findAll() throws DAOException {
		List<USR001> result = null;
		try {
			Query q = em.createQuery("SELECT NEW " + USR001.class.getName()+ "(u.id, u.name, u.userCode, u.dateOfBirth) FROM User u ORDER BY u.name ASC");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of User", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User find(String usercode) throws DAOException {
		User result = null;
		try {
			Query q = em.createNamedQuery("User.findByUsercode");
			q.setParameter("userCode", usercode);
			result = (User) q.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find User(Username = " + usercode + ")", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public User findById(String id) throws DAOException {
		User result = null;
		try {
			Query q = em.createNamedQuery("User.findById");
			q.setParameter("id", id);
			result = (User) q.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find User(id = " + id + ")", pe);
		}
		return result;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(String id) throws DAOException {
		try {
			Query q = em.createNamedQuery("User.deleteById");
			q.setParameter("id", id);
			q.executeUpdate();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find User(id = " + id + ")", pe);
		}
	}

}
