package org.tech.hms.currency.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.currency.Currency;
import org.tech.hms.currency.persistence.interfaces.ICurrencyDAO;
import org.tech.hms.currency.service.interfaces.ICurrencyService;
import org.tech.hms.user.User;
import org.tech.java.component.SystemException;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.BaseService;
import org.tech.java.component.service.interfaces.IDataRepService;

@Service(value="CurrrencyService")
public class CurrrencyService extends BaseService implements ICurrencyService {
	
	@Resource(name = "CurrerncyDAO")
	private ICurrencyDAO currencyDAO;
	
	@Resource(name = "DataRepService")
	private IDataRepService<Currency> dataRepService;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public List<Currency> findAllCurrency() {
		List<Currency> result = null;
		try {
			result = currencyDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of Currency)", e);
		}
		return result;
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
			dataRepService.insert(currency);
	} catch (DAOException e) {
		throw new SystemException(e.getErrorCode(), "Failed to find all of User)", e);
	}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateCurrency(Currency currency) {
		try {
			dataRepService.update(currency);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to update currency", e);
		}
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCurrency(Currency currency) throws SystemException{
		try {
		dataRepService.delete(currency);
	} catch (DAOException e) {
		throw new SystemException(e.getErrorCode(), "Failed to delete user", e);
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
