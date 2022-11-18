package org.tech.hms.currencyChartOfAccount.service;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.branch.Branch;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.coa.persistence.interfaces.ICoaDAO;
import org.tech.hms.common.dto.coaDto.CcoaDto;
import org.tech.hms.common.dto.coaDto.YearlyBudgetDto;
import org.tech.hms.currency.Currency;
import org.tech.hms.currencyChartOfAccount.CurrencyChartOfAccount;
import org.tech.hms.currencyChartOfAccount.persistence.interfaces.ICcoaDAO;
import org.tech.hms.currencyChartOfAccount.service.interfaces.ICcoaService;
import org.tech.java.component.SystemException;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.BaseService;
import org.tech.java.component.service.interfaces.IDataRepService;

@Service(value = "CcoaService")
public class CcoaService extends BaseService implements ICcoaService {
	@Resource(name = "CcoaDAO")
	private ICcoaDAO ccoaDAO;

	@Resource(name = "CoaDAO")
	private ICoaDAO coaDAO;

	/*
	 * @Resource(name = "CCOA_HistoryDAO") private ICCOA_HistoryDAO ccoaHistoryDAO;
	 */

	@Resource(name = "DataRepService")
	private IDataRepService<CurrencyChartOfAccount> ccoaDataRepService;

	@Transactional(propagation = Propagation.REQUIRED)
	public List<CurrencyChartOfAccount> findAll() {
		List<CurrencyChartOfAccount> list = null;
		try {
			list = ccoaDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of CCOA.", e);
		}

		return list;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<CurrencyChartOfAccount> findCCOAByCOAid(String coaid) {
		List<CurrencyChartOfAccount> list = null;
		try {
			list = ccoaDAO.findCCOAByCOAid(coaid);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of CCOA.", e);
		}

		return list;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CurrencyChartOfAccount findSpecificCCOA(String coaId, String currencyId, String branchId, String optionalDepartmentId) {
		CurrencyChartOfAccount result = null;
		try {
			result = ccoaDAO.findSpecificCCOA(coaId, currencyId, branchId, optionalDepartmentId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find specific CCOA.", e);
		}

		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<String> findAllBudgetYear() {
		List<String> list = null;
		try {
			list = ccoaDAO.findAllBudgetYear();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all Budget Year.", e);
		}
		return list;
	}

	

	@Transactional(propagation = Propagation.REQUIRED)
	public List<YearlyBudgetDto> findAllYearlyBudget(YearlyBudgetDto dto) {
		List<YearlyBudgetDto> result = null;
		try {
			result = ccoaDAO.findAllYearlyBudget(dto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find All Year Budget ", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateYearlyBudget(List<YearlyBudgetDto> dtoList) {
		try {
			for (YearlyBudgetDto dto : dtoList) {
				ccoaDAO.updateYearlyBudget(dto);
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to update Yearly Budget.", e);
		}
	}


	@Transactional(propagation = Propagation.REQUIRED)
	public List<CurrencyChartOfAccount> findCOAByBranchID(String branchID) {
		List<CurrencyChartOfAccount> list = null;
		try {
			list = ccoaDAO.findCOAByBranchID(branchID);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find COA By Branch ID.", e);
		}
		return list;
	}


	@Transactional(propagation = Propagation.REQUIRED)
	public List<CurrencyChartOfAccount> findCCOAByCurrencyID(String currencyID) {
		List<CurrencyChartOfAccount> list = null;
		try {
			list = ccoaDAO.findCOAByCurrencyID(currencyID);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of CCOA", e);
		}

		return list;
	}

	public List<CurrencyChartOfAccount> findCCOAByBranchID(String branchID) {
		List<CurrencyChartOfAccount> list = null;
		try {
			list = ccoaDAO.findCCOAByBranchID(branchID);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of CCOA", e);
		}

		return list;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CurrencyChartOfAccount> findCCOAforBranchClosing(String branchId)throws SystemException{
		try {
			return ccoaDAO.findCCOAforBranchClosing(branchId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Fail to find CCOA");
		}
	}

	@Override
	public List<CcoaDto> findAllCcoaDtos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CurrencyChartOfAccount> findBudgetFigure(String branchId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal finddblBalance(StringBuffer sf, ChartOfAccount coa, String budgetYear, Currency currency,
			Branch branch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCCOAByAllBranch(CurrencyChartOfAccount ccoa) {
		// TODO Auto-generated method stub
		
	}
	

}