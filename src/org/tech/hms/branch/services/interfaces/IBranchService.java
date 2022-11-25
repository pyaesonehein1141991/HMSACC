package org.tech.hms.branch.services.interfaces;

import java.util.List;

import org.tech.hms.common.dto.brachDto.BranchDTO;
import org.tech.java.component.SystemException;

public interface IBranchService {

	List<BranchDTO> findAllBranchDto() throws SystemException;

	void deleteById(String id) throws SystemException;

}
