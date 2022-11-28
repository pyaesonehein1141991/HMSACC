package org.tech.hms.web.system.coa;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.coa.service.interfaces.ICoaService;
import org.tech.hms.codesetup.AccountCodeType;
import org.tech.hms.common.AccountType;
import org.tech.hms.common.dto.coaDto.CoaDTO;
import org.tech.hms.common.validation.ErrorMessage;
import org.tech.hms.common.validation.IDataValidator;
import org.tech.hms.common.validation.MessageId;
import org.tech.hms.common.validation.ValidationResult;
import org.tech.java.component.SystemException;
import org.tech.java.component.service.interfaces.IDataRepService;
import org.tech.java.web.common.BaseBean;

import lombok.Getter;
import lombok.Setter;

@Named(value = "ManageCoaActionBean")
@Scope(value = "view")
public class ManageCoaActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ICoaService coaService;

	@Autowired
	private IDataValidator<ChartOfAccount> accountCodeValidator;

	@Autowired
	@Qualifier(value = "CoaService")
	private IDataRepService<ChartOfAccount> dataRepService;

	@Getter
	@Setter
	private CoaDTO coaDTO;

	@Getter
	private List<CoaDTO> coaDTOList;

	@PostConstruct
	public void init() {
		loadDTOList();
	}

	public void initialization() {

	}

	public void loadDTOList() {
		coaDTOList = coaService.findAllDTO();
	}

	public AccountType[] getAcTypes() {
		return AccountType.values();
	}

	public String createNewCoa() {
		return "manageNewChartOfAccount.xhtml?faces-redirect=true";
	}

	public String prepareUpdateCoa(CoaDTO dto) {
		putParam("coaDto", dto);
		return "manageNewChartOfAccount.xhtml?faces-redirect=true";
	}

	public String deleteCoa(CoaDTO dto) {
		ChartOfAccount coa = dataRepService.findById(ChartOfAccount.class, dto.getId());
		ValidationResult result = accountCodeValidator.validate(coa, true);
		if (result.isVerified()) {
			try {
				coaService.deleteChartOfAccount(coa);
				coaDTOList.removeIf(d -> d.getId().equalsIgnoreCase(coa.getId()));
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

	public AccountCodeType[] getAccountCodeTypes() {
		return AccountCodeType.values();
	}
}
