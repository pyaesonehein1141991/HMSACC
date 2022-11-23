package org.tech.hms.web.system.openingbalance;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.tech.hms.process.interfaces.IUserProcessService;
import org.tech.java.web.common.BaseBean;

@Named(value = "ManageOpeningBalance")
@Scope("view")

public class ManageOpeningBalance extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IUserProcessService userProcessService;
	
	
	
	@PostConstruct
	public void init() {
		
	}

}
