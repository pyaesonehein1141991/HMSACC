package org.tech.hms.tlf.interfaces;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.branch.Branch;
import org.tech.hms.coasetup.COASetup;
import org.tech.hms.coasetup.persistence.interfaces.ICOASetupDAO;
import org.tech.hms.coasetup.persistence.interfaces.ITranTypeDAO;
import org.tech.hms.common.DateUtils;
import org.tech.hms.common.SystemConstants;
import org.tech.hms.common.TranCode;
import org.tech.hms.common.TranType;
import org.tech.hms.common.dto.coaDto.VoucherDTO;
import org.tech.hms.currency.Currency;
import org.tech.hms.currencydailyrate.RateType;
import org.tech.hms.currencydailyrate.persistence.interfaces.IRateInfoDAO;
import org.tech.hms.process.interfaces.IUserProcessService;
import org.tech.hms.tlf.TLF;
import org.tech.hms.tlf.interfaces.service.ITLFService;
import org.tech.java.component.SystemException;
import org.tech.java.component.idgen.IDGen;
import org.tech.java.component.idgen.service.interfaces.ICustomIDGenerator;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.BaseService;
import org.tech.java.component.service.interfaces.IDataRepService;

@Service(value = "TLFService")
public class TLFService extends BaseService implements ITLFService {

	//@Resource(name = "TLFDAO")
	//private ITLFDAO tlfDao;

	@Resource(name = "DataRepService")
	private IDataRepService<TLF> dataRepService;

	@Resource(name = "RateInfoDAO")
	private IRateInfoDAO rateInfoDAO;

	@Resource(name = "COASetupDAO")
	private ICOASetupDAO coaSetupDAO;

	//@Resource(name = "TLFHISTDAO")
	//private ITLFHISTDAO tlfHistoryDAO;

	@Resource(name = "TranTypeDAO")
	private ITranTypeDAO tranTypeDAO;

	@Resource(name = "CustomIDGenerator")
	private ICustomIDGenerator customIDGenerator;

	@Resource(name = "UserProcessService")
	private IUserProcessService userProcessService;

	
	@Transactional(propagation = Propagation.REQUIRED)
	public String addVoucher(VoucherDTO voucherDto, TranCode tranCode) throws SystemException {
		try {
			Date settlementDate = null;
			Date createdDate = null;
			if (null != voucherDto.getSettlementDate()) {
				settlementDate = voucherDto.getSettlementDate();
				createdDate = voucherDto.getSettlementDate();
			} else {
				settlementDate = new Date();
			}

			TranType tranType = null;
			boolean reverse = false;
			boolean paid = true;
			String referenceType = null;
			List<TLF> tlfList = new ArrayList<TLF>();
			TLF tlf = null;
			Branch branch = null;
			COASetup coaSetup = null;
			String voucherNo = getVoucherNoForVocherTypes(tranCode, settlementDate);
			/* String voucherNo = voucherDto.getVoucherNo(); */
			branch = userProcessService.getLoginUser().getBranch();
			// .round(new MathContext(6))
			voucherDto.setHomeAmount(voucherDto.getAmount().multiply(voucherDto.getHomeExchangeRate()).setScale(2, RoundingMode.HALF_UP));
			voucherDto.setLocalAmount(voucherDto.getAmount().setScale(2, RoundingMode.HALF_UP));

			// credit account
			tranType = tranTypeDAO.findByTransCode(tranCode);
			tlf = new TLF(voucherDto, voucherNo, tranType, branch, reverse, paid, referenceType, settlementDate, createdDate, voucherDto.getCreatedUserId());
			tlfList.add(tlf);

			// cash account
			coaSetup = coaSetupDAO.findCOAByACNameAndCur("CASH", voucherDto.getCurrency());
			tranCode = (tranCode.equals(TranCode.CSCREDIT)) ? TranCode.CSDEBIT : TranCode.CSCREDIT;
			tranType = tranTypeDAO.findByTransCode(tranCode);
			voucherDto.setCcoa(coaSetup.getCcoa());
			tlf = new TLF(voucherDto, voucherNo, tranType, branch, reverse, paid, referenceType, settlementDate, createdDate, voucherDto.getCreatedUserId());
			tlfList.add(tlf);

			addVoucher(tlfList);
			return voucherNo;
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to add voucher", e);
		}
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public String getVoucherNoForVocherTypes(TranCode tranCode, Date settlementDate) {
		String voucherNo = null;
		/*
		 * String voucherCodeprefix = ""; if
		 * (TranCode.CSCREDIT.equals(tranCode)) { voucherCodeprefix = "R"; }
		 * else if (TranCode.CSDEBIT.equals(tranCode)) { voucherCodeprefix =
		 * "P"; } else { voucherCodeprefix = "J"; }
		 */
		int month = DateUtils.getMonthFromDate(settlementDate) + 1;
		int year = DateUtils.getYearFromDate(settlementDate);
		IDGen idgen = null;
		if (TranCode.CSCREDIT.equals(tranCode)) {
			idgen = customIDGenerator.findCustomIDGenByBranchCodeMonthandYear(SystemConstants.CREDIT_VOUCHER_NO, month, year);
			if (null == idgen) {
				idgen = new IDGen();
				idgen.setGenerateItem(SystemConstants.CREDIT_VOUCHER_NO);
				idgen.setPrefix("VOC/R");
				idgen.setLength(10);
				idgen.setMonth(month);
				idgen.setYear(year);
				//idgen.setBranch(userProcessService.getLoginUser().getBranch());
				customIDGenerator.insert(idgen);

			}
			voucherNo = customIDGenerator.getNextId(SystemConstants.CREDIT_VOUCHER_NO, month, year, settlementDate);
		} else if (TranCode.CSDEBIT.equals(tranCode)) {
			idgen = customIDGenerator.findCustomIDGenByBranchCodeMonthandYear(SystemConstants.DEBIT_VOUCHER_NO, month, year);
			if (null == idgen) {
				idgen = new IDGen();
				idgen.setGenerateItem(SystemConstants.DEBIT_VOUCHER_NO);
				idgen.setPrefix("VOC/P");
				idgen.setLength(10);
				idgen.setMonth(month);
				idgen.setYear(year);
				//idgen.setBranch(userProcessService.getLoginUser().getBranch());
				customIDGenerator.insert(idgen);

			}
			voucherNo = customIDGenerator.getNextId(SystemConstants.DEBIT_VOUCHER_NO, month, year, settlementDate);
		} else {
			idgen = customIDGenerator.findCustomIDGenByBranchCodeMonthandYear(SystemConstants.JOURNAL_VOUCHER_NO, month, year);
			if (null == idgen) {
				idgen = new IDGen();
				idgen.setGenerateItem(SystemConstants.JOURNAL_VOUCHER_NO);
				idgen.setPrefix("VOC/J");
				idgen.setLength(10);
				idgen.setMonth(month);
				idgen.setYear(year);
				//idgen.setBranch(userProcessService.getLoginUser().getBranch());
				customIDGenerator.insert(idgen);

			}
			voucherNo = customIDGenerator.getNextId(SystemConstants.JOURNAL_VOUCHER_NO, month, year, settlementDate);
		}

		return voucherNo;
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void addVoucher(List<TLF> tlfList) throws SystemException {
		// If Class_Authorizor != Globalizer.Class_Authorizor
		// show error message ==>
		try {
			for (TLF tlf : tlfList) {
				dataRepService.insert(tlf);
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to add a Voucher", e);
		}
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public double getExchangeRate(Currency currency, RateType rateType, Date date) throws SystemException {
		double exchangeRate = 0.0;
		try {
			// RateInfo result = null;
			if (currency.getIsHomeCur()) {
				exchangeRate = 1;
			} else {
				exchangeRate = rateInfoDAO.findExchangeRateBy(currency, rateType, DateUtils.formatDate(date));
				// exchangeRate = result == null ? 0 :
				// result.getExchangeRate().doubleValue();
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to Find ExchangeRate", e);
		}
		return exchangeRate;
	}


}