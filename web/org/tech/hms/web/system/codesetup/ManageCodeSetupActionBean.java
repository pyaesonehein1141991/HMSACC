package org.tech.hms.web.system.codesetup;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.tech.hms.codesetup.CodeSetup;
import org.tech.hms.codesetup.service.interfaces.ICodeSetupService;
import org.tech.hms.common.validation.MessageId;
import org.tech.hms.currency.Currency;
import org.tech.hms.currency.service.interfaces.ICurrencyService;
import org.tech.java.component.SystemException;
import org.tech.java.web.common.BaseBean;

@Named(value = "ManageCodeSetupActionBean")
@Scope(value = "view")
public class ManageCodeSetupActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	protected ICodeSetupService codesetupService;

	

	private boolean createNew;
	private CodeSetup codeSetup;
	private List<CodeSetup> codeSetupList;

	@PostConstruct
	public void init() {
		createNewCodeSetup();
		loadCodeSetup();
	}

	public void createNewCodeSetup() {
		createNew = true;
		codeSetup = new CodeSetup();
	}

	public void loadCodeSetup() {
		codeSetupList = codesetupService.findAllCodeSetup();
	}


	public void addNewCodeSetup() {
		try {
			if (createNew) {
				codesetupService.addNewCodeSetup(codeSetup);
				loadCodeSetup();
			} else {
				codesetupService.updateCodeSetup(codeSetup);
				loadCodeSetup();
			}
			addInfoMessage(null, MessageId.SAVE_SUCCESS, codeSetup.getName());
			createNewCodeSetup();
		} catch (SystemException ex) {
			handleSysException(ex);
		}
	}

	public String deleteCodeSetup(CodeSetup codeSetup) {
		try {
			codesetupService.deleteCodeSetup(codeSetup);
			addInfoMessage(null, MessageId.DELETE_SUCCESS, codeSetup.getName());
			codeSetupList.remove(codeSetup);
		} catch (SystemException ex) {
			handleSysException(ex);
		}
		return null;
	}
	
	public void prepareUpdateCodeSetup(CodeSetup codeSetup) {
		createNew = false;
		this.codeSetup = codeSetup;
	}
	public void updateCodeSetup() {
			try {
				codesetupService.updateCodeSetup(codeSetup);
				addInfoMessage(null, MessageId.UPDATE_SUCCESS, codeSetup.getName());
				createNewCodeSetup();
				loadCodeSetup();
			} catch (SystemException ex) {
				handleSysException(ex);
			}
			loadCodeSetup();
		}

			
	public boolean isCreateNew() {
		return createNew;
	}

	public void setCreateNew(boolean createNew) {
		this.createNew = createNew;
	}

	/**
	 * @return the codesetupService
	 */
	public ICodeSetupService getCodesetupService() {
		return codesetupService;
	}

	/**
	 * @param codesetupService the codesetupService to set
	 */
	public void setCodesetupService(ICodeSetupService codesetupService) {
		this.codesetupService = codesetupService;
	}

	/**
	 * @return the codeSetup
	 */
	public CodeSetup getCodeSetup() {
		return codeSetup;
	}

	/**
	 * @param codeSetup the codeSetup to set
	 */
	public void setCodeSetup(CodeSetup codeSetup) {
		this.codeSetup = codeSetup;
	}

	/**
	 * @return the codeSetupList
	 */
	public List<CodeSetup> getCodeSetupList() {
		return codeSetupList;
	}

	/**
	 * @param codeSetupList the codeSetupList to set
	 */
	public void setCodeSetupList(List<CodeSetup> codeSetupList) {
		this.codeSetupList = codeSetupList;
	}


}
