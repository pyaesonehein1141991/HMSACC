package org.tech.hms.coa.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

}
