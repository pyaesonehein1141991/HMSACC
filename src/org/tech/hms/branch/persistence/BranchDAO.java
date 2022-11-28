package org.tech.hms.branch.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.branch.Branch;
import org.tech.hms.branch.persistence.interfaces.IBranchDAO;
import org.tech.hms.common.dto.brachDto.BranchDTO;
import org.tech.java.component.persistence.BasicDAO;
import org.tech.java.component.persistence.exception.DAOException;

@Repository(value = "BranchDAO")
public class BranchDAO extends BasicDAO implements IBranchDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<BranchDTO> findALLDTO() throws DAOException {
		try {
			TypedQuery<BranchDTO> q = em.createQuery("SELECT NEW " + BranchDTO.class.getName()
					+ "(b.id,b.name,b.code,b.description) FROM Branch b ORDER BY b.code ASC", BranchDTO.class);
			return q.getResultList();
		} catch (NoResultException ne) {
			return new ArrayList<>();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Branch", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(String id) throws DAOException {
		try {

		} catch (PersistenceException e) {
			throw translate("Failed to delete branch", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Branch> findAll() throws DAOException {
		List<Branch> result = null;
		try {
			TypedQuery<Branch> q = em.createNamedQuery("Branch.findAll", Branch.class);
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Branch", pe);
		}
		return result;
	}

}
