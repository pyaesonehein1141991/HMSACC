package org.tech.hms.coa.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.branch.Branch;
import org.tech.hms.branch.services.interfaces.IBranchService;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.coa.persistence.interfaces.ICoaDAO;
import org.tech.hms.coa.service.interfaces.ICoaService;
import org.tech.hms.common.BusinessUtil;
import org.tech.hms.common.dto.coaDto.CoaDTO;
import org.tech.hms.common.dto.coaDto.CoaDialogCriteriaDto;
import org.tech.hms.currency.Currency;
import org.tech.hms.currency.service.interfaces.ICurrencyService;
import org.tech.hms.currencyChartOfAccount.CurrencyChartOfAccount;
import org.tech.hms.currencyChartOfAccount.service.interfaces.ICcoaService;
import org.tech.java.component.SystemException;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.DataRepService;
import org.tech.java.component.service.interfaces.IDataRepService;

@Service
public class CoaService extends DataRepService<ChartOfAccount> implements ICoaService {

	@Autowired
	private ICoaDAO coaDAO;

	@Autowired
	private ICurrencyService currencyService;

	@Autowired
	private IBranchService branchService;

	@Autowired
	@Qualifier(value = "CcoaService")
	private IDataRepService<CurrencyChartOfAccount> ccoaService;

	@Autowired
	private ICcoaService ccService;

	// create coa
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createCoa(ChartOfAccount coa) {
		try {
			Calendar cal = Calendar.getInstance();
			coa.setPDate(cal.getTime());
			String budInfo = BusinessUtil.getBudgetInfo(coa.getPDate(), 2);
			List<Currency> curList = currencyService.findAllCurrency();
			List<Branch> branchList = branchService.findAll(Branch.class);
			insert(coa);

			CurrencyChartOfAccount ccoa = null;
			for (Currency currency : curList) {
				for (Branch branch : branchList) {
					ccoa = new CurrencyChartOfAccount();
					ccoa.setCoa(coa);
					ccoa.setAcName(coa.getAcName());
					ccoa.setBranch(branch);
					ccoa.setCurrency(currency);
					ccoa.setBudget(budInfo);
					ccoaService.insert(ccoa);
				}
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Fail to create Chart of Account", e);
		}
	}

	// update coa
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ChartOfAccount updateCoa(ChartOfAccount coa) {

		try {
			for (CurrencyChartOfAccount ccoa : ccService.findCCOAByCOAid(coa.getId())) {
				ccoa.setAcName(coa.getAcName());
				ccoaService.update(ccoa);
			}
			return update(coa);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Fail to update Chart of Account", e);
		}
	}

	// delete coa
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteChartOfAccount(ChartOfAccount chartOfAccount) {
		try {

			delete(chartOfAccount);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to delete ChartOfAccount", e);
		}
	}

	// find all coa
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<ChartOfAccount> findAllCoa() {
		try {
			return coaDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of ChartOfAccount)", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<CoaDTO> findAllDTO() {
		try {
			return coaDAO.findALLDTO();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of ChartOfAccount)", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ChartOfAccount findCoaByAcCode(String acCode) {
		ChartOfAccount chartOfAccount = null;
		try {
			chartOfAccount = coaDAO.findByAcCode(acCode);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find COA By AcCode.)", e);
		}
		return chartOfAccount;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ChartOfAccount findCoaByibsbAcCode(String ibsbACode) {
		ChartOfAccount chartOfAccount = null;
		try {
			chartOfAccount = coaDAO.findByIbsbACode(ibsbACode);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find COA By IBSBCode.)", e);
		}
		return chartOfAccount;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ChartOfAccount> findAllCoaByAccountCodeType() {
		List<ChartOfAccount> result = null;
		try {
			result = coaDAO.findAllCOAByAccountCodeType();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of ChartOfAccount)", e);
		}
		return result;
	}

	@Override
	public List<ChartOfAccount> findAllCoaByCriteria(CoaDialogCriteriaDto dto) {
		List<ChartOfAccount> result = new ArrayList<>();
		try {
			result = coaDAO.findAllCoaByCriteria(dto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to findAllCoaByCriteria", e);
		}
		return result;
	}
}
