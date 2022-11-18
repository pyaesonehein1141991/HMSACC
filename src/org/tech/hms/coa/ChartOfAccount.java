package org.tech.hms.coa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.tech.hms.codesetup.AccountCodeType;
import org.tech.hms.common.AccountType;
import org.tech.hms.common.TableName;
import org.tech.hms.common.UserRecorder;
import org.tech.java.component.idgen.service.IDInterceptor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableName.COA)
@TableGenerator(name = "COA_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "COA_GEN", allocationSize = 10)
@NamedQueries(value = {
		@NamedQuery(name = "ChartOfAccount.findAll", query = "SELECT c FROM ChartOfAccount c  ORDER BY c.acName ASC"),
		@NamedQuery(name = "ChartOfAccount.findById", query = "SELECT c FROM ChartOfAccount c WHERE c.id = :id"),
		@NamedQuery(name = "ChartOfAccount.findByAcCode", query = "SELECT c FROM ChartOfAccount c WHERE c.acCode = :acCode"),
		@NamedQuery(name = "ChartOfAccount.findByIbsbACode", query = "SELECT c FROM ChartOfAccount c WHERE c.ibsbACode = :ibsbACode ")})
@EntityListeners(IDInterceptor.class)
@Data
@NoArgsConstructor
public class ChartOfAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COA_GEN")
	private String id;

	private String acName;

	private String acCode;

	@Enumerated(value = EnumType.STRING)
	private AccountType acType;

	@Enumerated(value = EnumType.STRING)
	private AccountCodeType acCodeType;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date pDate;

	private String ibsbACode;
	
	private String headId;

	private String groupId;
	
	
	@Version
	private int version;

	@Embedded
	private UserRecorder userRecorder;
	
	
	@Override
	public String toString() {
		return acCode + "-" + acName;
	}
	


}
