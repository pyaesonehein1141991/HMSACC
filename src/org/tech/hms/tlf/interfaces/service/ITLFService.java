package org.tech.hms.tlf.interfaces.service;

import java.util.Date;
import java.util.List;

import org.tech.hms.common.TranCode;
import org.tech.hms.common.dto.coaDto.VoucherDTO;
import org.tech.hms.currency.Currency;
import org.tech.hms.currencydailyrate.RateType;
import org.tech.hms.tlf.TLF;
import org.tech.java.component.SystemException;

public interface ITLFService {
	//public List<TLF> findAllTLF();

	//public List<RateDTO> findRateTLF(RateDTO ratedto);

	//public String getVoucherNo();

	//public List<String> findVoucherNoByBranchIdAndVoucherType(String branchId, VoucherType voucherType);

	//public List<TLF> findVoucherListByReverseZero(String voucherNo);

	//public TranType findCashAccountByVoucherNo(String voucherNo);

	public double getExchangeRate(Currency reqCur, RateType reqRateType, Date rDate) throws SystemException;

	//public COASetup findCOABy(String acName, Currency currency, Branch branch);

	public void addVoucher(List<TLF> dtoList) throws SystemException;

	public String addVoucher(VoucherDTO voucherDto, TranCode transCode) throws SystemException;

	//public void updateVoucher(List<TLF> oldVoucherList, List<EditVoucherDto> voucherList, VoucherType voucherType);

	//public void reverseVoucher(List<TLF> oldList, boolean reverse, VoucherType voucherType);

	//public String addNewTlfByDto(List<JVdto> dtoList);

	String getVoucherNoForVocherTypes(TranCode tranCode, Date settlementDate);

	//String getVoucherNoForVocherTypes(TranCode tranCode);

	//List<GainAndLossDTO> findGainAndLosttList(GainAndLossDTO dto);

	//List<BalanceSheetDTO> generateBalanceSheetByGroup() throws SystemException;

	//List<BalanceSheetDTO> generateBalanceSheet(String branchId, String currencyId, boolean isHomeCurrency, String budgetYear) throws SystemException;

	//List<BalanceSheetDTO> generateBalanceSheetByClone(String branchId, String currencyId, boolean isHomeCurrency, String budgetYear) throws SystemException;

	//List<BalanceSheetDTO> generateBalanceSheetByDate(String branchId, String currencyId, Date fromDate, Date toDate, boolean isHomeCurrency) throws SystemException;

	//List<BalanceSheetDTO> generateCloneBalanceSheetByDate(String branchId, String currencyId, Date fromDate, Date toDate, boolean isHomeCurrency) throws SystemException;

}
