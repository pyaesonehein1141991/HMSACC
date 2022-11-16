package org.tech.hms.coa.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.coa.persistence.interfaces.ICoaDAO;
import org.tech.hms.coa.service.interfaces.ICoaService;
import org.tech.hms.common.dto.coaDto.CoaDTO;
import org.tech.java.component.SystemException;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.DataRepService;

@Service
public class CoaService extends DataRepService<ChartOfAccount> implements ICoaService {
	
	@Autowired
	private ICoaDAO coaDAO;
	
	//create coa
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createCoa(ChartOfAccount coa) {
		try {
			//TODO Business Logic
			insert(coa);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(),"Fail to create Chart of Account",e);
		}
	}
	
	
	//update coa
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ChartOfAccount updateCoa(ChartOfAccount coa) {
		
		try {
		return update(coa);	
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Fail to update Chart of Account",e);
		}
	}
	
	//delete coa
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteChartOfAccount(ChartOfAccount chartOfAccount) {
		try {
			
			delete(chartOfAccount);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to delete ChartOfAccount", e);
		}
	}
	
	//find all coa
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<ChartOfAccount> findAllCoa() {
		try {
			return coaDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of ChartOfAccount)", e);
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<CoaDTO> findAllDTO() {
		try {
			return coaDAO.findALLDTO();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of ChartOfAccount)", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ChartOfAccount findCoaByAcCode(String acCode) {
		ChartOfAccount chartOfAccount = null;
		try {
			chartOfAccount = coaDAO.findByAcCode(acCode);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find COA By AcCode.)", e);
		}
		return chartOfAccount;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ChartOfAccount findCoaByibsbAcCode(String ibsbACode) {
		ChartOfAccount chartOfAccount = null;
		try {
			chartOfAccount = coaDAO.findByIbsbACode(ibsbACode);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find COA By IBSBCode.)", e);
		}
		return chartOfAccount;
	}

}
