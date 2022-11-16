package org.tech.hms.codesetup.service.interfaces;

import java.util.List;

import org.tech.hms.codesetup.AccountCodeType;

public interface ICodeSetupService {

	public List<AccountCodeType> findAllCodeSetup();
	

	public void addNewCodeSetup(AccountCodeType accountCodeType);

	public void updateCodeSetup(AccountCodeType accountCodeType);

	public void deleteCodeSetup(AccountCodeType accountCodeType);
}
