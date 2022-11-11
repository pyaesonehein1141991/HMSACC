package org.tech.hms.currency.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.currency.Currency;
import org.tech.hms.currency.persistence.interfaces.ICurrencyDAO;
import org.tech.java.component.persistence.BasicDAO;
import org.tech.java.component.persistence.exception.DAOException;

@Repository("CurrerncyDAO")
public class CurrerncyDAO extends BasicDAO implements ICurrencyDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Currency> findAll() throws DAOException {
		List<Currency> result = null;
		try {
			Query q = em.createNamedQuery("Currency.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Currency", pe);
		}
		return result;
	}
		

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Currency> findForeignCurrency() throws DAOException {
		List<Currency> result = null;
		try {
			Query q = em.createNamedQuery("Currency.findForeignCurrency");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Currency", pe);
		}
		return result;
	}

	@Override
	public Currency findHomeCurrency() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Currency cur) throws DAOException {
		// TODO Auto-generated method stub
		
	}
	
}
