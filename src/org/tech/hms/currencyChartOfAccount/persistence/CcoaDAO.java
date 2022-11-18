package org.tech.hms.currencyChartOfAccount.persistence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.branch.Branch;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.common.dto.coaDto.YearlyBudgetDto;
import org.tech.hms.currency.Currency;
import org.tech.hms.currencyChartOfAccount.CurrencyChartOfAccount;
import org.tech.hms.currencyChartOfAccount.persistence.interfaces.ICcoaDAO;
import org.tech.hms.process.interfaces.IUserProcessService;
import org.tech.java.component.persistence.BasicDAO;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.interfaces.IDataRepService;

@Repository("CcoaDAO")
public class CcoaDAO extends BasicDAO implements ICcoaDAO {

	@Resource(name = "UserProcessService")
	private IUserProcessService userProcessService;

	@Resource(name = "DataRepService")
	private IDataRepService<CurrencyChartOfAccount> ccoaDataRepService;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CurrencyChartOfAccount> findAll() throws DAOException {
		List<CurrencyChartOfAccount> list = null;
		try {
			Query q = em.createNamedQuery("CurrencyChartOfAccount.findAll");
			list = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of CurrencyChartOfAccount", pe);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CurrencyChartOfAccount> findCCOAByCOAid(String coaId) throws DAOException {
		List<CurrencyChartOfAccount> list = null;
		try {
			Query q = em.createNamedQuery("CurrencyChartOfAccount.findCCOAByCOAid");
			q.setParameter("id", coaId);
			list = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find CurrencyChartOfAccount by coaid", pe);
		}
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CurrencyChartOfAccount findSpecificCCOA(String coaId, String currencyId, String branchId, String optionalDepartmentId) throws DAOException {
		CurrencyChartOfAccount result = null;
		try {
			StringBuffer queryString = new StringBuffer(
					"SELECT c FROM CurrencyChartOfAccount c WHERE c.coa.id=:coaId AND c.currency.id=:currencyId " + " AND c.branch.id=:branchId ");
			if (optionalDepartmentId != null) {
				queryString.append(" AND c.department.id=:optionalDepartmentId ");
			}
			Query q = em.createQuery(queryString.toString());
			q.setParameter("coaId", coaId);
			q.setParameter("currencyId", currencyId);
			q.setParameter("branchId", branchId);
			if (optionalDepartmentId != null) {
				q.setParameter("optionalDepartmentId", optionalDepartmentId);
			}

			result = (CurrencyChartOfAccount) q.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find specific CurrencyChartOfAccount", pe);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CurrencyChartOfAccount> findCOAByBranchID(String branchID) throws DAOException {
		List<CurrencyChartOfAccount> list = null;
		try {
			Query q = em.createNamedQuery("CurrencyChartOfAccount.findCOAByBranchID");
			q.setParameter("branchId", branchID);
			list = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of CurrencyChartOfAccount", pe);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<String> findAllBudgetYear() throws DAOException {
		List<String> list = null;
		try {
			Query q = em.createNamedQuery("CurrencyChartOfAccount.findAllBudget");
			list = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of CurrencyChartOfAccount", pe);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<YearlyBudgetDto> findAllYearlyBudget(YearlyBudgetDto dto) throws DAOException {
		List<YearlyBudgetDto> result = null;
		try {
			StringBuffer sf = new StringBuffer(
					"SELECT new org.ace.accounting.dto.YearlyBudgetDto(c.id, c.coa.acCode, c.coa.acName, c.currency.currencyCode, d.dCode, c.branch.branchCode, c.bF)");
			sf.append(" FROM CurrencyChartOfAccount c LEFT OUTER JOIN c.department d WHERE c.coa.id!=null");
			if (!(dto.getCoaId() == null || dto.getCoaId().isEmpty())) {
				sf.append(" AND c.coa.id=:coaId");
			}
			if (!(dto.getBranchId() == null || dto.getBranchId().isEmpty())) {
				sf.append(" AND c.branch.id=:branchId ");
			}
			if (!(dto.getCurId() == null || dto.getCurId().isEmpty())) {
				sf.append(" AND c.currency.id=:currencyId");
			}
			if (!(dto.getDepartmentId() == null || dto.getDepartmentId().isEmpty())) {
				sf.append(" AND c.department.id=:departmentId");
			}
			sf.append(" ORDER BY  c.coa.acCode, c.currency.currencyCode, d.dCode, c.branch.branchCode");

			Query q = em.createQuery(sf.toString());

			if (!(dto.getCoaId() == null || dto.getCoaId().isEmpty())) {
				q.setParameter("coaId", dto.getCoaId());
			}
			if (!(dto.getBranchId() == null || dto.getBranchId().isEmpty())) {
				q.setParameter("branchId", dto.getBranchId());
			}
			if (!(dto.getCurId() == null || dto.getCurId().isEmpty())) {
				q.setParameter("currencyId", dto.getCurId());
			}
			if (!(dto.getDepartmentId() == null || dto.getDepartmentId().isEmpty())) {
				q.setParameter("departmentId", dto.getDepartmentId());
			}
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of CurrencyChartOfAccount", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateYearlyBudget(YearlyBudgetDto dto) throws DAOException {
		try {
			Query q = em.createNamedQuery("CurrencyChartOfAccount.updateBudgetYearByID");
			q.setParameter("id", dto.getCcoaId());
			q.setParameter("bF", dto.getbF());
			q.executeUpdate();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of CurrencyChartOfAccount", pe);
		}
	}

	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CurrencyChartOfAccount> findBudgetFigure(String branchId) throws DAOException {
		List<CurrencyChartOfAccount> result = null;
		try {
			StringBuffer queryString = new StringBuffer();
			queryString.append(
					"SELECT c FROM CurrencyChartOfAccount c WHERE c.coa.acCodeType != org.ace.accounting.system.chartaccount.AccountCodeType.HEAD AND c.coa.acCodeType != org.ace.accounting.system.chartaccount.AccountCodeType.GROUP ");

			if (branchId != null) {
				queryString.append(" AND c.branch.id=:branchId  ");
			}

			queryString.append(" ORDER BY c.coa.acType ");

			Query q = em.createQuery(queryString.toString());

			if (branchId != null) {
				q.setParameter("branchId", branchId);
			}
			result = q.getResultList();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find Budget Figure with branchId : " + branchId, pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public BigDecimal finddblBalance(StringBuffer sf, ChartOfAccount coa, String budgetYear, Currency currency, Branch branch) throws DAOException {
		BigDecimal result = null;
		try {
			Query query = em.createQuery(sf.toString());
			query.setParameter("coa", coa);
			query.setParameter("budgetYear", budgetYear);
			if (currency != null) {
				query.setParameter("currency", currency);
			}
			if (branch != null) {
				query.setParameter("branch", branch);
			}
			result = (BigDecimal) query.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find CurrencyChartOfAccount", pe);
		}
		return (result == null) ? BigDecimal.ZERO : result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CurrencyChartOfAccount findCCOAByIdAndBranch(String coaId, Branch branch, Currency currency) throws DAOException {
		CurrencyChartOfAccount result = null;
		try {
			Query q = em.createNamedQuery("CurrencyChartOfAccount.findCCOAByIdAndBranch");
			q.setParameter("coaId", coaId);
			q.setParameter("branch", branch);
			q.setParameter("currency", currency);
			result = (CurrencyChartOfAccount) q.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find CurrencyChartOfAccount with coaId " + coaId, pe);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CurrencyChartOfAccount> findCOAByCurrencyID(String currencyID) throws DAOException {
		List<CurrencyChartOfAccount> list = null;
		try {
			Query q = em.createNamedQuery("CurrencyChartOfAccount.findCCOAByCurrencyID");
			q.setParameter("curid", currencyID);
			list = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of CurrencyChartOfAccount", pe);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ChartOfAccount> findAllCOAByAccountCodeType() throws DAOException {
		List<ChartOfAccount> result = null;
		try {
			Query q = em.createNamedQuery("ChartOfAccount.findAllCOAByAccountCodeType");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of ChartOfAccount", pe);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CurrencyChartOfAccount> findCCOAByBranchID(String branchID) throws DAOException {
		List<CurrencyChartOfAccount> list = null;
		try {
			Query q = em.createNamedQuery("CurrencyChartOfAccount.findCCOAByBranchID");
			q.setParameter("branchid", branchID);
			list = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of CurrencyChartOfAccount", pe);
		}
		return list;
	}

	

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CurrencyChartOfAccount> findCCOAByCOAIDAndBranchId(String coaId, String branchId) throws DAOException {
		try {
			TypedQuery<CurrencyChartOfAccount> q = em.createQuery("SELECT c FROM CurrencyChartOfAccount c where c.coa.id =:coaId and c.branch.id=:branchId",
					CurrencyChartOfAccount.class);
			q.setParameter("coaId", coaId);
			q.setParameter("branchId", branchId);
			return q.getResultList();
		} catch (NoResultException pe) {
			return new ArrayList<>();
		} catch (PersistenceException pe) {
			throw translate("Failed to find CurrencyChartOfAccount with coaId " + coaId, pe);
		}
	}


	// Accumulated surplus or deficit
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CurrencyChartOfAccount> findCCOAByCOAIDAndBranchIdForHOClosing(String coaId, String branchId) throws DAOException {
		try {
			TypedQuery<CurrencyChartOfAccount> q = em.createQuery("SELECT c FROM CurrencyChartOfAccount c where c.coa.id =:coaId and c.branch.id=:branchId",
					CurrencyChartOfAccount.class);
			q.setParameter("coaId", coaId);
			q.setParameter("branchId", branchId);
			return q.getResultList();
		} catch (NoResultException pe) {
			return new ArrayList<>();
		} catch (PersistenceException pe) {
			throw translate("Failed to find CurrencyChartOfAccount with coaId " + coaId, pe);
		}
	}

	@Override
	public List<CurrencyChartOfAccount> findCCOAforBranchClosing(String branchId) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CurrencyChartOfAccount> findCCOAforHOClosing(String branchId) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
