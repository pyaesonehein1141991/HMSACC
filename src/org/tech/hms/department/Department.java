package org.tech.hms.department;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.tech.hms.common.TableName;
import org.tech.hms.common.UserRecorder;
import org.tech.java.component.idgen.service.IDInterceptor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableName.DEPARTMENT)
@TableGenerator(name = "DEPARTMENT_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "DEPARTMENT_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d ORDER BY d.dCode ASC"), 
						@NamedQuery(name = "Department.findCCOAUsedDept", query = "SELECT ccoa FROM CurrencyChartOfAccount ccoa Where ccoa.department=:department ")})
@EntityListeners(IDInterceptor.class)
@Data
@NoArgsConstructor
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "DEPARTMENT_GEN")
	private String id;

	@Column(unique=true)
	private String dCode;

	private String description;

	@Version
	private int version;

	@Embedded
	private UserRecorder userRecorder;
	
}
