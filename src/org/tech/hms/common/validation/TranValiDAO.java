package org.tech.hms.common.validation;


import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.branch.Branch;
import org.tech.hms.currency.Currency;
import org.tech.java.component.persistence.BasicDAO;
import org.tech.java.component.persistence.exception.DAOException;

@Repository("TranValiDAO")
public class TranValiDAO extends BasicDAO implements ITranValiDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean isCurUsed(Currency currency) throws DAOException {
		StringBuffer buffer = null;
		Number result = null;
		Query query = null;
		try {
			buffer = new StringBuffer();
			buffer.append("SELECT SUM(ccoa.oBal) FROM CurrencyChartOfAccount ccoa WHERE ccoa.currency.id = :currencyId");
			query = em.createQuery(buffer.toString());
			query.setParameter("currencyId", currency.getId());
			result = (Number) query.getSingleResult();
			result = (result == null) ? 0 : result;
			if (result.doubleValue() > 0) {
				return true;
			}

			buffer = new StringBuffer();
			buffer.append("SELECT COUNT(t.id) FROM TLF t WHERE t.currency.id = :currencyId AND t.reverse = false");
			query = em.createQuery(buffer.toString());
			query.setParameter("currencyId", currency.getId());
			result = (Number) query.getSingleResult();
			if (result.doubleValue() > 0) {
				return true;
			}

			buffer = new StringBuffer();
			buffer.append("SELECT COUNT(t.id) FROM TLFHIST t WHERE t.currency.id = :currencyId AND t.reverse = false");
			query = em.createQuery(buffer.toString());
			query.setParameter("currencyId", currency.getId());
			result = (Number) query.getSingleResult();
			if (result.doubleValue() > 0) {
				return true;
			}

			buffer = new StringBuffer();
			buffer.append("SELECT COUNT(c.id) FROM COASetup c WHERE c.currency.id = :currencyId");
			query = em.createQuery(buffer.toString());
			query.setParameter("currencyId", currency.getId());
			result = (Number) query.getSingleResult();
			if (result.doubleValue() > 0) {
				return true;
			}
		} catch (PersistenceException pe) {
			throw translate("Failed to find Currency Used Transaction", pe);
		}

		return false;
	}

	

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean isBranchUsed(Branch branch) throws DAOException {
		try {
			Number result = null;
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT COUNT(DISTINCT u) FROM User u WHERE u.branch.id = :branchId ");
			Query query = em.createQuery(buffer.toString());
			query.setParameter("branchId", branch.getId());
			result = (Number) query.getSingleResult();
			if (result.doubleValue() > 0) {
				return true;
			}

			buffer = new StringBuffer();
			buffer.append("SELECT SUM(ccoa.oBal) FROM CurrencyChartOfAccount ccoa WHERE ccoa.branch.id = :branchId");
			query = em.createQuery(buffer.toString());
			query.setParameter("branchId", branch.getId());
			result = (Number) query.getSingleResult();
			result = (result == null) ? 0 : result;
			if (result.doubleValue() > 0) {
				return true;
			}

			buffer = new StringBuffer();
			buffer.append(" SELECT COUNT(t.id) FROM TLF t WHERE t.branch.id = :branchId  AND t.reverse = false ");
			query = em.createQuery(buffer.toString());
			query.setParameter("branchId", branch.getId());
			result = (Number) query.getSingleResult();
			if (result.doubleValue() > 0) {
				return true;
			}

			buffer = new StringBuffer();
			buffer.append(" SELECT COUNT(t.id) FROM TLFHIST t WHERE t.branch.id = :branchId  AND t.reverse = false ");
			query = em.createQuery(buffer.toString());
			query.setParameter("branchId", branch.getId());
			result = (Number) query.getSingleResult();
			if (result.doubleValue() > 0) {
				return true;
			}

		} catch (PersistenceException pe) {
			throw translate("Failed to find Branch Used Transaction", pe);
		}

		return false;

	}



	@Override
	public boolean isCoaUsed(String coaId) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}
}
