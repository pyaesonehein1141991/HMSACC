package org.tech.hms.coasetup.persistence.interfaces;

import java.util.List;

import org.tech.hms.common.TranCode;
import org.tech.hms.common.TranType;
import org.tech.java.component.persistence.exception.DAOException;

public interface ITranTypeDAO {
	
	public List<TranType> findAll() throws DAOException;
	
	public TranType findByTransCode(TranCode tranCode) throws DAOException;
}
