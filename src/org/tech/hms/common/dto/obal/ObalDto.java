package org.tech.hms.common.dto.obal;

import java.math.BigDecimal;

import org.tech.hms.branch.Branch;
import org.tech.hms.common.AccountType;
import org.tech.hms.currency.Currency;
import org.tech.hms.department.Department;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ObalDto {
	private String acCode;
	private String acName;
	private BigDecimal oBal;
	private BigDecimal hoBal;
	private BigDecimal oldOBal;
	private BigDecimal oldHoBal;
	private AccountType acType;
	private Branch branch;
	private Department department;
	private Currency currency;
	private String ccoaId;
	private String headId;
	private String groupId;

	public ObalDto(String acCode, String acName, BigDecimal oBal, BigDecimal hoBal, AccountType acType, Branch branch,
			Department department, Currency currency, String ccoaId, String headId, String groupId) {
		this.acCode = acCode;
		this.acName = acName;
		this.oBal = oBal;
		this.oldOBal = new BigDecimal(getoBal().doubleValue());
		this.hoBal = hoBal;
		this.oldHoBal = new BigDecimal(getHoBal().doubleValue());
		this.acType = acType;
		this.branch = branch;
		this.department = department;
		this.currency = currency;
		this.ccoaId = ccoaId;
		this.headId = headId;
		this.groupId = groupId;
	}

	public BigDecimal getoBal() {
		return oBal == null ? BigDecimal.ZERO : oBal;
	}

	public BigDecimal getHoBal() {
		return hoBal == null ? BigDecimal.ZERO : hoBal;
	}

	public boolean isError() {
		double oBal = getoBalDoubleValue();
		double hoBal = getHoBalDoubleValue();
		if ((oBal != 0 && hoBal == 0)) {
			return true;
		} else if (oBal == 0 && hoBal != 0) {
			return true;
		} else {
			return false;
		}
	}

	public double getoBalDoubleValue() {
		return getoBal().doubleValue();
	}

	public double getHoBalDoubleValue() {
		return getHoBal().doubleValue();
	}

	public boolean isUpdated() {
		boolean result = false;
		if (getoBal().doubleValue() != oldOBal.doubleValue()) {
			result = true;
		} else if (getHoBal().doubleValue() != oldHoBal.doubleValue()) {
			result = true;
		}
		return result;

	}

}
