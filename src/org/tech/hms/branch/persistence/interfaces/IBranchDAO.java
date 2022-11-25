package org.tech.hms.branch.persistence.interfaces;

import java.util.List;

import org.tech.hms.common.dto.brachDto.BranchDTO;
import org.tech.java.component.persistence.exception.DAOException;

public interface IBranchDAO {

	List<BranchDTO> findALLDTO() throws DAOException;

	void deleteById(String id) throws DAOException;

}
