package org.tech.hms.codesetup.persistence.interfaces;

import java.util.List;

import org.tech.hms.codesetup.CodeSetup;
import org.tech.java.component.persistence.exception.DAOException;

public interface ICodeSetupDAO {
	public List<CodeSetup> findAll() throws DAOException;


}
