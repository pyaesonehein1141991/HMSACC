package org.tech.hms.currency.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.currency.Currency;
import org.tech.hms.currency.persistence.interfaces.ICurrencyDAO;
import org.tech.hms.currency.service.interfaces.ICurrencyService;
import org.tech.java.component.SystemException;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.DataRepService;

@Service(value = "CurrrencyService")
public class CurrrencyService extends DataRepService<Currency> implements ICurrencyService {

	@Resource(name = "CurrerncyDAO")
	private ICurrencyDAO currencyDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public List<Currency> findAllCurrency() throws SystemException {
		return currencyDAO.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Currency findHomeCurrency() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void addNewCurrency(Currency currency) {
		try {
			// TODO Business Logic
			insert(currency);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to add currency", e);
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateCurrency(Currency currency) {
		try {
			// TODO Business Logic
			update(currency);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to update currency", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCurrency(Currency currency){
		try {
			// TODO Business Logic
			delete(currency);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to delete currency", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Currency> findForeignCurrency() {
		List<Currency> result = null;
		try {
			result = currencyDAO.findForeignCurrency();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of Currency)", e);
		}
		return result;
	}


}
