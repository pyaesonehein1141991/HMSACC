package org.tech.java.component.idgen.persistence.interfaces;

import org.tech.java.component.idgen.IDGen;
import org.tech.java.component.persistence.exception.DAOException;

public interface IDGenDAOInf {
	public IDGen getNextId(String genName, int month, int year) throws DAOException;
	public IDGen getNextId(String genName) throws DAOException;
	public IDGen findCustomIDGenByBranchCodeMonthandYear(String generateItem, int month, int year) throws DAOException;
	public IDGen insert(IDGen idGen) throws DAOException;
}
