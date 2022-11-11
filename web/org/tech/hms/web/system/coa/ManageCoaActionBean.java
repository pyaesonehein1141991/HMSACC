package org.tech.hms.web.system.coa;

import java.io.Serializable;
import java.util.List;

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

@Named(value = "ManageCoaActionBean")
@Scope(value = "view")
public class ManageCoaActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ICoaService coaService;
	
	@Getter
	@Setter
	private CoaDTO coaDTO;
	
	@Getter
	private List<CoaDTO> coaDTOList;
	
	

	
	//init method
	@PostConstruct
	public void init() {
		
	}
	
	public void initialization() {
		
	}
	
	public void loadDTOList() {
		coaDTOList = coaService.findAllDTO();
	}
	
	public AccountType[] getAcTypes() {
		return AccountType.values();
	}
}
