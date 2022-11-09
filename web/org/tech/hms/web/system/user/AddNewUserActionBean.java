package org.tech.hms.web.system.user;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.tech.hms.common.ContactInfo;
import org.tech.hms.common.validation.MessageId;
import org.tech.hms.role.Role;
import org.tech.hms.user.User;
import org.tech.hms.user.service.interfaces.IUserService;
import org.tech.java.component.SystemException;
import org.tech.java.web.common.BaseBean;

@Named(value = "AddNewUserActionBean")
@Scope(value = "view")
public class AddNewUserActionBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IUserService userService;

	private String userId;
	private boolean createNew;
	private User user;

	@PostConstruct
	public void init() {
		userId = (String) getParam("UserId");
		if (userId == null) {
			createNewUser();
		} else {
			createNew = false;
			user = userService.findById(userId);
			if (user.getContactInfo() == null) {
				user.setContactInfo(new ContactInfo());
			}
			user.setPassword(userService.getDecodedPassword(user));
		}
	}

	public void createNewUser() {
		createNew = true;
		user = new User();
		removeParam("UserId");
	}

	public String addNewUser() {
		try {
			return "addNewUser";
		} catch (SystemException ex) {
			handleSysException(ex);
		}
		return "";
	}

	public void saveUser() {
		try {
			if (createNew) {
				userService.addNewUser(user);
			} else {
				userService.updateUser(user);
			}
			addInfoMessage(null, MessageId.SAVE_SUCCESS, user.getUserCode());
			createNewUser();
		} catch (SystemException ex) {
			handleSysException(ex);
		}
	}

	public String navManageUser() {
		return "manageUser";
	}

	public boolean isCreateNew() {
		return createNew;
	}

	public User getUser() {
		return user;
	}

	@SuppressWarnings("rawtypes")
	public void returnRole(SelectEvent event) {
		Role role = (Role) event.getObject();
		user.setRole(role);
	}
	
	@PreDestroy
	public void destroy() {
		removeParam("UserId");
	}

}
