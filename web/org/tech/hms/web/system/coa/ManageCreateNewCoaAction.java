package org.tech.hms.web.system.coa;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

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
	@Setter
	private List<AccountCodeType> codeSetupList;
	@Getter
	@Setter
	private List<ChartOfAccount> coaList;
	

	public void initialization() {

	}
	
	@PostConstruct
	public void init() {
		coaDTO = new CoaDTO();
		codeSetupList = codesetupService.findAllCodeSetup();
		root = new DefaultTreeNode("Root", null);
		selectedNodes = null;
		rebindData();
	}
	public void rebindData() {
		coaList = coaService.findAllCoa();
		TreeNode node = new DefaultTreeNode("ChartOfAccount", root);

		coaList.forEach(coa -> {
			node.getChildren().add(new DefaultTreeNode(coa, root));
		});
	}

	public AccountType[] getAcTypes() {
		return AccountType.values();
	}

}
