package org.tech.hms.exchangeconfig.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.exchangeconfig.ExchangeConfig;
import org.tech.hms.exchangeconfig.persistence.interfaces.IExchangeConfigDAO;
import org.tech.java.component.persistence.BasicDAO;
import org.tech.java.component.persistence.exception.DAOException;

@Repository("ExchangeConfigDAO")
public class ExchangeConfigDAO extends BasicDAO implements IExchangeConfigDAO {
	
	@Transactional(propagation = Propagation.REQUIRED,readOnly = true)
	@Override
	public List<ExchangeConfig> findAllExchangeConfig() throws DAOException{
		try {
			TypedQuery<ExchangeConfig> query = em.createNamedQuery("ExchangeConfig.findAll", ExchangeConfig.class);
			return query.getResultList();
		} catch (NoResultException ne) {
			return new ArrayList<>();
		} catch (PersistenceException pe) {
			throw translate("Fail to find all ExchangeConfig Data", pe);
		}
	}

}
