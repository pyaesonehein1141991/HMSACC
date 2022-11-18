package org.tech.hms.web.system.coa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.coa.service.interfaces.ICoaService;
import org.tech.hms.codesetup.AccountCodeType;
import org.tech.hms.common.AccountType;
import org.tech.hms.common.validation.ErrorMessage;
import org.tech.hms.common.validation.IDataValidator;
import org.tech.hms.common.validation.MessageId;
import org.tech.hms.common.validation.ValidationResult;
import org.tech.java.component.SystemException;
import org.tech.java.web.common.BaseBean;

import lombok.Getter;
import lombok.Setter;

@Named(value = "ManageCreateNewCoaAction")
@Scope(value = "view")
public class ManageCreateNewCoaAction extends BaseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICoaService coaService;

	@Autowired
	private IDataValidator<ChartOfAccount> accountCodeValidator;

	
	@Getter
	@Setter
	private boolean createNew;
	@Getter
	@Setter
	private ChartOfAccount coa;
	@Getter
	@Setter
	private List<ChartOfAccount> coaList;
	@Getter
	@Setter
	private List<ChartOfAccount> groupList;
	@Getter
	@Setter
	private List<ChartOfAccount> headList;
	@Getter
	@Setter
	private boolean headCodeDisable = false;
	@Getter
	@Setter
	private boolean groupCodeDisable = true;
	@Getter
	@Setter
	private boolean acCodeDisabled = false;


	@PostConstruct
	public void init() {
		loadData();
		createNewCoa();
	}

	public void createNewCoa() {
		acCodeDisabled = false;
		createNew = true;
		coa = new ChartOfAccount();
		coa.setAcType(AccountType.A);
		coa.setAcCodeType(AccountCodeType.DETAIL);
		loadHeadList();
		eventAcCodeType();
	}

	private void loadData() {
		coaList = coaService.findAllCoa();
		// sort();
	}

	public void prepareUpdateCoa(ChartOfAccount coa) {
		acCodeDisabled = true;
		createNew = false;
		this.coa = coa;
		loadHeadList();
		eventAcCodeType();
		loadGroupList();
	}

	public void loadHeadList() {
		if(null != coaList || !coaList.isEmpty()) {
			headList = coaList.stream().filter(
					temp -> temp.getAcCodeType().equals(AccountCodeType.HEAD) && temp.getAcType().equals(coa.getAcType()))
					.collect(Collectors.toList());
		}else {
			headList = new ArrayList<>();
		}
		
	}

	public void eventAcCodeType() {
		switch (coa.getAcCodeType()) {
		case DETAIL:
			headCodeDisable = false;
			groupCodeDisable = false;
			break;
		case GROUP:
			headCodeDisable = false;
			groupCodeDisable = true;
			coa.setGroupId(null);
			break;
		case HEAD:
			headCodeDisable = true;
			groupCodeDisable = true;
			coa.setGroupId(null);
			coa.setHeadId(null);
			break;
		}

	}

	public void loadGroupList() {
		if (coa.getAcCodeType().equals(AccountCodeType.DETAIL)) {
			groupList = coaList.stream().filter(temp -> (temp.getAcCodeType().equals(AccountCodeType.GROUP)
					&& temp.getHeadId().equals(coa.getHeadId()))).collect(Collectors.toList());
			groupCodeDisable = false;
		} else {
			groupCodeDisable = true;
		}
	}

	public void addNewCoa() {
		ValidationResult result = accountCodeValidator.validate(coa, false);
		if (result.isVerified()) {
			try {
				coaService.createCoa(coa);
				coaList.add(coa);
				addInfoMessage(null, MessageId.INSERT_SUCCESS, coa.getAcCode());
				createNewCoa();
			} catch (SystemException ex) {
				handleSysException(ex);
			}
		} else {
			for (ErrorMessage message : result.getErrorMeesages()) {
				addErrorMessage(message.getId(), message.getErrorcode(), message.getParams());
			}
		}
	}

	public void updateCoa() {
		ValidationResult result = accountCodeValidator.validate(coa, false);
		if (result.isVerified()) {
			try {
				coaService.updateCoa(coa);
				updateList(coa);
				addInfoMessage(null, MessageId.UPDATE_SUCCESS, coa.getAcCode());
				createNewCoa();
			} catch (SystemException ex) {
				handleSysException(ex);
			}
		} else {

			for (ErrorMessage message : result.getErrorMeesages()) {
				addErrorMessage(message.getId(), message.getErrorcode(), message.getParams());
			}
		}
	}

	public String deleteCoa(ChartOfAccount coa) {
		this.coa = coa;
		ValidationResult result = accountCodeValidator.validate(coa, true);
		if (result.isVerified()) {
			try {
				coaService.deleteChartOfAccount(coa);
				coaList.remove(coa);
				addInfoMessage(null, MessageId.DELETE_SUCCESS, coa.getAcCode());
				createNewCoa();
			} catch (SystemException ex) {
				handleSysException(ex);
			}

		} else {
			for (ErrorMessage message : result.getErrorMeesages()) {
				addErrorMessage(message.getId(), message.getErrorcode(), message.getParams());
			}
		}

		return null;
	}

	private void updateList(ChartOfAccount coa) {
		ChartOfAccount tempCoa = ((List<ChartOfAccount>) coaList.stream().filter(p -> p.getId().equals(coa.getId()))
				.collect(Collectors.toList())).get(0);
		coaList.remove(tempCoa);
		// ChartOfAccount newCoa = dataservice.findById(ChartOfAccount.class,
		// coa.getId());
		// coaList.add(newCoa);
		sort();
	}

	private void sort() {
		Comparator<ChartOfAccount> c = Comparator.comparing(ChartOfAccount::getAcType)
				.thenComparing(ChartOfAccount::getAcCodeType).thenComparing(ChartOfAccount::getAcCode);
		coaList.sort(c);
	}

	public AccountType[] getAcTypes() {
		return AccountType.values();
	}

	public AccountCodeType[] getAcCodeTypes() {
		return AccountCodeType.values();
	}


}
