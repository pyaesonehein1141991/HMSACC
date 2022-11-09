package org.tech.hms.role.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.role.ROL001;
import org.tech.hms.role.Role;
import org.tech.hms.role.persistence.interfaces.IRoleDAO;
import org.tech.hms.role.service.interfaces.IRoleService;
import org.tech.java.component.SystemException;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.BaseService;
import org.tech.java.component.service.interfaces.IDataRepService;

@Service(value = "RoleService")
public class RoleService extends BaseService implements IRoleService {

	@Resource(name = "RoleDAO")
	private IRoleDAO roleDAO;
	
	@Resource(name = "DataRepService")
	private IDataRepService<Role> dataRepService;

	@Transactional(propagation = Propagation.REQUIRED)
	public Role addNewRole(Role role) {
		try {
			dataRepService.insert(role);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to add a Role", e);
		}
		return role;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Role updateRole(Role role) {
		try {
			role = dataRepService.update(role);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to update role", e);
		}
		return role;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteRole(Role role) {
		try {
			dataRepService.delete(role);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to delete role", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<ROL001> findAllRole() {
		List<ROL001> result = null;
		try {
			result = roleDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to fine all Role", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Role findRoleById(String id) {
		Role result = null;
		try {
			result = dataRepService.findById(Role.class, id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to fine all Role", e);
		}
		return result;
	}

}
