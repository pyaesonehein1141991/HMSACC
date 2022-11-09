package org.tech.hms.process.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.tech.hms.process.interfaces.IUserProcessService;
import org.tech.hms.user.User;

@Service(value = "UserProcessService")
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class UserProcessService implements IUserProcessService {

	private User user;

	@Override
	public void registerUser(User user) {
		this.user = user;
	}

	@Override
	public User getLoginUser() {
		return user;
	}
}
