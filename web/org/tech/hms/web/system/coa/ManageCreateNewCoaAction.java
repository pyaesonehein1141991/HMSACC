package org.tech.hms.web.system.coa;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.tech.hms.coa.service.interfaces.ICoaService;
import org.tech.hms.common.AccountType;
import org.tech.hms.common.dto.coaDto.CoaDTO;
import org.tech.java.web.common.BaseBean;

import lombok.Getter;
import lombok.Setter;

@Named(value = "ManageCreateNewCoaAction")
@Scope(value = "view")
public class ManageCreateNewCoaAction extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ICoaService coaService;

	@Getter
	@Setter
	private CoaDTO coaDTO;

	public void initialization() {

	}
	
	@PostConstruct
	public void init() {
		coaDTO = new CoaDTO();
	}


	public AccountType[] getAcTypes() {
		return AccountType.values();
	}

}
