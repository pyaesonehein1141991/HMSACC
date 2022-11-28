package org.tech.hms.department.persistence.interfaces;

import java.util.List;

import org.tech.hms.department.Department;
import org.tech.java.component.persistence.exception.DAOException;

public interface IDepartmentDAO {

	List<Department> findAll() throws DAOException;

}
