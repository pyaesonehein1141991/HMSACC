package org.tech.java.component.idgen.persistence;

import javax.annotation.Resource;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.process.interfaces.IUserProcessService;
import org.tech.java.component.idgen.IDGen;
import org.tech.java.component.idgen.persistence.interfaces.IDGenDAOInf;
import org.tech.java.component.idgen.service.interfaces.IDConfigLoader;
import org.tech.java.component.persistence.BasicDAO;
import org.tech.java.component.persistence.exception.DAOException;

@Repository("IDGenDAO")
public class IDGenDAO extends BasicDAO implements IDGenDAOInf {
	@Resource(name = "IDConfigLoader")
	protected IDConfigLoader configLoader;

	@Resource(name = "UserProcessService")
	private IUserProcessService userProcessService;

	@Transactional(propagation = Propagation.REQUIRED)
	public IDGen getNextId(String generateItem, String branchId, int month, int year) throws DAOException {
		IDGen idGen = null;
		try {
			Query selectQuery = em.createQuery("SELECT g FROM IDGen g WHERE g.generateItem = :generateItem AND g.branch.id = :branchId AND g.month = :month AND g.year = :year");
			selectQuery.setLockMode(LockModeType.PESSIMISTIC_WRITE);
			selectQuery.setHint("javax.persistence.lock.timeout", 30000);
			selectQuery.setParameter("generateItem", generateItem);
			selectQuery.setParameter("branchId", branchId);
			selectQuery.setParameter("month", month);
			selectQuery.setParameter("year", year);
			idGen = (IDGen) selectQuery.getSingleResult();
			idGen.setMaxValue(idGen.getMaxValue() + 1);
			idGen = em.merge(idGen);

		} catch (

		PersistenceException pe) {
			throw translate("Failed to update ID Generation for " + generateItem, pe);
		}
		return idGen;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public IDGen getNextId(String generateItem) throws DAOException {
		IDGen idGen = null;
		try {
			Query selectQuery = em.createQuery("SELECT g FROM IDGen g WHERE g.generateItem = :generateItem AND g.branch.id IN (SELECT b.id FROM Branch b Where b.branchCode = :branchCode)");
			selectQuery.setLockMode(LockModeType.PESSIMISTIC_WRITE);
			selectQuery.setHint("javax.persistence.lock.timeout", 30000);
			selectQuery.setParameter("generateItem", generateItem);
			idGen = (IDGen) selectQuery.getSingleResult();
			idGen.setMaxValue(idGen.getMaxValue() + 1);
			idGen = em.merge(idGen);
		} catch (PersistenceException pe) {
			throw translate("Failed to update ID Generation for " + generateItem, pe);
		}
		return idGen;
	}


	@Transactional(propagation = Propagation.REQUIRED)
	public IDGen findCustomIDGenByBranchCodeMonthandYear(String generateItem, int month, int year, String branchId) throws DAOException {
		IDGen idGen = null;
		try {
			Query selectQuery = em.createQuery("SELECT g FROM IDGen g WHERE g.generateItem = :generateItem AND g.branch.id = :branchId AND g.month = :month AND g.year = :year");

			selectQuery.setParameter("generateItem", generateItem);
			selectQuery.setParameter("branchId", branchId);
			selectQuery.setParameter("month", month);
			selectQuery.setParameter("year", year);
			idGen = (IDGen) selectQuery.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			return null;

		} catch (PersistenceException pe) {
			throw translate("Failed to update ID Generation for " + generateItem, pe);
		}
		return idGen;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public IDGen insert(IDGen idGen) throws DAOException {
		try {
			em.persist(idGen);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert customIdGen", pe);
		}
		return idGen;
	}

}
