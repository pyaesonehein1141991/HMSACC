package org.tech.hms.currencydailyrate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.tech.hms.common.TableName;
import org.tech.hms.common.UserRecorder;
import org.tech.hms.currency.Currency;
import org.tech.java.component.idgen.service.IDInterceptor;


@Entity
@Table(name = TableName.RATEINFO)
@TableGenerator(name = "RATEINFO_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "RATEINFO_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "RateInfo.findAll", query = "SELECT r FROM RateInfo r WHERE r.lastModify = 1 ORDER BY r.rDate, r.currency DESC"),
		@NamedQuery(name = "RateInfo.findRateInfoByCurRTypeRDate", query = "SELECT r FROM RateInfo r WHERE r.currency = :currency AND r.rateType= :rateType AND r.rDate= :rDate AND r.lastModify = 1"),
		@NamedQuery(name = "RateInfo.updateLastModifyBy", query = "UPDATE RateInfo r set r.lastModify=0  WHERE r.currency = :currency AND r.rateType= :rateType"),
		@NamedQuery(name = "RateInfo.findRateInfoByCurrencyID", query = "SELECT r FROM RateInfo r WHERE r.currency.id=:currency") })
@EntityListeners(IDInterceptor.class)
public class RateInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6273089696398684674L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RATEINFO_GEN")
	private String id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUR", referencedColumnName = "ID")
	private Currency currency;

	@Enumerated(value = EnumType.STRING)
	private RateType rateType;

	private BigDecimal exchangeRate;

	private String denoRate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date rDate;

	private boolean lastModify = true;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TOCUR", referencedColumnName = "ID")
	private Currency toCur;

	@Version
	private int version;


	@Embedded
	private UserRecorder userRecorder;

	public RateInfo() {
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public RateType getRateType() {
		return rateType;
	}

	public void setRateType(RateType rateType) {
		this.rateType = rateType;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getDenoRate() {
		return denoRate;
	}

	public void setDenoRate(String denoRate) {
		this.denoRate = denoRate;
	}

	public Date getrDate() {
		return rDate;
	}

	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}

	public boolean isLastModify() {
		return lastModify;
	}

	public void setLastModify(boolean lastModify) {
		this.lastModify = lastModify;
	}

	public Currency getToCur() {
		return toCur;
	}

	public void setToCur(Currency toCur) {
		this.toCur = toCur;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
	@Override
	public int hashCode() {
		return Objects.hash(currency, denoRate, exchangeRate, id, lastModify, rDate, rateType, toCur, userRecorder,
				version);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	
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
		RateInfo other = (RateInfo) obj;
		return Objects.equals(currency, other.currency) && Objects.equals(denoRate, other.denoRate)
				&& Objects.equals(exchangeRate, other.exchangeRate) && Objects.equals(id, other.id)
				&& lastModify == other.lastModify && Objects.equals(rDate, other.rDate) && rateType == other.rateType
				&& Objects.equals(toCur, other.toCur) && Objects.equals(userRecorder, other.userRecorder)
				&& version == other.version;
	}


}
