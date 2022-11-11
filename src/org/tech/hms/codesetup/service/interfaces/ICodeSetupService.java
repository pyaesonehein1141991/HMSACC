package org.tech.hms.codesetup.service.interfaces;

import java.util.List;

import org.tech.hms.codesetup.CodeSetup;

public interface ICodeSetupService {

	public List<CodeSetup> findAllCodeSetup();
	

	public void addNewCodeSetup(CodeSetup codeSetup);

	public void updateCodeSetup(CodeSetup codeSetup);

	public void deleteCodeSetup(CodeSetup codeSetup);
}
