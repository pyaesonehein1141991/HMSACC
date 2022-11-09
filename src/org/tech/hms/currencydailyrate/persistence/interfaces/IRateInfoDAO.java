package org.tech.hms.currencydailyrate.persistence.interfaces;

import java.util.Date;
import java.util.List;

import org.tech.hms.currency.Currency;
import org.tech.hms.currencydailyrate.RateInfo;
import org.tech.hms.currencydailyrate.RateType;
import org.tech.java.component.persistence.exception.DAOException;

public interface IRateInfoDAO {

	public List<RateInfo> findAll() throws DAOException;

	public double findExchangeRateBy(Currency reqCur, RateType reqRateType, Date rDate) throws DAOException;

	public void updateLastModifyBy(Currency cur, RateType rateType) throws DAOException;

	public List<RateInfo> findRateInfoByCurrencyID(String currencyID) throws DAOException;

}
