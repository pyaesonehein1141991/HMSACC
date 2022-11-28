package org.tech.hms.coasetup;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.tech.hms.branch.Branch;
import org.tech.hms.common.TableName;
import org.tech.hms.common.UserRecorder;
import org.tech.hms.currency.Currency;
import org.tech.hms.currencyChartOfAccount.CurrencyChartOfAccount;
import org.tech.hms.department.Department;
import org.tech.java.component.idgen.service.IDInterceptor;

import lombok.Data;

@Entity
@Table(name = TableName.COASETUP)
@TableGenerator(name = "COASETUP_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "COASETUP_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "COASetup.findAcnameByAcNameBranchCode", query = "SELECT c FROM COASetup c") })
@NamedQuery(name = "COASetup.findAllCashAccount", query = "SELECT c from COASetup c  WHERE c.acName=org.tech.hms.common.VoucherType.CASH")
@EntityListeners(IDInterceptor.class)
@Data
public class COASetup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -157733738940063961L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COASETUP_GEN")
	private String id;

	private String acName;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CCOAID", referencedColumnName = "ID")
	private CurrencyChartOfAccount ccoa;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCHID", referencedColumnName = "ID")
	private Branch branch;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENTID", referencedColumnName = "ID")
	private Department department;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCYID", referencedColumnName = "ID")
	private Currency currency;

	@Version
	private int version;

	@Embedded
	private UserRecorder userRecorder;
	

}
