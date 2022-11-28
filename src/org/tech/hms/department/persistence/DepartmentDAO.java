package org.tech.hms.department.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.department.Department;
import org.tech.hms.department.persistence.interfaces.IDepartmentDAO;
import org.tech.java.component.persistence.BasicDAO;
import org.tech.java.component.persistence.exception.DAOException;

@Repository("DepartmentDAO")
public class DepartmentDAO extends BasicDAO implements IDepartmentDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Department> findAll() throws DAOException {
		List<Department> result = null;
		try {
			TypedQuery<Department> q = em.createNamedQuery("Department.findAll", Department.class);
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Department", pe);
		}
		return result;
	}

}
