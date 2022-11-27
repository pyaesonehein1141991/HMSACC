package org.tech.hms.coasetup.persistence.interfaces;

import org.tech.hms.branch.Branch;
import org.tech.hms.coasetup.COASetup;
import org.tech.hms.currency.Currency;
import org.tech.java.component.persistence.exception.DAOException;


public interface ICOASetupDAO {
	public COASetup findCOAByACNameAndCur(String acName, Currency currency) throws DAOException;

	

	//public List<COASetup> findCOAListByACNameAndCur(String acName, Currency currency, Branch branch) throws DAOException;

	//public List<COASetup> findAllCashAccount() throws DAOException;
}
