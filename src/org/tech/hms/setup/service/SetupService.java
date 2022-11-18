package org.tech.hms.setup.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.setup.persistence.interfaces.ISetupDAO;
import org.tech.hms.setup.service.interfaces.ISetupService;
import org.tech.java.component.SystemException;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.BaseService;

@Service(value = "SetupService")
public class SetupService extends BaseService implements ISetupService {

	@Resource(name = "SetupDAO")
	private ISetupDAO setupDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public String findSetupValueByVariable(String variable) throws DAOException {
		String result = null;
		try {
			result = setupDAO.findSetupValueByVariable(variable);
		} catch (DAOException de) {
			throw new SystemException(de.getErrorCode(), "Failed to handle yearly posting.", de);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(String variable) throws DAOException {
		try {
			setupDAO.insert(variable);
		} catch (DAOException de) {
			throw new SystemException(de.getErrorCode(), "Failed to handle yearly posting.", de);
		}
	}
}
