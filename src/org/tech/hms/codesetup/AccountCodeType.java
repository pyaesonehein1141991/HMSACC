package org.tech.hms.codesetup;



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


@Entity
@Table(name = TableName.ACCODETYPE)
@TableGenerator(name = "ACCODETYPE_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "ACCODETYPE_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "AccountCodeType.findAll", query = "SELECT c FROM AccountCodeType c  ORDER BY c.name ASC"),
		@NamedQuery(name = "AccountCodeType.findById", query = "SELECT c FROM AccountCodeType c WHERE c.id = :id") })
@EntityListeners(IDInterceptor.class)
@Data
public class AccountCodeType implements Serializable {

	private static final long serialVersionUID = -5785537862626257490L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CODESETUP_GEN")
	private String id;

	@Column(name = "name", unique = true)
	private String name;
	private String description;


	@Version
	private int version;

	@Embedded
	private UserRecorder userRecorder;

	public AccountCodeType() {
		
	}

	
	

}
