package org.tech.hms.nrcPrefix.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.nrcPrefix.NrcPrefix;
import org.tech.hms.nrcPrefix.persistence.interfaces.INrcPrefixDAO;
import org.tech.hms.nrcPrefix.service.interfaces.INrcPrefixService;
import org.tech.java.component.SystemException;
import org.tech.java.component.persistence.BasicDAO;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.interfaces.IDataRepService;

@Service(value = "NrcPrefixService")
public class NrcPrefixService extends BasicDAO implements INrcPrefixService  {
	@Resource(name = "NrcPrefixDAO")
	private INrcPrefixDAO nrcDAO;

	@Resource(name = "DataRepService")
	private IDataRepService<NrcPrefix> dataRepService;

	@Transactional(propagation = Propagation.REQUIRED)
	public NrcPrefix addNewNrc(NrcPrefix nrc) throws SystemException {
		try {

			dataRepService.insert(nrc);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to add a NRC", e);
		}
		return nrc;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public NrcPrefix updateNrc(NrcPrefix nrc) throws SystemException {
		try {

			dataRepService.update(nrc);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to update NRC", e);
		}
		return nrc;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteNrc(NrcPrefix nrc) throws SystemException {
		try {
			dataRepService.delete(nrc);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to delete NRC", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<NrcPrefix> findAllNrc() throws SystemException {
		List<NrcPrefix> result = null;
		try {
			result = nrcDAO.findALl();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all NRC", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public NrcPrefix findByNrcCode(String code) throws SystemException {
		NrcPrefix nrc = nrcDAO.findByNrcTsp(code);
		return nrc;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public NrcPrefix findById(String id) throws SystemException {
		NrcPrefix result = null;
		try {
			result = nrcDAO.findById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find NrcPrefix with id " + id + ")", e);
		}
		return result;
	}

}
