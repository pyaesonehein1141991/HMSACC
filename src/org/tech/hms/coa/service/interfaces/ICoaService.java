package org.tech.hms.coa.service.interfaces;

import java.util.List;

import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.common.dto.coaDto.CoaDTO;

public interface ICoaService {

	void createCoa(ChartOfAccount coa);

	ChartOfAccount updateCoa(ChartOfAccount coa);

	void deleteChartOfAccount(ChartOfAccount chartOfAccount);

	List<ChartOfAccount> findAllCoa();

	List<CoaDTO> findAllDTO();
	
	public ChartOfAccount findCoaByibsbAcCode(String ibsbACode);
	
	public ChartOfAccount findCoaByAcCode(String acCode);
	

}
