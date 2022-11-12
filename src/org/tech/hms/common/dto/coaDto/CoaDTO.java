package org.tech.hms.common.dto.coaDto;

import java.util.Date;

import org.tech.hms.coa.ChartOfAccount;
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

	/*
	 * @Enumerated(value = EnumType.STRING) private AccountCodeType acCodeType;
	 */

	private Date pDate;

	private String ibsbACode;
	
	
	private ChartOfAccount parent;
	
	
	

}
