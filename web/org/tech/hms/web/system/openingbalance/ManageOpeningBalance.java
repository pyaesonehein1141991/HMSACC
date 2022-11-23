package org.tech.hms.web.system.openingbalance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.tech.hms.branch.Branch;
import org.tech.hms.codesetup.AccountCodeType;
import org.tech.hms.common.AccountType;
import org.tech.hms.common.dto.coaDto.CoaDialogCriteriaDto;
import org.tech.hms.common.dto.obal.ObalCriteriaDto;
import org.tech.hms.common.dto.obal.ObalDto;
import org.tech.hms.common.validation.MessageId;
import org.tech.hms.currency.Currency;
import org.tech.hms.currency.service.interfaces.ICurrencyService;
import org.tech.hms.currencyChartOfAccount.service.interfaces.ICcoaService;
import org.tech.hms.department.Department;
import org.tech.hms.process.interfaces.IUserProcessService;
import org.tech.hms.user.User;
import org.tech.java.component.SystemException;
import org.tech.java.web.common.BaseBean;
import org.tech.java.web.common.ParamId;

import lombok.Getter;
import lombok.Setter;

@Named(value = "ManageOpeningBalance")
@Scope("view")
public class ManageOpeningBalance extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IUserProcessService userProcessService;

	@Autowired
	private ICcoaService ccoaService;

	@Autowired
	private ICurrencyService currencyService;

	@Getter
	private boolean branchDisable = true;

	@Getter
	@Setter
	private ObalCriteriaDto dto;
	@Getter
	@Setter
	private ObalDto dtoToDelete;
	private List<ObalDto> dtoList;

	private BigDecimal balDiff;

	@PostConstruct
	public void init() {
		createNew();
	}

	public void createNew() {
		balDiff = BigDecimal.ZERO;
		rebindData();
	}

	public void rebindData() {
		dtoList = new ArrayList<>();
		filteredList = new ArrayList<>();
		dto = new ObalCriteriaDto();
		User user = userProcessService.getLoginUser();
		if (user.isAdmin()) {
			branchDisable = false;
		} else {
			branchDisable = true;
			dto.setBranchId(user.getBranch().getId());
		}
		PrimeFaces.current().executeScript("PF('openingBalanceTable').clearFilters();");
	}

	public void delete(ObalDto dtoToDelete) {
		dtoToDelete.setOBal(BigDecimal.ZERO);
		dtoToDelete.setHoBal(BigDecimal.ZERO);
	}

	public void deleteAll() {
		for (ObalDto dto : dtoList) {
			dto.setOBal(BigDecimal.ZERO);
			dto.setHoBal(BigDecimal.ZERO);
		}
	}

	public void search() {
		if (dtoList.size() > 0 && (dtoList.stream().anyMatch(dto -> dto.isUpdated() == true))) {
			PrimeFaces.current().executeScript("PF('saveCheckDialog').show();");
		} else {
			dtoList = ccoaService.findOpeningBalance(dto);
		}
	}

	public void save() {
		try {
			calBalDiff();
			if (balDiff.compareTo(BigDecimal.ZERO) != 0) {
				addErrorMessage("openingBalanceForm:openingBalanceTable", MessageId.OUT_OF_BALANCE);
				PrimeFaces.current().ajax().update("openingBalanceForm");
			} else {
				ccoaService.updateObalByDtos(dtoList);
				addInfoMessage(null, MessageId.UPDATE_SUCCESS, "Opening balance");
				balDiff = BigDecimal.ZERO;
				rebindData();
				PrimeFaces.current().ajax().update("openingBalanceForm");

			}
		} catch (SystemException e) {
			handleSysException(e);
		}
	}

	public void calBalDiff() {
		BigDecimal assetTotal = BigDecimal.ZERO;
		BigDecimal liabilityTotal = BigDecimal.ZERO;
		for (ObalDto dto : dtoList) {
			if (dto.getAcType() == AccountType.A) {
				assetTotal = assetTotal.add(dto.getHoBal());
			} else if (dto.getAcType() == AccountType.L) {
				liabilityTotal = liabilityTotal.add(dto.getHoBal());
			}
		}
		balDiff = assetTotal.subtract(liabilityTotal);
	}

	public void setDtoList(List<ObalDto> dtoList) {
		this.dtoList = dtoList;
	}

	public List<ObalDto> getDtoList() {
		return dtoList;
	}

	public double getBalDiff() {
		return balDiff.doubleValue();
	}

	public void setBalDiff(BigDecimal balDiff) {
		this.balDiff = balDiff;
	}

	private List<ObalDto> filteredList;

	public List<ObalDto> getFilteredList() {
		return filteredList;
	}

	public void setFilteredList(List<ObalDto> filteredList) {
		this.filteredList = filteredList;
	}

	public void openCoaDialog() {
		CoaDialogCriteriaDto dto = new CoaDialogCriteriaDto();
		dto.setAccountCodeType(AccountCodeType.DETAIL);
		dto.addAccountTypes(AccountType.A);
		dto.addAccountTypes(AccountType.L);
		putParam(ParamId.COA_DIALOG_CRITERIA_DATA, dto);
		selectCoa();
	}

	public void returnDepartment(SelectEvent event) {
		Department dept = (Department) event.getObject();
		dto.setDepartmentId(dept.getId());
		dto.setDeptCode(dept.getDCode());
	}

	public void returnBranch(SelectEvent event) {
		Branch branch = (Branch) event.getObject();
		dto.setBranchId(branch.getId());
		dto.setBranchCode(branch.getCode());
	}

	public List<Currency> getCurrencyList() {
		return currencyService.findAllCurrency();
	}

}
