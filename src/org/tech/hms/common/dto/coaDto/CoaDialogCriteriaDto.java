package org.tech.hms.common.dto.coaDto;

import java.util.ArrayList;
import java.util.List;

import org.tech.hms.codesetup.AccountCodeType;
import org.tech.hms.common.AccountType;

import lombok.Data;

@Data
public class CoaDialogCriteriaDto {
	private AccountCodeType accountCodeType;
	private List<AccountType> accountTypes;

	public CoaDialogCriteriaDto() {
		accountTypes = new ArrayList<AccountType>();
	}
	public void addAccountTypes(AccountType acType) {
		if (!accountTypes.contains(acType)) {
			accountTypes.add(acType);
		}
	}

}
