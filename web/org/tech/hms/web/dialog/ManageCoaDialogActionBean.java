package org.tech.hms.web.dialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.coa.service.interfaces.ICoaService;
import org.tech.hms.common.dto.coaDto.CoaDTO;
import org.tech.hms.common.dto.coaDto.CoaDialogCriteriaDto;
import org.tech.hms.common.dto.coaDto.CoaDialogDto;
import org.tech.java.web.common.BaseBean;
import org.tech.java.web.common.ParamId;

import lombok.Getter;

@Named(value = "ManageCoaDialogActionBean")
@Scope(value = "view")
public class ManageCoaDialogActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String BASEROOTNAME = "Chart Of Account";

	@Autowired
	private ICoaService coaService;

	@Getter
	private List<CoaDTO> dtoList;

	private CoaDialogDto dto = new CoaDialogDto();

	@PostConstruct
	public void init() {
		Comparator<ChartOfAccount> c = Comparator.comparing(ChartOfAccount::getAcType)
				.thenComparing(ChartOfAccount::getAcCodeType).thenComparing(ChartOfAccount::getAcCode);
		// There must be criteria to get coaList
		CoaDialogCriteriaDto criteria = (CoaDialogCriteriaDto) getParam(ParamId.COA_DIALOG_CRITERIA_DATA);
		List<ChartOfAccount> list = new ArrayList<ChartOfAccount>();
		if (getParam(ParamId.COA_DATA) == null) {
			list = coaService.findAllCoaByCriteria(criteria);
			list.sort(c);
			dto.setCoaList(list);
			dto.setCriteriaDto(criteria);
			putParam(ParamId.COA_DATA, dto);
		} else {
			dto = (CoaDialogDto) getParam(ParamId.COA_DATA);
			if (!dto.getCriteriaDto().equals(criteria)) {
				list = coaService.findAllCoaByCriteria(criteria);
				list.sort(c);
				dto.setCoaList(list);
				dto.setCriteriaDto(criteria);
				putParam(ParamId.COA_DATA, dto);
			}
		}
		removeParam(ParamId.COA_DIALOG_CRITERIA_DATA);
	}

	public List<ChartOfAccount> getCoaList() {
		return dto.getCoaList();
	}

	public void selectCoa(ChartOfAccount coa) {
		PrimeFaces.current().dialog().closeDynamic(coa);
		/* RequestContext.getCurrentInstance().closeDialog(coa); */
	}

}
