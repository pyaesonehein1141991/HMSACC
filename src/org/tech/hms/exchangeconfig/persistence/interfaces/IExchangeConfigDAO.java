package org.tech.hms.exchangeconfig.persistence.interfaces;

import java.util.List;

import org.tech.hms.exchangeconfig.ExchangeConfig;
import org.tech.java.component.persistence.exception.DAOException;



public interface IExchangeConfigDAO {

	List<ExchangeConfig> findAllExchangeConfig() throws DAOException;

}