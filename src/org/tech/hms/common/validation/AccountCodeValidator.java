package org.tech.hms.common.validation;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.coa.service.interfaces.ICoaService;


@Service(value = "AccountCodeValidator")
public class AccountCodeValidator implements IDataValidator<ChartOfAccount> {

	@Resource(name = "CoaService")
	private ICoaService coaService;

	@Resource(name = "TranValiDAO")
	private ITranValiDAO tranValiDAO;

	@Override
	public ValidationResult validate(ChartOfAccount coa, boolean transaction) {

		ValidationResult result = new ValidationResult();

		String formId = "coaEntryForm";

		if (transaction) {
			if (tranValiDAO.isCoaUsed(coa.getId())) {
				result.addErrorMessage(null, MessageId.TRANS_USED, coa.getAcCode());
			}

		} else {
			ChartOfAccount temp = coaService.findCoaByAcCode(coa.getAcCode());
			if (temp != null && !temp.getId().equals(coa.getId())) {
				result.addErrorMessage(formId + ":acCode", MessageId.ACCODE_EXISTED, temp.getAcCode(), temp.getAcType());
			}

			if (coa.getIbsbACode() != null && !coa.getIbsbACode().isEmpty()) {
				temp = coaService.findCoaByibsbAcCode(coa.getIbsbACode());
				if (temp != null && !temp.getId().equals(coa.getId())) {
					result.addErrorMessage(formId + ":ibsbACode", MessageId.IBSBCODE_EXISTED, temp.getIbsbACode(), temp.getAcCode(), temp.getAcType());
				}
			}

		}

		return result;
	}

}
