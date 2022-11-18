package org.tech.java.web.model;

import java.lang.reflect.Field;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class SelectableIDDataModel<T> extends ListDataModel<T> implements SelectableDataModel<T> {

	public SelectableIDDataModel() {

	}

	public SelectableIDDataModel(List<T> data) {
		super(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getRowData(String rowKey) {
		List<T> data = (List<T>) getWrappedData();
		for (T val : data) {
			try {
				Field field = val.getClass().getDeclaredField("id");
				field.setAccessible(true);
				String id = (String) field.get(val);
				if (id.equals(rowKey)) {
					return val;
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getRowKey(T val) {
		String id = null;
		try {
			Field field = val.getClass().getDeclaredField("id");
			field.setAccessible(true);
			id = (String) field.get(val);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return id;
	}

}
