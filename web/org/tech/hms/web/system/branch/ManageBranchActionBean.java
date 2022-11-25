package org.tech.hms.web.system.branch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.tech.hms.branch.services.interfaces.IBranchService;
import org.tech.hms.common.dto.brachDto.BranchDTO;
import org.tech.java.component.SystemException;
import org.tech.java.web.common.BaseBean;

import lombok.Getter;

@Named(value = "ManageBranchActionBean")
@Scope(value = "view")
public class ManageBranchActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IBranchService branchService;

	private BranchDTO branchDTO;

	@Getter
	private List<BranchDTO> branchDtoList;

	@PostConstruct
	public void init() {
		initialization();
	}

	public void initialization() {
		branchDTO = new BranchDTO();
		branchDtoList = new ArrayList<>();
		branchDtoList = searchBranch();
	}

	public List<BranchDTO> searchBranch() {
		return branchService.findAllBranchDto();
	}

	public String prepareEditBranch(String branchId) {
		putParam("branchId", branchId);
		return "createNewBranch.xhtml?faces-redirect=true";
	}

	public String navigateBranchSetup() {
		return "createNewBranch.xhtml?faces-redirect=true";
	}

	public void deleteBranch(String id) {
		try {
			branchService.deleteById(id);
		} catch (SystemException e) {
			handleSysException(e);
		}
	}

}
