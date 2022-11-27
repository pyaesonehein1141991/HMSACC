package org.tech.hms.web.system.transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.tech.hms.common.BusinessUtil;
import org.tech.hms.common.DateUtils;
import org.tech.hms.common.TranCode;
import org.tech.hms.common.dto.coaDto.VoucherDTO;
import org.tech.hms.common.validation.MessageId;
import org.tech.hms.currency.Currency;
import org.tech.hms.currency.service.interfaces.ICurrencyService;
import org.tech.hms.currencyChartOfAccount.CurrencyChartOfAccount;
import org.tech.hms.currencydailyrate.RateType;
import org.tech.hms.process.interfaces.IUserProcessService;
import org.tech.hms.role.Role;
import org.tech.hms.tlf.interfaces.service.ITLFService;
import org.tech.hms.user.User;
import org.tech.java.component.SystemException;
import org.tech.java.component.service.PasswordCodecHandler;
import org.tech.java.web.common.BaseBean;


@Named(value = "DebitVoucherActionBean")
@Scope(value = "view")
public class DebitVoucherActionBean extends BaseBean {

	@Autowired
	private ICurrencyService currencyService;


	@Autowired
	private ITLFService tlfService;


	@Autowired
	private IUserProcessService userProcessService;


	@Autowired
	private PasswordCodecHandler codecHandler;


	public final String formID = "voucherForm";
	private List<Currency> currencyList;
	private VoucherDTO voucherDto;
	private boolean admin;
	private Date todayDate;
	private Date minDate;
	private Date beforeBudgetSDate;
	private String editPassword;
	private boolean isEdit;

	@PostConstruct
	public void init() {
		createNewDebitVoucher();
		rebindData();
		//minDate = BusinessUtil.getBackDate();
		//beforeBudgetSDate = BusinessUtil.getBudgetStartDate();
		isEdit = false;
	}

	public void rebindData() {
		currencyList = currencyService.findAllCurrency();
	}

	public void createNewDebitVoucher() {
		voucherDto = new VoucherDTO();
		Currency homeCur = currencyService.findHomeCurrency();
		voucherDto.setCurrency(homeCur);
		voucherDto.setHomeExchangeRate(BigDecimal.ONE);
		admin = userProcessService.getLoginUser().isAdmin();
		todayDate = new Date();
		voucherDto.setCreatedUserId(userProcessService.getLoginUser().getId());
		voucherDto.setSettlementDate(new Date());

	}

	public void addVoucher() {
		// if (validate()) {
		try {
			
					if (voucherDto.getHomeExchangeRate().doubleValue() > 0) {
						String voucherNo = tlfService.addVoucher(voucherDto, TranCode.CSDEBIT);
						addInfoMessage(null, MessageId.INSERT_SUCCESS, voucherNo);
						createNewDebitVoucher();
						rebindData();
					} else {
						addErrorMessage(null, MessageId.NO_EXCHANGE_RATE,
								DateUtils.formatDateToString(null != voucherDto.getSettlementDate() ? voucherDto.getSettlementDate() : new Date()));
					}
			isEdit = false;
		} catch (SystemException e) {
			handleSysException(e);
		}
	}

	public void selectRate() {
		if (voucherDto.getCurrency() != null) {
			double exchangeRate = 0.0;
			if (null != voucherDto.getSettlementDate()) {
				exchangeRate = tlfService.getExchangeRate(voucherDto.getCurrency(), RateType.CS, voucherDto.getSettlementDate());
			} else {
				exchangeRate = tlfService.getExchangeRate(voucherDto.getCurrency(), RateType.CS, new Date());
			}
			voucherDto.setHomeExchangeRate(BigDecimal.valueOf(exchangeRate));
		}
		PrimeFaces.current().resetInputs("voucherForm:exchangeRate");
	}

	public boolean validate() {
		boolean valid = true;

		Date budgetEndDate = BusinessUtil.getBudgetEndDate();
		Date budgetStartDate = BusinessUtil.getBudgetStartDate();
		// nextYear = DateUtils.plusYears(budgetEndDate, 1);
		if (isValidVoucherDate()) {
			valid = false;
			addInfoMessage(null, MessageId.VALID_VOUCHER_DATE, DateUtils.formatDateToString(budgetStartDate), DateUtils.formatDateToString(budgetEndDate));
		}
		return valid;
	}

	public boolean isValidVoucherDate() {
		boolean validate = false;
		if (voucherDto.getSettlementDate().before(minDate) && voucherDto.getSettlementDate().equals(minDate)) {
			validate = true;
		}
		return validate;
	}

	

	public String returnDashBoard() {
		return "home.xhtml?faces-redirect=true";
	}



	public void changeCurrency() {
		voucherDto.setCcoa(null);
		selectRate();
	}

	public void changeDate() {
		selectRate();
	}

	public void selectCCOAAccountCode() {
		selectCCOAAccountCode(voucherDto.getCurrency());
	}

	public void returnCCOAAccountCode(SelectEvent event) {
		CurrencyChartOfAccount ccoa = (CurrencyChartOfAccount) event.getObject();
		voucherDto.setCcoa(ccoa);
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

	public VoucherDTO getVoucherDto() {
		return voucherDto;
	}

	public void setVoucherDto(VoucherDTO voucherDto) {
		this.voucherDto = voucherDto;
	}

	public boolean isAdmin() {
		return admin;
	}

	public Date getTodayDate() {
		return todayDate;
	}

	public Date getMinDate() {
		return minDate;
	}

	public Date getBeforeBudgetSDate() {
		return beforeBudgetSDate;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public String getEditPassword() {
		return editPassword;
	}

	public void setEditPassword(String editPassword) {
		this.editPassword = editPassword;
	}

}
