package org.tech.hms.currencyChartOfAccount;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.tech.hms.branch.Branch;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.common.TableName;
import org.tech.hms.common.UserRecorder;
import org.tech.hms.currency.Currency;
import org.tech.java.component.idgen.service.IDInterceptor;

import lombok.Data;


@Entity
@Table(name = TableName.CCOA)
@TableGenerator(name = "CCOA_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "CCOA_GEN", allocationSize = 10)
/*
 * @NamedQueries(value = { @NamedQuery(name = "CurrencyChartOfAccount.findAll",
 * query =
 * "SELECT c FROM CurrencyChartOfAccount c ORDER BY c.coa.acCode,c.currency.currencyCode ASC"
 * ),
 * 
 * @NamedQuery(name = "CurrencyChartOfAccount.findCCOAByCOAid", query =
 * "SELECT c FROM CurrencyChartOfAccount c where c.coa.id=:id"),
 * 
 * @NamedQuery(name = "CurrencyChartOfAccount.findCOAByBranchID", query =
 * "SELECT DISTINCT NEW org.tech.hms.currencyChartOfAccount.CurrencyChartOfAccount(c.coa) FROM CurrencyChartOfAccount c WHERE c.branch.id=:branchId and c.coa.acCodeType != org.tech.hms.codesetup.AccountCodeType.HEAD AND c.coa.acCodeType != org.tech.hms.codesetup.AccountCodeType.GROUP "
 * ),
 * 
 * @NamedQuery(name = "CurrencyChartOfAccount.findByAcCodeAndBranchId", query =
 * "SELECT c FROM CurrencyChartOfAccount c WHERE c.coa.acCode=:acCode AND c.branch.id=:branchId ORDER BY c.currency.currencyCode ASC"
 * ),
 * 
 * @NamedQuery(name = "CurrencyChartOfAccount.updateBudgetYearByID", query =
 * "UPDATE CurrencyChartOfAccount c SET c.bF=:bF WHERE c.id=:id"),
 * 
 * @NamedQuery(name = "CurrencyChartOfAccount.findCCOAByIdAndBranch", query =
 * "Select ccoa from CurrencyChartOfAccount ccoa WHERE ccoa.coa.id=:coaId AND ccoa.branch=:branch AND ccoa.currency=:currency "
 * ),
 * 
 * @NamedQuery(name = "CurrencyChartOfAccount.findAllBudget", query =
 * "Select distinct ccoa.budget from CurrencyChartOfAccount ccoa WHERE ccoa.budget IS NOT NULL UNION ALL Select distinct ccoahist.budget from CcoaHistory ccoahist WHERE ccoahist.budget IS NOT NULL"
 * ),
 * 
 * @NamedQuery(name = "CurrencyChartOfAccount.findCCOAByCurrencyID", query =
 * "SELECT c FROM CurrencyChartOfAccount c where c.currency.id=:curid"),
 * 
 * @NamedQuery(name = "CurrencyChartOfAccount.findCCOAByBranchID", query =
 * "SELECT c FROM CurrencyChartOfAccount c where c.branch.id=:branchid") ,
 * 
 * @NamedQuery(name = "CurrencyChartOfAccount.updateByBranchId", query =
 * "UPDATE CurrencyChartOfAccount c SET c.acName=:acName, c.department=:departmentId WHERE c.coa.acCode = :acCode AND c.branch.branchCode = :branchCode"
 * ) })
 */
@EntityListeners(IDInterceptor.class)
@Data
public class CurrencyChartOfAccount implements Serializable {
	private static final long serialVersionUID = -5230388877783166738L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CCOA_GEN")
	private String id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COAID", referencedColumnName = "ID")
	private ChartOfAccount coa;

	private String acName;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCHID", referencedColumnName = "ID")
	private Branch branch;

	/*
	 * @OneToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "DEPARTMENTID", referencedColumnName = "ID") private
	 * Department department;
	 */

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCYID", referencedColumnName = "ID")
	private Currency currency;

	/*
	 * Budget Figure
	 */
	private BigDecimal bF;

	/*
	 * Opening Balance
	 */
	private BigDecimal oBal;

	/*
	 * Home Opening Balance
	 */
	private BigDecimal hOBal;

	/*
	 * Closing Balance
	 */
	private BigDecimal cBal;

	@Embedded
	private MonthlyRate monthlyRate;

	@Embedded
	private MSrcRate msrcRate;

	@Embedded
	private BfRate bfRate;

	@Embedded
	private BfSrcRate bfsrcRate;

	@Embedded
	private MrevRate mrevRate;

	@Embedded
	private LymSrcRate lymsrcRate;

	private BigDecimal sccrBal;

	private BigDecimal accrued;

	private String budget;

	@Version
	private int version;

	@Embedded
	private UserRecorder userRecorder;
	
	public CurrencyChartOfAccount() {
		bF = BigDecimal.ZERO;
		oBal = BigDecimal.ZERO;
		hOBal = BigDecimal.ZERO;
		cBal = BigDecimal.ZERO;
		sccrBal = BigDecimal.ZERO;
		accrued = BigDecimal.ZERO;
		monthlyRate = new MonthlyRate();
		msrcRate = new MSrcRate();
		bfRate = new BfRate();
		bfsrcRate = new BfSrcRate();
		mrevRate = new MrevRate();
		lymsrcRate = new LymSrcRate();
	}
	
	

	

}