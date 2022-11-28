package org.tech.hms.branch.services.interfaces;

import java.util.List;

import org.tech.hms.branch.Branch;
import org.tech.hms.common.dto.brachDto.BranchDTO;
import org.tech.java.component.SystemException;

public interface IBranchService {

	List<BranchDTO> findAllBranchDto() throws SystemException;

	void deleteById(String id) throws SystemException;

	List<Branch> findAll();

	void insert(Object branch);

	public Branch update(Branch branch);

	public void delete(Object object);

	public Branch findById(Class<Branch> paramClass, Object paramObject);

}
