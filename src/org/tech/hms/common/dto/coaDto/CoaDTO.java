package org.tech.hms.common.dto.coaDto;

import java.util.Date;

import org.tech.hms.codesetup.AccountCodeType;
import org.tech.hms.common.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoaDTO {

	private String id;
	private String acName;

	private String acCode;

	private AccountType acType;

	private AccountCodeType acCodeType;

	private Date pDate;

	private String ibsbACode;

	public CoaDTO(String id, String acName, String acCode, AccountType acType, AccountCodeType acCodeType,
			String ibsbACode) {
		this.id = id;
		this.acName = acName;
		this.acCode = acCode;
		this.acType = acType;
		this.acCodeType = acCodeType;
		this.ibsbACode = ibsbACode;
	}

}
