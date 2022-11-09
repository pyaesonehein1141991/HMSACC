package org.tech.hms.nrcPrefix.persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.nrcPrefix.NrcPrefix;
import org.tech.hms.nrcPrefix.persistence.interfaces.INrcPrefixDAO;
import org.tech.java.component.persistence.BasicDAO;
import org.tech.java.component.persistence.exception.DAOException;

@Repository("NrcPrefixDAO")
public class NrcPrefixDAO extends BasicDAO implements INrcPrefixDAO {


	@Transactional(propagation = Propagation.REQUIRED)
	public NrcPrefix findByNrcTsp(String code) {
		NrcPrefix result = null;
		try {
			Query q = em.createNamedQuery("NrcPrefix.findByCode");
			q.setParameter("nrcTsp", code);
			result = (NrcPrefix) q.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find NRC(NRC_Prefix = " + code + ")", pe);
		}
		return result;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public NrcPrefix findById(String id) throws DAOException {
		NrcPrefix result = null;
		try {
			Query q = em.createNamedQuery("User.findById");
			q.setParameter("id", id);
			result = (NrcPrefix) q.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find NrcPrefix(id = " + id + ")", pe);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<NrcPrefix> findALl() throws DAOException {
		List<NrcPrefix> result=null;
		try {
			Query q = em.createNamedQuery("NrcPrefix.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of NrcPrefix", pe);
		}
		return result;
	}

}
