package org.tech.java.component.service.interfaces;

import java.util.List;

public interface IDataRepService<T> {

	public void insert(Object object);

	public T update(T param);

	public void delete(Object object);

	public T findById(Class<T> paramClass, Object paramObject);

	List<T> findAll(Class<T> param);
}
