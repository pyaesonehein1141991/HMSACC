package org.tech.hms.currency;



import java.io.Serializable;
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


@Entity
@Table(name = TableName.CUR)
@TableGenerator(name = "CURRENCY_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "CURRENCY_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "Currency.findAll", query = "SELECT c FROM Currency c  ORDER BY c.code ASC"),
		@NamedQuery(name = "Currency.findById", query = "SELECT c FROM Currency c WHERE c.id = :id"),
		@NamedQuery(name = "Currency.findHomeCurrency", query = "SELECT c FROM Currency c WHERE c.isHomeCur = true") })
@EntityListeners(IDInterceptor.class)
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

	@Version
	private int version;

	@Embedded
	private UserRecorder userRecorder;

	public Currency() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Boolean getIsHomeCur() {
		return isHomeCur;
	}

	public void setIsHomeCur(Boolean isHomeCur) {
		this.isHomeCur = isHomeCur;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public UserRecorder getUserRecorder() {
		return userRecorder;
	}

	public void setUserRecorder(UserRecorder userRecorder) {
		this.userRecorder = userRecorder;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, description, id, isHomeCur, symbol, userRecorder, version);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Currency other = (Currency) obj;
		return Objects.equals(code, other.code) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(isHomeCur, other.isHomeCur)
				&& Objects.equals(symbol, other.symbol) && Objects.equals(userRecorder, other.userRecorder)
				&& version == other.version;
	}

	

}
