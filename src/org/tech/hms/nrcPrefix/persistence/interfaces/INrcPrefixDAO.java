package org.tech.hms.nrcPrefix.persistence.interfaces;

import java.util.List;

import org.tech.hms.nrcPrefix.NrcPrefix;
import org.tech.java.component.persistence.exception.DAOException;

public interface INrcPrefixDAO  {
	
	public List<NrcPrefix> findALl() throws DAOException;
	public NrcPrefix findByNrcTsp(String code) throws DAOException;
	public NrcPrefix findById(String id) throws DAOException;

}
