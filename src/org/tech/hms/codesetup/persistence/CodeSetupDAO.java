package org.tech.hms.codesetup.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.codesetup.CodeSetup;
import org.tech.hms.codesetup.persistence.interfaces.ICodeSetupDAO;
import org.tech.java.component.persistence.BasicDAO;
import org.tech.java.component.persistence.exception.DAOException;

@Repository("CodeSetupDAO")
public class CodeSetupDAO extends BasicDAO implements ICodeSetupDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CodeSetup> findAll() throws DAOException {
		List<CodeSetup> result = null;
		try {
			Query q = em.createNamedQuery("CodeSetup.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of CodeSetup", pe);
		}
		return result;
	}
		

}
