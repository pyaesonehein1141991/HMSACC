package org.tech.hms.branch.persistence;

import org.springframework.stereotype.Repository;
import org.tech.hms.branch.persistence.interfaces.IBranchDAO;
import org.tech.java.component.persistence.BasicDAO;

@Repository(value = "BranchDAO")
public class BranchDAO extends BasicDAO implements IBranchDAO{

}
