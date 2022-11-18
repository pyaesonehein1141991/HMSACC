package org.tech.hms.setup;

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
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableName.SETUP)
@TableGenerator(name = "SETUP_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "SETUP_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "Setup.findAll", query = "SELECT s FROM Setup s ORDER BY s.variable"),
		@NamedQuery(name = "Setup.findSetupValueByVariable", query = "SELECT s.value FROM Setup s WHERE s.variable=:variable"),
		@NamedQuery(name = "Setup.findSetupBudgetByVariable", query = "SELECT s.budget FROM Setup s WHERE s.variable=:variable"),
		@NamedQuery(name = "Setup.updateSetupBudget", query = "Update Setup s Set s.budget=:budget"),
		@NamedQuery(name = "Setup.updateSetupValueByVariable", query = "Update Setup s Set s.value=:value WHERE s.variable=:variable") })
@EntityListeners(IDInterceptor.class)
@Data
@NoArgsConstructor
public class Setup implements Serializable {
	private static final long serialVersionUID = 3050597136794243842L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SETUP_GEN")
	private String id;

	private String variable;

	private String value;

	private String budget;

	@Version
	private int version;

	@Embedded
	private UserRecorder userRecorder;

}