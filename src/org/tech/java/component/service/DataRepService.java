package org.tech.java.component.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.java.component.SystemException;
import org.tech.java.component.persistence.exception.DAOException;
import org.tech.java.component.persistence.interfaces.IDataRepository;
import org.tech.java.component.service.interfaces.IDataRepService;

@Service(value = "DataRepService")
public class DataRepService<T> extends BaseService implements IDataRepService<T> {

	@Resource(name = "DataRepository")
	private IDataRepository<T> dataRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void insert(Object object) {
		try {
			dataRepository.insert(object);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to add a new " + object.getClass().getName(), e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public T update(T param) {
		try {
			dataRepository.update(param);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to update a " + param.getClass().getName(), e);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Object object) {
		try {
			dataRepository.delete(object);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to delete a " + object.getClass().getName(), e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public T findById(Class<T> paramClass, Object paramObject) {
		T result = null;
		try {
			result = dataRepository.findById(paramClass, paramObject);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(),
					"Failed to find a " + paramClass.getName() + "(ID : " + paramObject.toString() + ")", e);
		}
		return result;
	}

	@Override
	public List<T> findAll(Class<T> param) {
		try {
			return dataRepository.findAll(param);
		} catch (NoResultException ne) {
			return new ArrayList<>();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all ", e);
		}
	}

}
