package org.tech.java.web.common;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.tech.hms.common.validation.ErrorMessage;
import org.tech.java.component.SystemException;

@Component
@ApplicationScope
public class BaseBean {

	/**
	 *********************** Web Utilities *******************
	 */
	protected FacesContext getFacesContext() {
		System.out.println("FacesContext.getCurrentInstance():" + FacesContext.getCurrentInstance());
		return FacesContext.getCurrentInstance();
	}

	protected ServletContext getServletContext() {

		System.out.println("(ServletContext) getFacesContext().getExternalContext().getContext()" + (ServletContext) getFacesContext().getExternalContext().getContext());
		return (ServletContext) getFacesContext().getExternalContext().getContext();
	}

	protected Application getApplication() {
		return getFacesContext().getApplication();
	}

	protected Map getApplicationMap() {
		return getFacesContext().getExternalContext().getApplicationMap();
	}

	@SuppressWarnings("rawtypes")
	protected Map getSessionMap() {
		return getFacesContext().getExternalContext().getSessionMap();
	}

	@SuppressWarnings("unchecked")
	protected void putParam(String key, Object obj) {
		getSessionMap().put(key, obj);
	}

	protected Object getParam(String key) {
		return getSessionMap().get(key);
	}

	protected boolean isExistParam(String key) {
		return getSessionMap().containsKey(key);
	}

	protected void removeParam(String key) {
		if (isExistParam(key)) {
			getSessionMap().remove(key);
		}
	}

	protected ResourceBundle getBundle() {
		return ResourceBundle.getBundle(getApplication().getMessageBundle(), getFacesContext().getViewRoot().getLocale());
	}

	protected String getWebRootPath() {
		Object context = getFacesContext().getExternalContext().getContext();
		String systemPath = ((ServletContext) context).getRealPath("/");
		return systemPath;
	}

	/**
	 *********************** Message Utilities *******************
	 */
	protected String getMessage(String id, Object... params) {
		String text = null;
		try {
			text = getBundle().getString(id);
		} catch (MissingResourceException e) {
			text = "!! key " + id + " not found !!";
		}
		if (params != null) {
			MessageFormat mf = new MessageFormat(text);
			text = mf.format(params, new StringBuffer(), null).toString();
		}
		return text;
	}

	protected void addWranningMessage(String id, String errorCode, Object... params) {
		String message = getMessage(errorCode, params);
		getFacesContext().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
	}

	protected void addInfoMessage(String id, String errorCode, Object... params) {
		String message = getMessage(errorCode, params);
		getFacesContext().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
	}

	protected void addErrorMessage(String id, String errorCode, Object... params) {
		String message = getMessage(errorCode, params);
		getFacesContext().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, id == null ? "" : message));
	}

	protected void addErrorMessage(String message) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
	}

	protected void addErrorMessage(ErrorMessage errorMessage) {
		String message = getMessage(errorMessage.getErrorcode(), errorMessage.getParams());
		getFacesContext().addMessage(errorMessage.getId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
	}

	protected void addInfoMessage(String message) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

	protected void addWranningMessage(String message) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
	}

	protected void handleSysException(SystemException systemException) {
		String errorCode = systemException.getErrorCode();
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage(errorCode), "");
		getFacesContext().addMessage(null, facesMessage);
	}

	protected void handleException(Exception exception) {
		String message = exception.getMessage() == null ? "System Error." : exception.getMessage();
		exception.printStackTrace();
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, message, "");
		getFacesContext().addMessage(null, facesMessage);
	}

	/**
	 *********************** Dialog Information *******************
	 */
	private static Map<String, Object> dialogOptions;

	public static Map<String, Object> getDialogOptions() {
		if (dialogOptions == null) {
			dialogOptions = new HashMap<String, Object>();
			dialogOptions.put("modal", true);
			dialogOptions.put("draggable", false);
			dialogOptions.put("resizable", false);
			dialogOptions.put("width", 640);
			dialogOptions.put("height", 340);
			dialogOptions.put("contentHeight", "100%");
			dialogOptions.put("contentWidth", "100%");
		}
		return dialogOptions;
	}

	public void selectRole() {
		PrimeFaces.current().dialog().openDynamic("roleDialog", getDialogOptions(), null);
	}

	public void selectUnit() {
		PrimeFaces.current().dialog().openDynamic("unitDialog", getDialogOptions(), null);
	}

	public void selectCountry() {
		PrimeFaces.current().dialog().openDynamic("countryDialog", getDialogOptions(), null);
	}

	public void selectProvince() {
		PrimeFaces.current().dialog().openDynamic("provinceDialog", getDialogOptions(), null);
	}

	public void selectDistrict() {
		PrimeFaces.current().dialog().openDynamic("districtDialog", getDialogOptions(), null);
	}

	public void selectTownship() {
		PrimeFaces.current().dialog().openDynamic("townshipDialog", getDialogOptions(), null);
	}

	public void selectPackagingUnit() {
		PrimeFaces.current().dialog().openDynamic("packagingunitDialog", getDialogOptions(), null);
	}

}
