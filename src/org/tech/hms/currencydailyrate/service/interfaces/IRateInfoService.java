package org.tech.hms.currencydailyrate.service.interfaces;

import java.util.Date;
import java.util.List;

import org.tech.hms.currency.Currency;
import org.tech.hms.currencydailyrate.RateInfo;

public interface IRateInfoService {
	public List<RateInfo> findAllRateInfo();

	public void addNewDailyRateInfo(RateInfo rateInfo);

	public List<RateInfo> findRateInfoByCurrencyID(String currencyID);

	double findCurrentRateInfo(Currency cur, Date voucherDate);
}
