package org.tech.hms.common;

import java.io.Serializable;

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
import javax.persistence.Version;

import org.tech.java.component.idgen.service.IDInterceptor;

import lombok.Data;

@Entity
@Table(name = TableName.TRANTYPE)
@TableGenerator(name = "TRANTYPE_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "TRANTYPE_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "TranType.findAll", query = "SELECT t FROM TranType t"),
		@NamedQuery(name = "TranType.findByTransCode", query = "SELECT t FROM TranType t WHERE t.tranCode=:tranCode")	})
@EntityListeners(IDInterceptor.class)
@Data
public class TranType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5733231881764097574L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TRANTYPE_GEN")
	private String id;

	@Enumerated(EnumType.STRING)
	private TranCode tranCode;

	private String desp;

	private String narration;

	private String status;

	private String pbReference;

	private String rvReference;

	@Version
	private int version;

	@Embedded
	private UserRecorder userRecorder;

	public TranType() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TranCode getTranCode() {
		return tranCode;
	}

	public void setTranCode(TranCode tranCode) {
		this.tranCode = tranCode;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPbReference() {
		return pbReference;
	}

	public void setPbReference(String pbReference) {
		this.pbReference = pbReference;
	}

	public String getRvReference() {
		return rvReference;
	}

	public void setRvReference(String rvReference) {
		this.rvReference = rvReference;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	

}