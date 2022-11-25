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

import lombok.Getter;
import lombok.Setter;

@Named(value = "ManageCurrencyActionBean")
@Scope(value = "view")
public class ManageCurrencyActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	protected ICurrencyService currencyService;

	@Getter
	@Setter
	private boolean createNew;
	
	@Getter
	@Setter
	private Currency currency;
	
	@Getter
	@Setter
	private boolean homeCurDisable;

	@Getter
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
public String createCurrency() {
	return "manageNewcurrency.xhtml?faces-redirect=true";
}
	public void loadCurrency() {
		currencyList = currencyService.findAllCurrency();
		Currency currency = currencyService.findHomeCurrency();
		homeCurDisable = currency == null ? false : true;
	}


	public String addNewCurrency() {
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
		return "managecurrency.xhtml?faces-redirect=true";
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

}
