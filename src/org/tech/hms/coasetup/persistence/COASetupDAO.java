package org.tech.hms.coasetup.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.branch.Branch;
import org.tech.hms.coasetup.COASetup;
import org.tech.hms.coasetup.persistence.interfaces.ICOASetupDAO;
import org.tech.hms.currency.Currency;
import org.tech.java.component.persistence.BasicDAO;
import org.tech.java.component.persistence.exception.DAOException;

@Repository("COASetupDAO")
public class COASetupDAO extends BasicDAO implements ICOASetupDAO {

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public COASetup findCOAByACNameAndCur(String acName, Currency currency) throws DAOException {

		List<COASetup> result = null;

		try {
			StringBuffer sF = new StringBuffer("SELECT c FROM COASetup c WHERE c.acName=:acName ");
			if (currency != null) {
				sF.append("AND c.currency=:currency");
			}
			/*
			 * if (branch != null) { sF.append(" AND c.branch=:branch "); }
			 */
			Query q = em.createQuery(sF.toString());
			q.setParameter("acName", acName);

			if (currency != null) {
				q.setParameter("currency", currency);
			}
			/*
			 * if (branch != null) { q.setParameter("branch", branch); }
			 */
			q.setFirstResult(0);
			q.setMaxResults(1);
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find COASetup  by" + acName + currency.getCode(), pe);
		}

		return result.size() >= 1 ? result.get(0) : null;
	}
	
}
