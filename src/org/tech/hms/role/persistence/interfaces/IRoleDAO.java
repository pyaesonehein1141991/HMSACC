package org.tech.hms.role.persistence.interfaces;

import java.util.List;

import org.tech.hms.role.ROL001;
import org.tech.java.component.persistence.exception.DAOException;


public interface IRoleDAO {
	public List<ROL001> findAll() throws DAOException;
}
