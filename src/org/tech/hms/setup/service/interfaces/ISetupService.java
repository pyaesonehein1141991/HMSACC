package org.tech.hms.setup.service.interfaces;

import org.tech.java.component.persistence.exception.DAOException;

public interface ISetupService {
	public String findSetupValueByVariable(String variable) throws DAOException;

	public void insert(String variable) throws DAOException;
}
