package org.tech.hms.web.dialog;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.tech.hms.common.CCOADialogDTO;
import org.tech.hms.currency.Currency;
import org.tech.hms.currencyChartOfAccount.CurrencyChartOfAccount;
import org.tech.hms.currencyChartOfAccount.service.interfaces.ICcoaService;
import org.tech.hms.process.interfaces.IUserProcessService;
import org.tech.hms.user.User;
import org.tech.java.component.service.interfaces.IDataRepService;
import org.tech.java.web.common.BaseBean;
import org.tech.java.web.common.ParamId;

@Named(value = "CCOAAccountDialogActionBean")
@Scope(value = "view")
public class CCOAAccountDialogActionBean extends BaseBean {
	
	@Autowired
	private IUserProcessService userProcessService;


	@Autowired
	private ICcoaService ccoaService;

	

	@Autowired
	@Qualifier("CcoaService")
	private IDataRepService<CurrencyChartOfAccount> dataRepService;


	private List<CCOADialogDTO> ccoaList;

	@PostConstruct
	public void init() {
		User user = userProcessService.getLoginUser();
		Currency currency = (Currency) getParam(ParamId.CURRENCY_DATA);
		ccoaList = ccoaService.findAllCCOADialogDTO(currency, user.getBranch());
	}

	public List<CCOADialogDTO> getCcoaList() {
		return ccoaList;
	}

	public void selectCCOAAccount(CCOADialogDTO ccoa) {
		CurrencyChartOfAccount account = dataRepService.findById(CurrencyChartOfAccount.class, ccoa.getId());
		PrimeFaces.current().dialog().closeDynamic(account);
	}

	@PreDestroy
	public void destory() {
		removeParam(ParamId.CURRENCY_DATA);
	}
}
