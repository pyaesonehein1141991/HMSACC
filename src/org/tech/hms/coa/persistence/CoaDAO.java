package org.tech.hms.coa.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.coa.persistence.interfaces.ICoaDAO;
import org.tech.hms.common.dto.coaDto.CoaDTO;
import org.tech.java.component.persistence.BasicDAO;
import org.tech.java.component.persistence.exception.DAOException;

@Repository
public class CoaDAO extends BasicDAO implements ICoaDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CoaDTO> findALLDTO() throws DAOException {
		try {
			TypedQuery<CoaDTO> q = em.createQuery("SELECT NEW " + CoaDTO.class.getName()
					+ "(c.id, c.acName, c.acCode, c.acType,c.ibsbACode,c.parent) FROM ChartOfAccount c ORDER BY c.acType ASC",
					CoaDTO.class);
			return q.getResultList();
		} catch (NoResultException ne) {
			return new ArrayList<>();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of User", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ChartOfAccount> findAll() throws DAOException {
		List<ChartOfAccount> result = null;
		try {
			TypedQuery<ChartOfAccount> q = em.createNamedQuery("ChartOfAccount.findAll",ChartOfAccount.class);
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of CodeSetup", pe);
		}
		return result;

	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ChartOfAccount findByAcCode(String acCode) throws DAOException {
		ChartOfAccount result = null;
		try {
			Query q = em.createNamedQuery("ChartOfAccount.findByAcCode");
			q.setParameter("acCode", acCode);
			result = (ChartOfAccount) q.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find ChartOfAccount with acCode : " + acCode, pe);
		}
		return result;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ChartOfAccount findByIbsbACode(String ibsbACode) throws DAOException {
		ChartOfAccount result = null;
		try {
			Query q = em.createNamedQuery("ChartOfAccount.findByIbsbACode");
			q.setParameter("ibsbACode", ibsbACode);
			result = (ChartOfAccount) q.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find ChartOfAccount with ibsbACode : " + ibsbACode, pe);
		}
		return result;
	}
}
