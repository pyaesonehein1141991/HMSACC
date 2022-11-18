package org.tech.hms.exchangeconfig;

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
@Table(name = TableName.EXCHANGECONFIG)
@TableGenerator(name = "EXCHANGE_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", pkColumnValue = "EXCHANGE_GEN", valueColumnName = "GEN_VAL", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "ExchangeConfig.findAll", query = "SELECT e FROM ExchangeConfig e ") })
@EntityListeners(IDInterceptor.class)
@Data
@NoArgsConstructor
public class ExchangeConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "EXCHANGE_GEN")
	private String id;
	@Column(name = "ACCODE")
	private String coaCode;
	private String exchangeCode;
	private String acName;

	@Version
	private int version;

	@Embedded
	private UserRecorder userRecorder;


}
