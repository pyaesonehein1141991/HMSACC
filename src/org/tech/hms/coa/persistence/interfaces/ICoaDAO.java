package org.tech.hms.coa.persistence.interfaces;

import java.util.List;

import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.common.dto.coaDto.CoaDTO;
import org.tech.java.component.persistence.exception.DAOException;

public interface ICoaDAO {

	List<CoaDTO> findALLDTO() throws DAOException;
	
	List<ChartOfAccount> findAll() throws DAOException;
	
	public ChartOfAccount findByIbsbACode(String ibsbACode) throws DAOException;
	
	public ChartOfAccount findByAcCode(String acCode) throws DAOException;
	
	public List<ChartOfAccount> findAllCOAByAccountCodeType() throws DAOException;

}
