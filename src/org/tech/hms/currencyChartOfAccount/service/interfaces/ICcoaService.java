package org.tech.hms.currencyChartOfAccount.service.interfaces;

import java.math.BigDecimal;
import java.util.List;

import org.tech.hms.branch.Branch;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.common.dto.coaDto.CcoaDto;
import org.tech.hms.common.dto.coaDto.YearlyBudgetDto;
import org.tech.hms.common.dto.obal.ObalCriteriaDto;
import org.tech.hms.common.dto.obal.ObalDto;
import org.tech.hms.currency.Currency;
import org.tech.hms.currencyChartOfAccount.CurrencyChartOfAccount;

public interface ICcoaService {
	public List<CurrencyChartOfAccount> findAll();

	public List<CurrencyChartOfAccount> findCCOAByCOAid(String coaid);

	public CurrencyChartOfAccount findSpecificCCOA(String coaId, String currencyId, String branchId,
			String optionalDepartmentId);

	public List<String> findAllBudgetYear();

	public void updateYearlyBudget(List<YearlyBudgetDto> yearlyBudgetList);

	public List<CcoaDto> findAllCcoaDtos();

	public List<CurrencyChartOfAccount> findBudgetFigure(String branchId);

	public List<CurrencyChartOfAccount> findCOAByBranchID(String branchID);

	public BigDecimal finddblBalance(StringBuffer sf, ChartOfAccount coa, String budgetYear, Currency currency,
			Branch branch);

	public List<CurrencyChartOfAccount> findCCOAByCurrencyID(String currencyID);

	public List<CurrencyChartOfAccount> findCCOAByBranchID(String branchID);

	public List<YearlyBudgetDto> findAllYearlyBudget(YearlyBudgetDto dto);

	public List<CurrencyChartOfAccount> findCCOAforBranchClosing(String branchId);

	void updateCCOAByAllBranch(CurrencyChartOfAccount ccoa);

	List<ObalDto> findOpeningBalance(ObalCriteriaDto dto);

	void updateObalByDtos(List<ObalDto> dtoList);
}
