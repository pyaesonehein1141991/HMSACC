package org.tech.hms.branch.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.branch.Branch;
import org.tech.hms.branch.persistence.interfaces.IBranchDAO;
import org.tech.hms.branch.services.interfaces.IBranchService;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.coa.service.interfaces.ICoaService;
import org.tech.hms.common.BusinessUtil;
import org.tech.hms.common.dto.brachDto.BranchDTO;
import org.tech.hms.currency.Currency;
import org.tech.hms.currency.service.interfaces.ICurrencyService;
import org.tech.hms.currencyChartOfAccount.CurrencyChartOfAccount;
import org.tech.java.component.ErrorCode;
import org.tech.java.component.SystemException;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.DataRepService;
import org.tech.java.component.service.interfaces.IDataRepService;

@Service(value = "BranchService")
public class BranchService extends DataRepService<Branch> implements IBranchService {

	@Autowired
	private IBranchDAO branchDAO;

	@Autowired
	private ICurrencyService currencyService;

	@Autowired
	@Qualifier(value = "CcoaService")
	private IDataRepService<CurrencyChartOfAccount> ccoaService;

	@Autowired
	private ICoaService coaService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<BranchDTO> findAllBranchDto() throws SystemException {
		try {
			return branchDAO.findALLDTO();
		} catch (DAOException de) {
			throw new SystemException(de.getErrorCode(), "Fail to find all branch", de);
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(String id) throws SystemException {
		try {
			branchDAO.deleteById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Fail to find all branch", e);
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addNewBranch(Branch branch) throws SystemException {
		try {
			insert(branch);
			List<ChartOfAccount> coaList = coaService.findAllCoa();
			List<Currency> currencyList = currencyService.findAllCurrency();

			for (ChartOfAccount chartOfAccount : coaList) {
				String budInfo = BusinessUtil.getBudgetInfo(chartOfAccount.getPDate(), 2);
				for (Currency currency : currencyList) {
					CurrencyChartOfAccount ccoa = new CurrencyChartOfAccount();
					ccoa.setCoa(chartOfAccount);
					ccoa.setAcName(chartOfAccount.getAcName());
					ccoa.setCurrency(currency);
					ccoa.setBranch(branch);
					ccoa.setBudget(budInfo);
					ccoaService.insert(ccoa);
				}
				coaService.updateCoa(chartOfAccount);
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to add new branch", e);
		} catch (SystemException e) {
			if (e.getErrorCode().equals(ErrorCode.DUPLICATE_KEY_FOUND)) {
				throw new SystemException(ErrorCode.DUPLICATE_BRANCH_CODE, "Branch code must be unique.", e);
			}
			throw e;
		}
	}

}
