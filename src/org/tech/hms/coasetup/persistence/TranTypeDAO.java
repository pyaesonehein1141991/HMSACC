package org.tech.hms.coasetup.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.coasetup.persistence.interfaces.ITranTypeDAO;
import org.tech.hms.common.TranCode;
import org.tech.hms.common.TranType;
import org.tech.java.component.persistence.BasicDAO;
import org.tech.java.component.persistence.exception.DAOException;

@Repository("TranTypeDAO")
public class TranTypeDAO extends BasicDAO implements ITranTypeDAO {

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TranType> findAll() throws DAOException {
		List<TranType> result = null;
		try {
			Query q = em.createNamedQuery("TranType.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of TranType", pe);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public TranType findByTransCode(TranCode tranCode) throws DAOException{
		List<TranType> result = null;
		try {
			Query q = em.createNamedQuery("TranType.findByTransCode");
			q.setParameter("tranCode", tranCode);
			result =  q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of TranType", pe);
		}
		return result.size() >= 1 ? result.get(0) : null;
	}
	
}
