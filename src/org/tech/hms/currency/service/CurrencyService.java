package org.tech.hms.currency.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.branch.Branch;
import org.tech.hms.branch.services.interfaces.IBranchService;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.coa.service.interfaces.ICoaService;
import org.tech.hms.common.dto.coaDto.MonthlyRateDto;
import org.tech.hms.currency.Currency;
import org.tech.hms.currency.persistence.interfaces.ICurrencyDAO;
import org.tech.hms.currency.service.interfaces.ICurrencyService;
import org.tech.hms.currencyChartOfAccount.CurrencyChartOfAccount;
import org.tech.java.component.SystemException;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.DataRepService;
import org.tech.java.component.service.interfaces.IDataRepService;

@Service(value = "CurrencyService")
public class CurrencyService extends DataRepService<Currency> implements ICurrencyService {

	@Autowired
	private ICurrencyDAO currencyDAO;

	@Autowired
	private ICoaService coaService;

	@Autowired
	private IBranchService branchService;

	@Autowired
	@Qualifier(value = "CcoaService")
	private IDataRepService<CurrencyChartOfAccount> ccoaService;

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public List<Currency> findAllCurrency() throws SystemException {
		return currencyDAO.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Currency findHomeCurrency() {
		Currency result = null;
		try {
			result = currencyDAO.findHomeCurrency();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find home Currency)", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void addNewCurrency(Currency currency) {
		try {
			insert(currency);
			List<ChartOfAccount> coaList = coaService.findAllCoa();
			List<Branch> branchList = branchService.findAllBranch();
			for (ChartOfAccount chartOfAccount : coaList) {
				for (Branch branch : branchList) {
					CurrencyChartOfAccount ccoa = new CurrencyChartOfAccount();
					ccoa.setCoa(chartOfAccount);
					ccoa.setAcName(chartOfAccount.getAcName());
					ccoa.setBranch(branch);
					ccoa.setCurrency(currency);
					ccoaService.insert(ccoa);
				}
			}
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
	public void deleteCurrency(Currency currency) {
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

	@Transactional(propagation = Propagation.REQUIRED)
	public List<MonthlyRateDto> findForeignCurrencyDto() {
		List<MonthlyRateDto> result = null;
		try {
			result = currencyDAO.findForeignCurrencyDto();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of Currency)", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateAllMonthlyRate(List<MonthlyRateDto> currencyList) {
		try {
			for (MonthlyRateDto currency : currencyList) {
				currencyDAO.updateMonthlyRate(currency);
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to update multiple currency", e);
		}
	}
}
