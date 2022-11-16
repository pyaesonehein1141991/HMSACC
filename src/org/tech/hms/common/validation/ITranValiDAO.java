package org.tech.hms.common.validation;

import org.tech.hms.branch.Branch;
import org.tech.hms.currency.Currency;
import org.tech.java.component.persistence.exception.DAOException;

public interface ITranValiDAO {

	public boolean isCurUsed(Currency cur) throws DAOException;

	public boolean isCoaUsed(String coaId) throws DAOException;

	public boolean isBranchUsed(Branch branch) throws DAOException;
}
