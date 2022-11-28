package org.tech.hms.common.dto.coaDto;

import java.util.ArrayList;
import java.util.List;

import org.tech.hms.coa.ChartOfAccount;

public class CoaDialogDto {
	private CoaDialogCriteriaDto criteriaDto;
	private List<ChartOfAccount> coaList;

	public CoaDialogDto() {
		coaList = new ArrayList<>();
	}

	public List<ChartOfAccount> getCoaList() {
		return coaList;
	}

	public void setCoaList(List<ChartOfAccount> coaList) {
		this.coaList = coaList;
	}

	public CoaDialogCriteriaDto getCriteriaDto() {
		return criteriaDto;
	}

	public void setCriteriaDto(CoaDialogCriteriaDto criteriaDto) {
		this.criteriaDto = criteriaDto;
	}

}
