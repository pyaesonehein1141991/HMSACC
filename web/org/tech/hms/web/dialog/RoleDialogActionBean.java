package org.tech.hms.web.dialog;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.tech.hms.role.ROL001;
import org.tech.hms.role.Role;
import org.tech.hms.role.service.interfaces.IRoleService;
import org.tech.java.web.common.BaseBean;

@ManagedBean(name = "RoleDialogActionBean")
@ViewScoped
public class RoleDialogActionBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{RoleService}")
	private IRoleService roleService;

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	private List<ROL001> roleList;

	@PostConstruct
	public void init() {
		roleList = roleService.findAllRole();
	}

	public List<ROL001> getRoleList() {
		return roleList;
	}

	public void selectRole(ROL001 rol001) {
		Role role = roleService.findRoleById(rol001.getId());
		PrimeFaces.current().dialog().closeDynamic(role);
	}
}
