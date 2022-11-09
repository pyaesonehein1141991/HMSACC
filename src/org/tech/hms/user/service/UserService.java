package org.tech.hms.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.user.USR001;
import org.tech.hms.user.User;
import org.tech.hms.user.persistence.interfaces.IUserDAO;
import org.tech.hms.user.service.interfaces.IUserService;
import org.tech.java.component.SystemException;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.service.BaseService;
import org.tech.java.component.service.PasswordCodecHandler;
import org.tech.java.component.service.interfaces.IDataRepService;

@Service(value = "UserService")
public class UserService extends BaseService implements IUserService {

	@Resource(name = "UserDAO")
	private IUserDAO userDAO;

	@Resource(name = "DataRepService")
	private IDataRepService<User> dataRepService;

	@Resource(name = "PasswordCodecHandler")
	private PasswordCodecHandler codecHandler;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<USR001> findAllUser() throws SystemException {
		List<USR001> result = null;
		try {
			result = userDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of User)", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User findUser(String userCode) throws SystemException {
		User user = userDAO.find(userCode);
		return user;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean authenticate(String usercode, String password) throws SystemException {
		try {
			User user = userDAO.find(usercode);
			if (user != null) {
				String encodedPassword = codecHandler.encode(password);
				if (user.getPassword().equals(encodedPassword)) {
					return true;
				}
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to change passowrd", e);
		}
		return false;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User addNewUser(User user) throws SystemException {
		try {
			user.setPassword(codecHandler.encode(user.getPassword()));
			dataRepService.insert(user);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to add a User", e);
		}
		return user;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User updateUser(User user) throws SystemException {
		try {
			user.setPassword(codecHandler.encode(user.getPassword()));
			dataRepService.update(user);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to update user", e);
		}
		return user;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(User user) throws SystemException {
		try {
			dataRepService.delete(user);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to delete user", e);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUserById(String id) throws SystemException {
		try {
			userDAO.deleteById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to delete user", e);
		}
	}

	public String getDecodedPassword(User user) {
		return codecHandler.decode(user.getPassword());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User findById(String id) throws SystemException {
		User result = null;
		try {
			result = userDAO.findById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find User with id " + id + ")", e);
		}
		return result;
	}

}