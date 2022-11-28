package org.tech.hms.common.validation;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tech.hms.branch.Branch;
import org.tech.hms.branch.persistence.interfaces.IBranchDAO;

@Service(value = "BranchCodeValidator")
public class BranchCodeValidator implements IDataValidator<Branch> {
	@Resource(name = "TranValiDAO")
	private ITranValiDAO tranValiDAO;

	@Resource(name = "BranchDAO")
	private IBranchDAO branchDAO;

	@Override
	public ValidationResult validate(Branch branch, boolean transaction) {
		ValidationResult result = new ValidationResult();
		String formId = "branchEntryForm";
		if (transaction) {
			if (tranValiDAO.isBranchUsed(branch)) {
				result.addErrorMessage(formId + ":panelBranch", MessageId.BRANCH_USED_TRANS);
			}
		}
		return result;
	}

}
