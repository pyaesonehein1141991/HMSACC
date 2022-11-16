package org.tech.hms.codesetup.persistence.interfaces;

import java.util.List;

import org.tech.hms.codesetup.AccountCodeType;
import org.tech.java.component.persistence.exception.DAOException;

public interface ICodeSetupDAO {
	public List<AccountCodeType> findAll() throws DAOException;


}
