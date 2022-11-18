package org.tech.hms.exchangeconfig.service.interfaces;

import java.util.List;

import org.tech.hms.exchangeconfig.ExchangeConfig;
import org.tech.java.component.SystemException;

public interface IExchangeConfigService {

	List<ExchangeConfig> findAllExchangeConfig() throws SystemException;

	void addNewExchangeConfig(ExchangeConfig exchangeConfig) throws SystemException;

	void updateBranch(ExchangeConfig exchangeConfig) throws SystemException;

	void deleteBranch(ExchangeConfig exchangeConfig) throws SystemException;

}