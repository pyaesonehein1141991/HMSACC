package org.tech.hms.currencyChartOfAccount.service;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.branch.Branch;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.common.CCOADialogDTO;
import org.tech.hms.common.dto.coaDto.CcoaDto;
import org.tech.hms.common.dto.coaDto.YearlyBudgetDto;
import org.tech.hms.common.dto.obal.ObalCriteriaDto;
import org.tech.hms.common.dto.obal.ObalDto;
import org.tech.hms.currency.Currency;
import org.tech.hms.currencyChartOfAccount.CurrencyChartOfAccount;
import org.tech.hms.currencyChartOfAccount.persistence.interfaces.ICcoaDAO;
import org.tech.hms.currencyChartOfAccount.service.interfaces.ICcoaService;
import org.tech.java.component.SystemException;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.DataRepService;

@Service(value = "CcoaService")
public class CcoaService extends DataRepService<CurrencyChartOfAccount> implements ICcoaService {

	@Resource
	private ICcoaDAO ccoaDAO;

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
	public CurrencyChartOfAccount findSpecificCCOA(String coaId, String currencyId, String branchId,
			String optionalDepartmentId) {
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
	public List<CurrencyChartOfAccount> findCCOAforBranchClosing(String branchId) throws SystemException {
		try {
			return ccoaDAO.findCCOAforBranchClosing(branchId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Fail to find CCOA");
		}
	}

	@Override
	public List<CcoaDto> findAllCcoaDtos() {
		List<CcoaDto> result = null;
		try {
			result = ccoaDAO.findAllCcoaDtos();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of CcoaDtos)", e);
		}
		return result;
	}

	@Override
	public List<CurrencyChartOfAccount> findBudgetFigure(String branchId) {
		List<CurrencyChartOfAccount> ccoa = null;
		try {
			ccoa = ccoaDAO.findBudgetFigure(branchId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find Yearly Budget Figure.", e);
		}
		return ccoa;
	}

	@Override
	public BigDecimal finddblBalance(StringBuffer sf, ChartOfAccount coa, String budgetYear, Currency currency,
			Branch branch) {
//		BigDecimal result = BigDecimal.ZERO;
//		try {
//			result = ccoaDAO.finddblBalance(sf, coa, budgetYear, currency, branch);
//			if (result == null || result.equals(BigDecimal.ZERO)) {
//				sf = new StringBuffer(sf.toString().replace("FROM CurrencyChartOfAccount", "FROM CcoaHistory"));
//				result = ccoaHistoryDAO.finddblBalance(sf, coa, budgetYear, currency, branch);
//			}
//		} catch (DAOException e) {
//			throw new SystemException(e.getErrorCode(), "Failed to find COA By Branch ID.", e);
//		}
//		return result;
		return null;
	}

	@Override
	public void updateCCOAByAllBranch(CurrencyChartOfAccount ccoa) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ObalDto> findOpeningBalance(ObalCriteriaDto dto) {
		List<ObalDto> dtos = null;
		try {
			dtos = ccoaDAO.findObal(dto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find opening balance", e);
		}
		return dtos;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateObalByDtos(List<ObalDto> dtoList) {
		try {
			ccoaDAO.updateCcoaByObalDtos(dtoList);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to update opening balance", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CCOADialogDTO> findAllCCOADialogDTO(Currency currency, Branch branch) {
		List<CCOADialogDTO> results = null;
		try {
			results = ccoaDAO.findAllCCOADialogDTO(currency, branch);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find COA By Branch ID.", e);
		}
		return results;
	}

}