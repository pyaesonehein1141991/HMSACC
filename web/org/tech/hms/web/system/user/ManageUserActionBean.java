package org.tech.hms.web.system.user;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.tech.hms.common.validation.MessageId;
import org.tech.hms.user.USR001;
import org.tech.hms.user.service.interfaces.IUserService;
import org.tech.java.component.SystemException;
import org.tech.java.web.common.BaseBean;

@Named(value = "ManageUserActionBean")
@Scope(value = "view")
public class ManageUserActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IUserService userService;

	private List<USR001> userList;

	@PostConstruct
	public void init() {
		rebindData();
	}

	private void rebindData() {
		userList = userService.findAllUser();
	}

	public String navAddNewUser() {
		return "addNewUser";
	}

	public String prepareUpdateUser(USR001 usr001) {
		putParam("UserId", usr001.getId());
		return "addNewUser";
	}

	public String deleteUser(USR001 usr001) {
		try {
			userService.deleteUserById(usr001.getId());
			addInfoMessage(null, MessageId.DELETE_SUCCESS, usr001.getUserCode());
			userList.remove(usr001);
		} catch (SystemException ex) {
			handleSysException(ex);
		}
		return null;
	}

	public List<USR001> getUserList() {
		return userList;
	}

}
