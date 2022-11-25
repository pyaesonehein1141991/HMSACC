package org.tech.hms.branch.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.branch.Branch;
import org.tech.hms.branch.persistence.interfaces.IBranchDAO;
import org.tech.hms.branch.services.interfaces.IBranchService;
import org.tech.hms.common.dto.brachDto.BranchDTO;
import org.tech.java.component.SystemException;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.DataRepService;

@Service(value = "BranchService")
public class BranchService extends DataRepService<Branch> implements IBranchService {

	@Autowired
	private IBranchDAO branchDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<BranchDTO> findAllBranchDto() throws SystemException {
		try {
			return branchDAO.findALLDTO();
		} catch (DAOException de) {
			throw new SystemException(de.getErrorCode(), "Fail to find all branch", de);
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(String id) throws SystemException {
		try {
			branchDAO.deleteById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Fail to find all branch", e);
		}

	}

}
