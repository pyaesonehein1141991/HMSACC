package org.tech.hms.nrcPrefix.service.interfaces;

import java.util.List;

import org.tech.hms.nrcPrefix.NrcPrefix;
import org.tech.hms.user.User;
import org.tech.java.component.SystemException;

public interface INrcPrefixService {
	
	public List<NrcPrefix> findAllNrc() throws SystemException;

	public NrcPrefix findByNrcCode(String code) throws SystemException;
	
	public NrcPrefix findById(String id) throws SystemException;

	public NrcPrefix addNewNrc(NrcPrefix nrc) throws SystemException;

	public NrcPrefix updateNrc(NrcPrefix nrc) throws SystemException;

	public void deleteNrc(NrcPrefix nrc) throws SystemException;

}
