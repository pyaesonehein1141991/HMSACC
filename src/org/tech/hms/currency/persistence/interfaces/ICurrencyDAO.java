package org.tech.hms.currency.persistence.interfaces;

import java.util.List;

import org.tech.hms.currency.Currency;
import org.tech.java.component.persistence.exception.DAOException;

public interface ICurrencyDAO {

	public List<Currency> findForeignCurrency() throws DAOException;
}
