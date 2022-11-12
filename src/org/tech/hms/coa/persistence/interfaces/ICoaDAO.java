package org.tech.hms.coa.persistence.interfaces;

import java.util.List;

import org.tech.hms.common.dto.coaDto.CoaDTO;
import org.tech.java.component.persistence.exception.DAOException;

public interface ICoaDAO {

	List<CoaDTO> findALLDTO() throws DAOException;

}
