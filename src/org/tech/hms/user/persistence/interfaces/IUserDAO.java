package org.tech.hms.user.persistence.interfaces;

import java.util.List;

import org.tech.hms.user.USR001;
import org.tech.hms.user.User;
import org.tech.java.component.persistence.exception.DAOException;

public interface IUserDAO {
	public List<USR001> findAll() throws DAOException;

	public User find(String username) throws DAOException;

	public User findById(String id) throws DAOException;
	
	public void deleteById(String id) throws DAOException;

}
