package org.tech.hms.web.system.coa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.coa.service.interfaces.ICoaService;
import org.tech.hms.codesetup.AccountCodeType;
import org.tech.hms.codesetup.service.interfaces.ICodeSetupService;
import org.tech.hms.common.AccountType;
import org.tech.hms.common.dto.coaDto.CoaDTO;
import org.tech.hms.role.Role;
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
	protected ICodeSetupService codesetupService;
	@Getter
	@Setter
	private CoaDTO coaDTO;
	@Getter
	@Setter
	private TreeNode root;
	@Getter
	@Setter
	private TreeNode[] selectedNodes;
	@Getter
	private List<AccountCodeType> acCodeTypeList;
	

	public void initialization() {

	}
	
	@PostConstruct
	public void init() {
		coaDTO = new CoaDTO();
		acCodeTypeList = codesetupService.findAllCodeSetup();
	}

	

	public AccountType[] getAcTypes() {
		return AccountType.values();
	}
	
	public String createCoa() {
		try {
			ChartOfAccount coa = new ChartOfAccount(coaDTO);
			
			coaService.createCoa(coa);
		} catch (SystemException e) {
			handleSysException(e);
		}
		return null;
	}
	
	public String navManageCoa() {
		return null;
	}
	


}
