package org.tech.hms.user.service.interfaces;

import java.util.List;

import org.tech.hms.user.USR001;
import org.tech.hms.user.User;
import org.tech.java.component.SystemException;

public interface IUserService {

	public List<USR001> findAllUser() throws SystemException;

	public User findUser(String userCode) throws SystemException;

	public User findById(String id) throws SystemException;

	public boolean authenticate(String username, String password) throws SystemException;

	public User addNewUser(User user) throws SystemException;

	public User updateUser(User user) throws SystemException;

	public void deleteUser(User user) throws SystemException;
	
	public void deleteUserById(String id) throws SystemException;

	public String getDecodedPassword(User user) throws SystemException;
}
