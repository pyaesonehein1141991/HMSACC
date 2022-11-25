package org.tech.hms.currency.service.interfaces;

import java.util.List;

import org.tech.hms.common.dto.coaDto.MonthlyRateDto;
import org.tech.hms.currency.Currency;

public interface ICurrencyService {

	public List<Currency> findAllCurrency();
	
	public List<Currency> findForeignCurrency();

	public Currency findHomeCurrency();

	public void addNewCurrency(Currency currency);

	public void updateCurrency(Currency currency);

	public void deleteCurrency(Currency currency);
	
	public List<MonthlyRateDto> findForeignCurrencyDto();
	
	public void updateAllMonthlyRate(List<MonthlyRateDto> currencyList);
}
