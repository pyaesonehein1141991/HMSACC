package org.tech.hms.exchangeconfig.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.exchangeconfig.ExchangeConfig;
import org.tech.hms.exchangeconfig.persistence.interfaces.IExchangeConfigDAO;
import org.tech.hms.exchangeconfig.service.interfaces.IExchangeConfigService;
import org.tech.java.component.SystemException;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.interfaces.IDataRepService;

@Service(value = "ExchangeConfigService")
public class ExchangeConfigService implements IExchangeConfigService {

	@Autowired
	private IExchangeConfigDAO exchangeConfigDAO;

	@Resource(name = "DataRepService")
	private IDataRepService<ExchangeConfig> exchangeDataRepService;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public List<ExchangeConfig> findAllExchangeConfig() throws SystemException {
		try {
			return exchangeConfigDAO.findAllExchangeConfig();
		} catch (DAOException de) {
			throw new SystemException(de.getErrorCode(), "Failed to Find All ExchangeConfig Data ", de);
		}

	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void addNewExchangeConfig(ExchangeConfig exchangeConfig) throws SystemException {
		try {
			exchangeDataRepService.insert(exchangeConfig);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to add new ExchangeConfig", e);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void updateBranch(ExchangeConfig exchangeConfig) throws SystemException {
		try {
			exchangeDataRepService.update(exchangeConfig);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to update Exchange Config", e);
		} 
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void deleteBranch(ExchangeConfig exchangeConfig) throws SystemException {
		try {
			exchangeDataRepService.delete(exchangeConfig);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to Exchange Config", e);
		}
	}
}
