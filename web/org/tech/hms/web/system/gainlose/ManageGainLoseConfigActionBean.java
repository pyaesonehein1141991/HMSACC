package org.tech.hms.web.system.gainlose;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.common.dto.coaDto.CoaDialogCriteriaDto;
import org.tech.hms.common.validation.MessageId;
import org.tech.hms.exchangeconfig.ExchangeConfig;
import org.tech.hms.exchangeconfig.service.interfaces.IExchangeConfigService;
import org.tech.java.component.SystemException;
import org.tech.java.web.common.BaseBean;
import org.tech.java.web.common.ParamId;

@Named(value = "ManageGainLoseConfigActionBean")
@Scope(value = "view")
public class ManageGainLoseConfigActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IExchangeConfigService exchangeConfigService;

	private ExchangeConfig exchangeConfig;
	private List<ExchangeConfig> exchangeList;
	private boolean isEdit;

	@PostConstruct
	public void init() {
		exchangeConfig = new ExchangeConfig();
		exchangeList = exchangeConfigService.findAllExchangeConfig();
	}

	public void createNewExchange() {
		exchangeConfig = new ExchangeConfig();
	}

	public void createExchange() {
		try {
			if (isEdit) {
				exchangeConfigService.updateBranch(exchangeConfig);
				exchangeList.add(exchangeConfig);
				addInfoMessage(null, MessageId.UPDATE_SUCCESS, exchangeConfig.getAcName());
				exchangeConfig = new ExchangeConfig();
			} else {
				exchangeConfigService.addNewExchangeConfig(exchangeConfig);
				exchangeList.add(exchangeConfig);
				addInfoMessage(null, MessageId.INSERT_SUCCESS, exchangeConfig.getAcName());
				exchangeConfig = new ExchangeConfig();
			}
		} catch (SystemException e) {
			// TODO: handle exception
		}
	}

	public void prepareEdit(ExchangeConfig exchangeConfig) {
		this.isEdit = true;
		this.exchangeConfig = exchangeConfig;
		exchangeList.remove(exchangeConfig);
	}

	public void deleteExchangeConfig(ExchangeConfig exchangeConfig) {
		try {
			exchangeConfigService.deleteBranch(exchangeConfig);
			exchangeList.remove(exchangeConfig);
			addInfoMessage(null, MessageId.DELETE_SUCCESS, exchangeConfig.getAcName());
		} catch (SystemException e) {
			// TODO: handle exception
		}
	}

	public void selectCOACode() {
		CoaDialogCriteriaDto dto = new CoaDialogCriteriaDto();
		putParam(ParamId.COA_DIALOG_CRITERIA_DATA, dto);
		selectCoa();
	}

	public void returnCoaCode(SelectEvent event) {
		ChartOfAccount coa = (ChartOfAccount) event.getObject();
		exchangeConfig.setCoaCode(coa.getAcCode());
		exchangeConfig.setAcName(coa.getAcName());
	}

	public void returnExchangeCode(SelectEvent event) {
		ChartOfAccount coa = (ChartOfAccount) event.getObject();
		exchangeConfig.setExchangeCode(coa.getAcCode());
	}

	public ExchangeConfig getExchangeConfig() {
		return exchangeConfig;
	}

	public void setExchangeConfig(ExchangeConfig exchangeConfig) {
		this.exchangeConfig = exchangeConfig;
	}

	public List<ExchangeConfig> getExchangeList() {
		return exchangeList;
	}

	public boolean isEdit() {
		return isEdit;
	}

}
