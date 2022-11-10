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
import org.tech.java.component.persistence.BasicDAO;
import org.tech.java.component.persistence.exception.DAOException;

@Repository(value = "BranchDAO")
public class BranchDAO extends BasicDAO implements IBranchDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Branch> findAllBranch() throws DAOException{
		try {
			TypedQuery<Branch> query = em.createNamedQuery("Branch.findAll", Branch.class);
			return query.getResultList();
		} catch (NoResultException ne) {
			return new ArrayList<>();
		} catch (PersistenceException pe) {
			throw translate("Fail to find all branch", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Branch findById(String id) throws DAOException{
		try {
			TypedQuery<Branch> q = em.createNamedQuery("Branch.findById", Branch.class);
			q.setParameter("id", id);
			return q.getSingleResult();
			
		} catch (NoResultException ne) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Fail to find Branch by Id: "+id, pe);
		}
	}

}
