package org.tech.hms.process.interfaces;

import org.tech.hms.user.User;

public interface IUserProcessService {
	public void registerUser(User user);
	public User getLoginUser();
}
