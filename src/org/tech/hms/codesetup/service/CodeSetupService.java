package org.tech.hms.codesetup.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.codesetup.CodeSetup;
import org.tech.hms.codesetup.persistence.interfaces.ICodeSetupDAO;
import org.tech.hms.codesetup.service.interfaces.ICodeSetupService;

import org.tech.java.component.SystemException;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.BaseService;
import org.tech.java.component.service.interfaces.IDataRepService;

@Service(value="CodeSetupService")
public class CodeSetupService extends BaseService implements ICodeSetupService {
	
	@Resource(name = "CodeSetupDAO")
	private ICodeSetupDAO codeSetupDAO;
	
	@Resource(name = "DataRepService")
	private IDataRepService<CodeSetup> dataRepService;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public List<CodeSetup> findAllCodeSetup() {
		List<CodeSetup> result = null;
		try {
			result = codeSetupDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of Code)", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void addNewCodeSetup(CodeSetup codeSetup) {
		try {
			dataRepService.insert(codeSetup);
	} catch (DAOException e) {
		throw new SystemException(e.getErrorCode(), "Failed to find all of User)", e);
	}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateCodeSetup(CodeSetup codeSetup) {
		try {
			dataRepService.update(codeSetup);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to update currency", e);
		}
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCodeSetup(CodeSetup codeSetup) throws SystemException{
		try {
		dataRepService.delete(codeSetup);
	} catch (DAOException e) {
		throw new SystemException(e.getErrorCode(), "Failed to delete user", e);
	}
	}

	

	
	

}
