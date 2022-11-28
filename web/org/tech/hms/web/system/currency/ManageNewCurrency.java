package org.tech.hms.web.system.currency;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

@Named(value = "ManageNewCurrency")
@Scope(value = "view")
public class ManageNewCurrency extends BaseBean implements Serializable {

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

	@PostConstruct
	public void init() {
		if (isExistParam("currency")) {
			this.currency = (Currency) getParam("currency");
		} else {
			createNewCurrency();

		}
		loadCurrency();
	}

	@PreDestroy
	public void destroy() {
		removeParam("currency");
	}

	public void createNewCurrency() {
		createNew = true;
		currency = new Currency();
		currency.setIsHomeCur(false);
	}

	public void loadCurrency() {
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

	public String updateCurrency() {
		try {
			currencyService.updateCurrency(currency);
			addInfoMessage(null, MessageId.UPDATE_SUCCESS, currency.getCode());
			return "managecurrency.xhtml?faces-redirect=true";
		} catch (SystemException ex) {
			handleSysException(ex);
		}
		return null;
	}

}
