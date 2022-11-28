package org.tech.hms.web.system.branch;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.tech.hms.branch.Branch;
import org.tech.hms.branch.services.interfaces.IBranchService;
import org.tech.hms.common.validation.ErrorMessage;
import org.tech.hms.common.validation.IDataValidator;
import org.tech.hms.common.validation.MessageId;
import org.tech.hms.common.validation.ValidationResult;
import org.tech.java.component.SystemException;
import org.tech.java.web.common.BaseBean;

@Named(value = "AddNewBranchActionBean")
@Scope(value = "view")
public class AddNewBranchActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IBranchService branchService;

	public void setBranchService(IBranchService branchService) {
		this.branchService = branchService;
	}

	@Autowired
	protected IDataValidator<Branch> branchCodeValidator;

	private boolean createNew;
	private Branch branch;
	private List<Branch> branchList;

	@PostConstruct
	public void init() {
		createNewBranch();
		rebindData();
	}

	public void createNewBranch() {
		createNew = true;
		branch = new Branch();
	}

	public void rebindData() {
		branchList = branchService.findAll();
	}

	public void prepareUpdateBranch(Branch branch) {
		createNew = false;
		this.branch = branch;
	}

	public void addNewBranch() {
		try {
			branchService.insert(branch);
			addInfoMessage(null, MessageId.INSERT_SUCCESS, branch.getName());
			createNewBranch();
			rebindData();
		} catch (SystemException ex) {
			handleSysException(ex);
		}
	}

	public void updateBranch() {
		try {
			branchService.update(branch);
			addInfoMessage(null, MessageId.UPDATE_SUCCESS, branch.getName());
			createNewBranch();
			rebindData();
		} catch (SystemException ex) {
			handleSysException(ex);
		}
	}

	public String deleteBranch(Branch branch) {
		ValidationResult result = branchCodeValidator.validate(branch, true);
		if (result.isVerified()) {
			try {
				branchService.delete(branch);
				addInfoMessage(null, MessageId.DELETE_SUCCESS, branch.getName());
			} catch (SystemException ex) {
				handleSysException(ex);
			}
		} else {
			for (ErrorMessage message : result.getErrorMeesages()) {
				addErrorMessage(null, message.getErrorcode(), message.getParams());
			}
		}

		createNewBranch();
		rebindData();
		return null;
	}

	public boolean isCreateNew() {
		return createNew;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<Branch> getBranchList() {
		return branchList;
	}

}
