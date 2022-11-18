package org.tech.hms.currency.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.common.BusinessUtil;
import org.tech.hms.common.dto.coaDto.MonthlyRateDto;
import org.tech.hms.currency.Currency;
import org.tech.hms.currency.persistence.interfaces.ICurrencyDAO;
import org.tech.java.component.persistence.BasicDAO;
import org.tech.java.component.persistence.exception.DAOException;

@Repository("CurrerncyDAO")
public class CurrerncyDAO extends BasicDAO implements ICurrencyDAO{

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Currency> findForeignCurrency() throws DAOException {
		List<Currency> result = null;
		try {
			Query q = em.createNamedQuery("Currency.findForeignCurrency");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Currency", pe);
		}
		return result;
	}

	@Override
	public List<Currency> findAll() throws DAOException {
		List<Currency> result = null;
		try {
			Query q = em.createNamedQuery("Currency.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Currency", pe);
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MonthlyRateDto> findForeignCurrencyDto() throws DAOException {
		List<MonthlyRateDto> result = null;
		try {
			StringBuffer sf = new StringBuffer();
			sf.append("SELECT NEW org.ace.accounting.dto.MonthlyRateDto(c.id,c.currencyCode,c.description,");
			sf.append("c." + BusinessUtil.getMonthlyRateFormula(1) + ",c." + BusinessUtil.getMonthlyRateFormula(2) + ",c." + BusinessUtil.getMonthlyRateFormula(3) + ",c.");
			sf.append(BusinessUtil.getMonthlyRateFormula(4) + ",c." + BusinessUtil.getMonthlyRateFormula(5) + ",c." + BusinessUtil.getMonthlyRateFormula(6) + ",c.");
			sf.append(BusinessUtil.getMonthlyRateFormula(7) + ",c." + BusinessUtil.getMonthlyRateFormula(8) + ",c." + BusinessUtil.getMonthlyRateFormula(9) + ",c.");
			sf.append(BusinessUtil.getMonthlyRateFormula(10) + ",c." + BusinessUtil.getMonthlyRateFormula(11) + ",c." + BusinessUtil.getMonthlyRateFormula(12) + ")");
			sf.append(" FROM Currency c WHERE c.isHomeCur=false ORDER BY c.currencyCode ASC");
			Query q = em.createQuery(sf.toString());
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Currency", pe);
		}
		return result;
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateMonthlyRate(MonthlyRateDto cur) throws DAOException {
		StringBuffer sf = new StringBuffer("UPDATE Currency c SET c.m1=:m1, c.m2=:m2, c.m3=:m3, c.m4=:m4, c.m5=:m5, c.m6=:m6, c.m7=:m7, c.m8=:m8, c.m9=:m9,"
				+ " c.m10=:m10, c.m11=:m11, c.m12=:m12 WHERE c.id=:id");
		try {
			Query q = em.createQuery(sf.toString());
			q.setParameter(BusinessUtil.getMonthlyRateFormula(1), cur.getM1());
			q.setParameter(BusinessUtil.getMonthlyRateFormula(2), cur.getM2());
			q.setParameter(BusinessUtil.getMonthlyRateFormula(3), cur.getM3());
			q.setParameter(BusinessUtil.getMonthlyRateFormula(4), cur.getM4());
			q.setParameter(BusinessUtil.getMonthlyRateFormula(5), cur.getM5());
			q.setParameter(BusinessUtil.getMonthlyRateFormula(6), cur.getM6());
			q.setParameter(BusinessUtil.getMonthlyRateFormula(7), cur.getM7());
			q.setParameter(BusinessUtil.getMonthlyRateFormula(8), cur.getM8());
			q.setParameter(BusinessUtil.getMonthlyRateFormula(9), cur.getM9());
			q.setParameter(BusinessUtil.getMonthlyRateFormula(10), cur.getM10());
			q.setParameter(BusinessUtil.getMonthlyRateFormula(11), cur.getM11());
			q.setParameter(BusinessUtil.getMonthlyRateFormula(12), cur.getM12());
			q.setParameter("id", cur.getId());
			q.executeUpdate();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Currency", pe);
		}
	}

	
}
