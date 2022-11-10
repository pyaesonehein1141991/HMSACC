package org.tech.hms.web.system.currency;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.tech.hms.common.validation.MessageId;
import org.tech.hms.currency.Currency;
import org.tech.hms.currency.service.interfaces.ICurrencyService;
import org.tech.java.component.SystemException;
import org.tech.java.web.common.BaseBean;

@Named(value = "ManageCurrencyActionBean")
@Scope(value = "view")
public class ManageCurrencyActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	protected ICurrencyService currencyService;

	

	private boolean createNew;
	private Currency currency;
	private boolean homeCurDisable;

	private List<Currency> currencyList;

	@PostConstruct
	public void init() {
		createNewCurrency();
		loadCurrency();
	}

	public void createNewCurrency() {
		createNew = true;
		currency = new Currency();
		currency.setIsHomeCur(false);
	}

	public void loadCurrency() {
		currencyList = currencyService.findAllCurrency();
		Currency currency = currencyService.findHomeCurrency();
		homeCurDisable = currency == null ? false : true;
	}


	public void addNewCurrency() {
		try {
			if (createNew) {
				currencyService.addNewCurrency(currency);
				loadCurrency();
			} else {
				currencyService.updateCurrency(currency);
				loadCurrency();
			}
			addInfoMessage(null, MessageId.SAVE_SUCCESS, currency.getCode());
			createNewCurrency();
		} catch (SystemException ex) {
			handleSysException(ex);
		}
	}

	public String deleteCurrency(Currency currency) {
		try {
			currencyService.deleteCurrency(currency);
			addInfoMessage(null, MessageId.DELETE_SUCCESS, currency.getCode());
			currencyList.remove(currency);
		} catch (SystemException ex) {
			handleSysException(ex);
		}
		return null;
	}
	
	public void prepareUpdateCurrency(Currency currency) {
		createNew = false;
		this.currency = currency;
	}
	public void updateCurrency() {
			try {
				currencyService.updateCurrency(currency);
				addInfoMessage(null, MessageId.UPDATE_SUCCESS, currency.getCode());
				createNewCurrency();
				loadCurrency();
			} catch (SystemException ex) {
				handleSysException(ex);
			}
			loadCurrency();
		}

			
	

	public ICurrencyService getCurrencyService() {
		return currencyService;
	}

	public void setCurrencyService(ICurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	public boolean isCreateNew() {
		return createNew;
	}

	public void setCreateNew(boolean createNew) {
		this.createNew = createNew;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public boolean isHomeCurDisable() {
		return homeCurDisable;
	}

	public void setHomeCurDisable(boolean homeCurDisable) {
		this.homeCurDisable = homeCurDisable;
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<Currency> currencyList) {
		this.currencyList = currencyList;
	}


}
