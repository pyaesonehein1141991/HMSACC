package org.tech.hms.web.system.branchcoa;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.tech.hms.common.validation.MessageId;
import org.tech.hms.currencyChartOfAccount.CurrencyChartOfAccount;
import org.tech.hms.department.Department;
import org.tech.hms.department.service.interfaces.IDepartmentService;
import org.tech.java.component.SystemException;
import org.tech.java.component.service.interfaces.IDataRepService;
import org.tech.java.web.common.BaseBean;

import lombok.Getter;
import lombok.Setter;

@Named(value = "EditBCoaActionBean")
@Scope(value = "view")
public class EditBCoaActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier(value = "CcoaService")
	private IDataRepService<CurrencyChartOfAccount> dataRepService;

	@Autowired
	private IDepartmentService departmentService;

	@Getter
	@Setter
	private CurrencyChartOfAccount ccoa;

	@Getter
	@Setter
	private List<Department> deptList;

	@PostConstruct
	public void init() {
		deptList = departmentService.findAllDepartment();
		if (isExistParam("ccoa")) {
			this.ccoa = (CurrencyChartOfAccount) getParam("ccoa");
		}
	}

	@PreDestroy
	public void destroy() {
		removeParam("ccoa");

	}

	public String updateCcoa() {
		try {
			dataRepService.update(ccoa);
			addInfoMessage(null, MessageId.UPDATE_SUCCESS, ccoa.getCoa().getAcCode());
			return "manageBranchCoaCode.xhtml?faces-redirect=true";
		} catch (SystemException ex) {
			handleSysException(ex);
		}
		return null;
	}

}
