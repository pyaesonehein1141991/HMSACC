package org.tech.hms.web.system.nrcPrefix;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.tech.hms.common.validation.MessageId;
import org.tech.hms.nrcPrefix.NrcPrefix;
import org.tech.hms.nrcPrefix.service.interfaces.INrcPrefixService;
import org.tech.java.component.SystemException;
import org.tech.java.web.common.BaseBean;

@Named(value = "AddNewNrcPrefixActionBean")
@ViewScoped
public class AddNewNrcPrefixActionBean extends BaseBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	INrcPrefixService nrcPrefixService;
	
	private String nrcId ;
	private boolean createNew;
	private NrcPrefix nrc;
	
	@PostConstruct
	public void init() {
		System.out.println("AddNewNrcPrefixActionBean's @PostConstruct fired");
		if(nrcId == null) {
			createNewNrcPrefix();
		}else {
			createNew =false;
			nrc=nrcPrefixService.findById(nrcId);
			
		}
	}
	
	public void createNewNrcPrefix() {
		createNew = true;
		nrc = new NrcPrefix();
	}
	
	public String NrcPrefix() {
		try {
			return "addNewNrcPrefix";
		} catch (SystemException ex) {
			handleSysException(ex);
		}
		return "";
	}
	
	public void prepareUpdateUser(NrcPrefix nrc) {
		createNew = false;
		this.nrc = nrc;
	}
	
	public void saveNrcPrefix() {
		try {
			if (createNew) {
				nrcPrefixService.addNewNrc(nrc);
			} else {
				nrcPrefixService.updateNrc(nrc);
			}
			addInfoMessage(null, MessageId.SAVE_SUCCESS, nrc.gettownship());
		} catch (SystemException ex) {
			handleSysException(ex);
		}
	}
	
	public String navManageNrcPrefx() {
		return "manageNrcPrefix";
	}
	
	public boolean isCreateNew() {
		return createNew;
	}

	public NrcPrefix getNrc() {
		return nrc;
	}

	public void setNrc(NrcPrefix nrc) {
		this.nrc = nrc;
	}


	


	

	
}
