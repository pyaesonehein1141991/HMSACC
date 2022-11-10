package org.tech.hms.branch;

import java.io.Serializable;

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
@EntityListeners(IDInterceptor.class)
@Table(name = TableName.BRANCH)
@TableGenerator(name = "BRANCH_GEN", table = "ID_GEN",valueColumnName = "GEN_VAL", pkColumnName = "GEN_NAME", pkColumnValue = "BRANCH_GEN",allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "Branch.findAll",query = "SELECT b FROM Branch b  ORDER BY b.name ASC"),
		@NamedQuery(name = "Branch.findById",query = "SELECT b FROM Branch b WHERE b.id = :id")
})
@Data
public class Branch implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator = "BRANCH_GEN")
	private String id;
	private String name;
	private String code;
	private String description;

	@Embedded
	private UserRecorder recorder;
	
	@Version
	private int version;

	
}
