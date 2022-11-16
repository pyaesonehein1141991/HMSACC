/*
 * package org.tech.hms.web.system.codesetup;
 * 
 * import java.io.Serializable; import java.util.List;
 * 
 * import javax.annotation.PostConstruct; import javax.inject.Named;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Scope; import
 * org.tech.hms.codesetup.AccountCodeType; import
 * org.tech.hms.codesetup.service.interfaces.ICodeSetupService; import
 * org.tech.hms.common.dto.coaDto.CoaDTO; import
 * org.tech.hms.common.validation.MessageId; import
 * org.tech.hms.currency.Currency; import
 * org.tech.hms.currency.service.interfaces.ICurrencyService; import
 * org.tech.java.component.SystemException; import
 * org.tech.java.web.common.BaseBean;
 * 
 * import lombok.Getter; import lombok.Setter;
 * 
 * @Named(value = "ManageCodeSetupActionBean")
 * 
 * @Scope(value = "view") public class ManageCodeSetupActionBean extends
 * BaseBean implements Serializable {
 * 
 * private static final long serialVersionUID = 1L;
 * 
 * @Autowired protected ICodeSetupService codesetupService;
 * 
 * 
 * @Getter
 * 
 * @Setter private boolean createNew;
 * 
 * @Getter
 * 
 * @Setter private AccountCodeType accountCodeType;
 * 
 * @Getter
 * 
 * @Setter private List<AccountCodeType> codeSetupList;
 * 
 * @PostConstruct public void init() { createNewCodeSetup(); loadCodeSetup(); }
 * 
 * public String createNewaccodeType() { return
 * "manageCreateNewacCodeType.xhtml?faces-redirect=true"; } public void
 * createNewCodeSetup() { createNew = true; accountCodeType = new
 * AccountCodeType(); }
 * 
 * public void loadCodeSetup() { codeSetupList =
 * codesetupService.findAllCodeSetup(); }
 * 
 * 
 * public String addNewCodeSetup() { try { if (createNew) {
 * codesetupService.addNewCodeSetup(accountCodeType); loadCodeSetup(); } else {
 * codesetupService.updateCodeSetup(accountCodeType); loadCodeSetup(); }
 * addInfoMessage(null, MessageId.SAVE_SUCCESS, accountCodeType.getName());
 * createNewCodeSetup(); } catch (SystemException ex) { handleSysException(ex);
 * } return "managecodestepup.xhtml?faces-redirect=true"; }
 * 
 * public String deleteCodeSetup(AccountCodeType accountCodeType) { try {
 * codesetupService.deleteCodeSetup(accountCodeType); addInfoMessage(null,
 * MessageId.DELETE_SUCCESS, accountCodeType.getName());
 * codeSetupList.remove(accountCodeType); } catch (SystemException ex) {
 * handleSysException(ex); } return null; }
 * 
 * public void prepareUpdateCodeSetup(AccountCodeType accountCodeType) {
 * createNew = false; this.accountCodeType = accountCodeType; } public void
 * updateCodeSetup() { try { codesetupService.updateCodeSetup(accountCodeType);
 * addInfoMessage(null, MessageId.UPDATE_SUCCESS, accountCodeType.getName());
 * createNewCodeSetup(); loadCodeSetup(); } catch (SystemException ex) {
 * handleSysException(ex); } loadCodeSetup(); }
 * 
 * 
 * 
 * 
 * }
 */