package org.tech.hms.tlf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.tech.hms.branch.Branch;
import org.tech.hms.common.TableName;
import org.tech.hms.common.TranType;
import org.tech.hms.common.UserRecorder;
import org.tech.hms.common.dto.coaDto.VoucherDTO;
import org.tech.hms.currency.Currency;
import org.tech.hms.currencyChartOfAccount.CurrencyChartOfAccount;
import org.tech.hms.department.Department;
import org.tech.java.component.idgen.service.IDInterceptor;

import lombok.Data;

@Entity
@Table(name = TableName.TLF)
@TableGenerator(name = "TLF_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "TLF_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "TLF.findAll", query = "SELECT t FROM TLF t"),
        @NamedQuery(name = "TLF.findVoucherNoByBranchId", query = "SELECT DISTINCT NEW java.lang.String(t.eNo)  FROM TLF t WHERE t.branch.id=:branchId AND t.tranType.status LIKE :status AND t.reverse=0  AND t.eNo LIKE :eNo"),
        @NamedQuery(name = "TLF.findVoucherListByVoucherNo", query = "SELECT DISTINCT t FROM TLF t, CurrencyChartOfAccount c WHERE "
                + "t.eNo=:voucherNo AND t.ccoa=c AND t.currency=c.currency AND " + "t.branch=c.branch AND t.reverse=0"),
        @NamedQuery(name = "TLF.updateReverseByID", query = "UPDATE TLF t SET t.reverse=:reverse WHERE t.id=:id"),
        })

@EntityListeners(IDInterceptor.class)
@Data
public class TLF implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5685055807452264053L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TLF_GEN")
    private String id;

    private String customerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CCOAID", referencedColumnName = "ID")
    private CurrencyChartOfAccount ccoa;

    private BigDecimal homeAmount;

    private BigDecimal localAmount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENCYID", referencedColumnName = "ID")
    private Currency currency;

    private String chequeNo;

    private String bankId;

    private String purchaseOrderId;

    private String loanId;

    private String narration;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRANTYPEID", referencedColumnName = "ID")
    private TranType tranType;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "DEPARTMENTID", referencedColumnName = "ID")
    private Department department;

    private String orgnTLFID;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCHID", referencedColumnName = "ID")
    private Branch branch;

    private BigDecimal rate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date settlementDate;

    private boolean reverse;

    private String referenceNo;

    private String referenceType;

    private String eNo;

    private boolean paid;

    private String tlfNo;

    private boolean clearing;

    private boolean isRenewal;

    @Version
    private int version;

    @Embedded
	private UserRecorder userRecorder;


    public TLF() {
    }

    public TLF(TranType tranType) {
        super();
        this.tranType = tranType;
    }

    public TLF(String id, String customerId, CurrencyChartOfAccount ccoa, BigDecimal homeAmount, BigDecimal localAmount, Currency currency, String chequeNo, String bankId,
            String purchaseOrderId, String loanId, String narration, TranType tranType, Department department, String orgnTLFID, Branch branch, BigDecimal rate,
            Date settlementDate, boolean reverse, String referenceNo, String referenceType, String eNo, boolean paid, String tlfNo, boolean clearing, boolean isRenewal,
            int version, UserRecorder userRecorder) {
        super();
        this.id = id;
        this.customerId = customerId;
        this.ccoa = ccoa;
        this.homeAmount = homeAmount;
        this.localAmount = localAmount;
        this.currency = currency;
        this.chequeNo = chequeNo;
        this.bankId = bankId;
        this.purchaseOrderId = purchaseOrderId;
        this.loanId = loanId;
        this.narration = narration;
        this.tranType = tranType;
        this.department = department;
        this.orgnTLFID = orgnTLFID;
        this.branch = branch;
        this.rate = rate;
        this.settlementDate = settlementDate;
        this.reverse = reverse;
        this.referenceNo = referenceNo;
        this.referenceType = referenceType;
        this.eNo = eNo;
        this.paid = paid;
        this.tlfNo = tlfNo;
        this.clearing = clearing;
        this.isRenewal = isRenewal;
        this.version = version;
        this.userRecorder = userRecorder;
    }

   

    public TLF(VoucherDTO dto, String voucherNo, TranType tranType, Branch branch, boolean reverse, boolean paid, String referenceType, Date settlementDate) {
        this.ccoa = dto.getCcoa();
        this.eNo = voucherNo;
        this.homeAmount = dto.getHomeAmount();
        this.localAmount = dto.getLocalAmount();
        this.narration = dto.getNarration();
        this.tranType = tranType;
        this.currency = dto.getCurrency();
        this.rate = dto.getHomeExchangeRate();
        this.branch = branch;
        this.reverse = reverse;
        this.paid = paid;
        this.referenceType = referenceType;
        this.settlementDate = settlementDate;
    }

    public TLF(VoucherDTO dto, String voucherNo, TranType tranType, Branch branch, boolean reverse, boolean paid, String referenceType, Date settlementDate, Date createdDate,
            String createdUserId) {
        this.ccoa = dto.getCcoa();
        this.eNo = voucherNo;
        this.homeAmount = dto.getHomeAmount();
        this.localAmount = dto.getLocalAmount();
        this.narration = dto.getNarration();
        this.tranType = tranType;
        this.currency = dto.getCurrency();
        this.rate = dto.getHomeExchangeRate();
        this.branch = branch;
        this.reverse = reverse;
        this.paid = paid;
        this.referenceType = referenceType;
        this.settlementDate = settlementDate;
        if (null != createdDate) {
            this.userRecorder = new UserRecorder();
            this.userRecorder.setCreatedDate(createdDate);
            this.userRecorder.setCreatedUserId(createdUserId);
        }

    }

    public TLF(CurrencyChartOfAccount ccoa, String voucherNo, BigDecimal homeAmount, BigDecimal localAmount, String narration, TranType tranType, Currency currency,
            BigDecimal homeExchangeRate, Branch branch, boolean reverse, boolean paid, String referenceType, Date settlementDate) {
        this.ccoa = ccoa;
        this.eNo = voucherNo;
        this.homeAmount = homeAmount;
        this.localAmount = localAmount;
        this.narration = narration;
        this.tranType = tranType;
        this.currency = currency;
        this.rate = homeExchangeRate;
        this.branch = branch;
        this.reverse = reverse;
        this.paid = paid;
        this.referenceType = referenceType;
        this.settlementDate = settlementDate;
    }

    public TLF(CurrencyChartOfAccount ccoa, String voucherNo, BigDecimal homeAmount, BigDecimal localAmount, String narration, TranType tranType, Currency currency,
            BigDecimal homeExchangeRate, Branch branch, boolean reverse, Date settlementDate, String chequeNo) {
        this.ccoa = ccoa;
        this.eNo = voucherNo;
        this.homeAmount = homeAmount;
        this.localAmount = localAmount;
        this.narration = narration;
        this.tranType = tranType;
        this.currency = currency;
        this.rate = homeExchangeRate;
        this.branch = branch;
        this.reverse = reverse;
        this.settlementDate = settlementDate;
        this.chequeNo = chequeNo;
    }

    public TLF(String id, String customerId, CurrencyChartOfAccount ccoa, BigDecimal homeAmount, BigDecimal localAmount, Currency currency, String chequeNo, String bankId,
            String purchaseOrderId, String loanId, String narration, TranType tranType, String orgnTLFID, Branch branch, BigDecimal rate, Date settlementDate, boolean reverse,
            String referenceNo, String referenceType, String eNo, boolean paid, String tlfNo, boolean clearing, boolean isRenewal) {
        super();
        this.id = id;
        this.customerId = customerId;
        this.ccoa = ccoa;
        this.homeAmount = homeAmount;
        this.localAmount = localAmount;
        this.currency = currency;
        this.chequeNo = chequeNo;
        this.bankId = bankId;
        this.purchaseOrderId = purchaseOrderId;
        this.loanId = loanId;
        this.narration = narration;
        this.tranType = tranType;
        this.orgnTLFID = orgnTLFID;
        this.branch = branch;
        this.rate = rate;
        this.settlementDate = settlementDate;
        this.reverse = reverse;
        this.referenceNo = referenceNo;
        this.referenceType = referenceType;
        this.eNo = eNo;
        this.paid = paid;
        this.tlfNo = tlfNo;
        this.clearing = clearing;
        this.isRenewal = isRenewal;
    }

    public TLF(TLF tlf) {
        this.customerId = tlf.getCustomerId();
        this.ccoa = tlf.getCcoa();
        this.homeAmount = tlf.getHomeAmount();
        this.localAmount = tlf.getLocalAmount();
        this.currency = tlf.getCurrency();
        this.chequeNo = tlf.getChequeNo();
        this.bankId = tlf.getBankId();
        this.purchaseOrderId = tlf.getPurchaseOrderId();
        this.loanId = tlf.getLoanId();
        this.narration = tlf.getNarration();
        this.tranType = tlf.getTranType();
        this.orgnTLFID = tlf.getOrgnTLFID();
        this.branch = tlf.getBranch();
        this.rate = tlf.getRate();
        this.settlementDate = tlf.getSettlementDate();
        this.reverse = tlf.isReverse();
        this.referenceNo = tlf.getReferenceNo();
        this.referenceType = tlf.getReferenceType();
        this.eNo = tlf.geteNo();
        this.paid = tlf.isPaid();
        this.tlfNo = tlf.getTlfNo();
        this.clearing = tlf.isClearing();
        this.isRenewal = tlf.isRenewal();
        this.userRecorder = tlf.getUserRecorder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getHomeAmount() {
        return homeAmount;
    }

    public void setHomeAmount(BigDecimal homeAmount) {
        this.homeAmount = homeAmount;
    }

    public BigDecimal getLocalAmount() {
        return localAmount;
    }

    public void setLocalAmount(BigDecimal localAmount) {
        this.localAmount = localAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public TranType getTranType() {
        return tranType;
    }

    public void setTranType(TranType tranType) {
        this.tranType = tranType;
    }

    public String getOrgnTLFID() {
        return orgnTLFID;
    }

    public void setOrgnTLFID(String orgnTLFID) {
        this.orgnTLFID = orgnTLFID;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public boolean isReverse() {
        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public String geteNo() {
        return eNo;
    }

    public void seteNo(String eNo) {
        this.eNo = eNo;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getTlfNo() {
        return tlfNo;
    }

    public void setTlfNo(String tlfNo) {
        this.tlfNo = tlfNo;
    }

    public boolean isClearing() {
        return clearing;
    }

    public void setClearing(boolean clearing) {
        this.clearing = clearing;
    }

    public boolean isRenewal() {
        return isRenewal;
    }

    public void setRenewal(boolean isRenewal) {
        this.isRenewal = isRenewal;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public CurrencyChartOfAccount getCcoa() {
        return ccoa;
    }

    public void setCcoa(CurrencyChartOfAccount ccoa) {
        this.ccoa = ccoa;
    }

    public TLF(String eNo, CurrencyChartOfAccount ccoa, BigDecimal homeAmount, BigDecimal localAmount, Currency currency, String narration, TranType tranType, Branch branch,
            BigDecimal rate, Date settlementDate, boolean reverse, String referenceNo, String referenceType, boolean paid) {
        this.eNo = eNo;
        this.ccoa = ccoa;
        this.homeAmount = homeAmount;
        this.localAmount = localAmount;
        this.currency = currency;
        this.narration = narration;
        this.tranType = tranType;
        this.branch = branch;
        this.rate = rate;
        this.settlementDate = settlementDate;
        this.reverse = reverse;
        this.referenceNo = referenceNo;
        this.referenceType = referenceType;
        this.paid = paid;

    }
}
