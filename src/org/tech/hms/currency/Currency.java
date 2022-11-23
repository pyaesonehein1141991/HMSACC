package org.tech.hms.currency;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

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
@Table(name = TableName.CUR)
@TableGenerator(name = "CURRENCY_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "CURRENCY_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "Currency.findAll", query = "SELECT c FROM Currency c  ORDER BY c.code ASC"),
		@NamedQuery(name = "Currency.findById", query = "SELECT c FROM Currency c WHERE c.id = :id"),
		@NamedQuery(name = "Currency.findForeignCurrency", query = "SELECT c FROM Currency c WHERE c.isHomeCur=false ORDER BY c.code ASC"),
		@NamedQuery(name = "Currency.findHomeCurrency", query = "SELECT c FROM Currency c WHERE c.isHomeCur = true") })
@EntityListeners(IDInterceptor.class)
@Data
public class Currency implements Serializable {

	private static final long serialVersionUID = -5785537862626257490L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CURRENCY_GEN")
	private String id;

	@Column(name = "CUR", unique = true)
	private String code;
	private String description;
	private String symbol;
	private Boolean isHomeCur;
	private BigDecimal m1;
	private BigDecimal m2;
	private BigDecimal m3;
	private BigDecimal m4;
	private BigDecimal m5;
	private BigDecimal m6;
	private BigDecimal m7;
	private BigDecimal m8;
	private BigDecimal m9;
	private BigDecimal m10;
	private BigDecimal m11;
	private BigDecimal m12;
	private BigDecimal m13;

	@Version
	private int version;

	@Embedded
	private UserRecorder userRecorder;


}
