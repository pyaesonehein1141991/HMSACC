package org.tech.hms.currencyChartOfAccount.persistence.interfaces;

import java.math.BigDecimal;
import java.util.List;

import org.tech.hms.branch.Branch;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.common.dto.coaDto.YearlyBudgetDto;
import org.tech.hms.currency.Currency;
import org.tech.hms.currencyChartOfAccount.CurrencyChartOfAccount;
import org.tech.java.component.persistence.exception.DAOException;


public interface ICcoaDAO {

	public List<CurrencyChartOfAccount> findAll() throws DAOException;

	public List<CurrencyChartOfAccount> findCCOAByCOAid(String coaid) throws DAOException;

	public CurrencyChartOfAccount findSpecificCCOA(String coaId, String currencyId, String branchId, String optionalDepartmentId) throws DAOException;

	public List<String> findAllBudgetYear() throws DAOException;

	public void updateYearlyBudget(YearlyBudgetDto dto) throws DAOException;

	

	public List<CurrencyChartOfAccount> findCOAByBranchID(String branchID) throws DAOException;

	/*
	 * Enquiry Account Ledger Information
	 */
	

	public CurrencyChartOfAccount findCCOAByIdAndBranch(String coaId, Branch branch, Currency currency) throws DAOException;

	/*
	 * Account Ledger Listing
	 */
	public BigDecimal finddblBalance(StringBuffer sf, ChartOfAccount coa, String budgetYear, Currency currency, Branch branch) throws DAOException;

	/**
	 * The method to retrieve the list of CurrencyChartOfAccount.
	 * 
	 * @param String
	 *            branchId
	 * @return {@link List} of {@link CCOADialogDTO} instances
	 */


	public List<CurrencyChartOfAccount> findCOAByCurrencyID(String currencyID) throws DAOException;

	public List<ChartOfAccount> findAllCOAByAccountCodeType() throws DAOException;

	public List<CurrencyChartOfAccount> findCCOAByBranchID(String branchID) throws DAOException;

	public List<YearlyBudgetDto> findAllYearlyBudget(YearlyBudgetDto dto) throws DAOException;

	

	List<CurrencyChartOfAccount> findCCOAforBranchClosing(String branchId) throws DAOException;

	List<CurrencyChartOfAccount> findCCOAByCOAIDAndBranchId(String coaId, String branchId) throws DAOException;

	public List<CurrencyChartOfAccount> findCCOAforHOClosing(String branchId) throws DAOException;

	public List<CurrencyChartOfAccount> findCCOAByCOAIDAndBranchIdForHOClosing(String coaId, String branchId) throws DAOException;
}
