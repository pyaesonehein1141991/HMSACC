package org.tech.hms.department.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.department.Department;
import org.tech.hms.department.persistence.interfaces.IDepartmentDAO;
import org.tech.hms.department.service.interfaces.IDepartmentService;
import org.tech.java.component.SystemException;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.DataRepService;

@Service(value = "DepartmentService")
public class DepartmentService extends DataRepService<DepartmentService> implements IDepartmentService {
	@Autowired
	private IDepartmentDAO departmentDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Department> findAllDepartment() {
		List<Department> result = null;
		try {
			result = departmentDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of Department)", e);
		}
		return result;
	}
}
