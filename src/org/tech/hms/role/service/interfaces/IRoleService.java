package org.tech.hms.role.service.interfaces;

import java.util.List;

import org.tech.hms.role.ROL001;
import org.tech.hms.role.Role;


public interface IRoleService {
	public Role addNewRole(Role role);

	public Role updateRole(Role role);

	public void deleteRole(Role role);

	public Role findRoleById(String id);

	public List<ROL001> findAllRole();

}
