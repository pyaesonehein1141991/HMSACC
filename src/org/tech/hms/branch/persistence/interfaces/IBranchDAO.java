package org.tech.hms.branch.persistence.interfaces;

import java.util.List;

import org.tech.hms.branch.Branch;

public interface IBranchDAO {
	
	public List<Branch> findAllBranch();
	public Branch findById(String id);

}
