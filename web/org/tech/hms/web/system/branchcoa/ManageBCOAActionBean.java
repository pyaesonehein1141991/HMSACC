package org.tech.hms.web.system.branchcoa;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.tech.hms.common.dto.coaDto.CcoaDto;
import org.tech.hms.currency.Currency;
import org.tech.hms.currency.service.interfaces.ICurrencyService;
import org.tech.hms.currencyChartOfAccount.CurrencyChartOfAccount;
import org.tech.hms.currencyChartOfAccount.service.interfaces.ICcoaService;
import org.tech.hms.department.Department;
import org.tech.hms.department.service.interfaces.IDepartmentService;
import org.tech.java.component.service.interfaces.IDataRepService;
import org.tech.java.web.common.BaseBean;

@Named(value = "ManageBCOAActionBean")
@Scope(value = "view")
public class ManageBCOAActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ICcoaService ccoaService;

	@Autowired
	@Qualifier(value = "CcoaService")
	private IDataRepService<CurrencyChartOfAccount> dataRepService;

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private ICurrencyService currencyService;

	private CurrencyChartOfAccount ccoa;
	private List<CcoaDto> ccoaDtoList;
	private List<Department> deptList;
	private List<Currency> currencyList;

	@PostConstruct
	public void init() {
		deptList = departmentService.findAllDepartment();
		currencyList = currencyService.findAllCurrency();
		createNewCcoa();
		loadData();
	}

	private void loadData() {
		ccoaDtoList = ccoaService.findAllCcoaDtos();
	}

	public String prepareUpdateCcoa(CcoaDto dto) {
		CurrencyChartOfAccount ccoa = dataRepService.findById(CurrencyChartOfAccount.class, dto.getId());
		putParam("ccoa", ccoa);
		return "editBranchCoaCode.xhtml?faces-redirect=true";
	}

	public void createNewCcoa() {
		ccoa = new CurrencyChartOfAccount();
	}

	public CurrencyChartOfAccount getCcoa() {
		return ccoa;
	}

	public List<Department> getDepartments() {
		return deptList;
	}

	public List<CcoaDto> getCcoaDtoList() {
		return ccoaDtoList;
	}

	public List<Currency> getCurrencyList() {
		return currencyList;

	}

}
