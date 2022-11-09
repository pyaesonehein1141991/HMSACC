package org.tech.hms.web.system.nrcPrefix;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.tech.hms.common.validation.MessageId;
import org.tech.hms.nrcPrefix.NrcPrefix;
import org.tech.hms.nrcPrefix.service.interfaces.INrcPrefixService;
import org.tech.java.component.SystemException;
import org.tech.java.web.common.BaseBean;


@Named(value = "ManageNrcPrefixActionBean")
@ViewScoped
public class ManageNrcPrefixActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	INrcPrefixService nrcPrefixService;
	
	private List<NrcPrefix> npList;
	
	@PostConstruct
	public void init() {
		System.out.println("ManageNrcPrefixActionBean's @PostContruct fired");
		rebindData();
	}

	private void rebindData() {
		npList = nrcPrefixService.findAllNrc();
	}
	public String navAddNewNrcPrefix() {
		return "addNewNrcPrefix";
	}
	
	public String prepareUpdateNrcPrefix(NrcPrefix nrc) {
		putParam("NRCID", nrc.getId());
		return "addNewNrcPrefix";
	}
	
	public String deleteNrcPrefix(NrcPrefix nrc) {
		try {
			nrcPrefixService.deleteNrc(nrc);
			addInfoMessage(null, MessageId.DELETE_SUCCESS, nrc.gettownship());
			rebindData();
		} catch (SystemException ex) {
			handleSysException(ex);
		}
		return null;
	}

	public List<NrcPrefix> getNpList() {
		return npList;
	}






}
