  package org.tech.hms.web.system.currencydailyrate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.tech.hms.common.validation.MessageId;
import org.tech.hms.currency.Currency;
import org.tech.hms.currency.service.interfaces.ICurrencyService;
import org.tech.hms.currencydailyrate.RateInfo;
import org.tech.hms.currencydailyrate.RateType;
import org.tech.hms.currencydailyrate.service.interfaces.IRateInfoService;
import org.tech.java.component.SystemException;
import org.tech.java.component.service.interfaces.IDataRepService;
import org.tech.java.web.common.BaseBean;

@Named(value = "RateDailyCurActionBean")
@Scope(value = "view")
public class RateDailyCurActionBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private ICurrencyService currencyService;

	
	@Autowired
	private IRateInfoService rateInfoService;

	
	//@Autowired
	//private IDataRepService<RateInfo> dataRepService;


	private RateInfo rateInfo;
	private List<RateInfo> rateInfoList;
	private List<Currency> currencyList;
	private boolean createNew;
	private List<RateInfo> filterList;

	@PostConstruct
	public void init() {
		createNewRateInfo();
		loadCurrency();
		rebindData();
	}

	public void createNewRateInfo() {
		createNew = true;
		rateInfo = new RateInfo();
	}
public String createNewCurrencyRate() {
	return "manageNewcurrencyrate.xhtml?faces-redirect=true";
}
	public void loadCurrency() {
		currencyList = currencyService.findForeignCurrency();
	}

	public void rebindData() {
		rateInfoList = rateInfoService.findAllRateInfo();
	}

	public RateType[] getRateType() {
		return RateType.values();
	}

	public String addNewRateInfo() {
		if (ValidateRateInfo()) {
			try {
				rateInfoService.addNewDailyRateInfo(rateInfo);
				addInfoMessage(null, MessageId.INSERT_SUCCESS, rateInfo.getCurrency().getCode());
				createNewRateInfo();
				rebindData();
			} catch (SystemException e) {
				handleSysException(e);
			}
		}
		return "managecurrencyrate.xhtml?faces-redirect=true";
	}

	public void deleteRateInfo() {
		try {
			rateInfo.setLastModify(false);
			//dataRepService.update(rateInfo);
			addInfoMessage(null, MessageId.DELETE_SUCCESS, rateInfo.getCurrency().getCode());
			createNewRateInfo();
			rebindData();
		} catch (SystemException e) {
			handleSysException(e);
		}
	}

	public boolean ValidateRateInfo() {
		Date maxDate = new Date();
		Date rDate = rateInfo.getrDate();
		boolean validate = true;
		if (rateInfo.getExchangeRate().equals(BigDecimal.ZERO)) {
			addErrorMessage("dailyCurrencyRateForm" + ":exchangeRate", MessageId.REQUIRED_EXCHANGERATE);
			validate = false;
		}
		if (rDate.after(maxDate)) {
			validate = false;
			addErrorMessage(null, MessageId.DATE_EXCEEDED);

		}
		return validate;
	}

	public RateInfo getRateInfo() {
		return rateInfo;
	}

	public void setRateInfo(RateInfo rateInfo) {
		this.rateInfo = rateInfo;
	}

	public List<RateInfo> getRateInfoList() {
		return rateInfoList;
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

	public boolean isCreateNew() {
		return createNew;
	}

	public List<RateInfo> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<RateInfo> filterList) {
		this.filterList = filterList;
	}

	public Date getMaxDate() {
		return new Date();
	}
}
