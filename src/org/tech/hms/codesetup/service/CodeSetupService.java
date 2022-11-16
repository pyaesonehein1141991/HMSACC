package org.tech.hms.codesetup.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.codesetup.AccountCodeType;
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
	private IDataRepService<AccountCodeType> dataRepService;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public List<AccountCodeType> findAllCodeSetup() {
		List<AccountCodeType> result = null;
		try {
			result = codeSetupDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of Code)", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void addNewCodeSetup(AccountCodeType accountCodeType) {
		try {
			dataRepService.insert(accountCodeType);
	} catch (DAOException e) {
		throw new SystemException(e.getErrorCode(), "Failed to find all of User)", e);
	}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateCodeSetup(AccountCodeType accountCodeType) {
		try {
			dataRepService.update(accountCodeType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to update currency", e);
		}
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCodeSetup(AccountCodeType accountCodeType) throws SystemException{
		try {
		dataRepService.delete(accountCodeType);
	} catch (DAOException e) {
		throw new SystemException(e.getErrorCode(), "Failed to delete user", e);
	}
	}

	

	
	

}
