package org.tech.java.component.idgen.service;

import java.lang.reflect.Field;
import java.util.Date;

import org.eclipse.persistence.descriptors.DescriptorEvent;
import org.eclipse.persistence.descriptors.DescriptorEventAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tech.hms.common.UserRecorder;
import org.tech.hms.process.interfaces.IUserProcessService;

@Component
public class IDInterceptor extends DescriptorEventAdapter {

	private static IUserProcessService userProcessService;

	@Autowired(required = true)
	@Qualifier("UserProcessService")
	public void setUserProcessService(IUserProcessService userProcessService) {
		IDInterceptor.userProcessService = userProcessService;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void preInsert(DescriptorEvent event) {
		try {
			Object obj = event.getObject();
			Class cla = obj.getClass();
			Field userRecorder = cla.getDeclaredField("userRecorder");
			userRecorder.setAccessible(true);
			UserRecorder recorder = (UserRecorder) userRecorder.get(obj);
			if (recorder == null) {
				recorder = new UserRecorder();
				recorder.setCreatedUserId(userProcessService.getLoginUser().getId());
				recorder.setCreatedDate(new Date());
			}
			userRecorder.set(obj, recorder);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void preUpdateWithChanges(DescriptorEvent event) {
		try {
			Object obj = event.getObject();
			Class cla = obj.getClass();
			Field userRecorder = cla.getDeclaredField("userRecorder");
			userRecorder.setAccessible(true);
			UserRecorder recorder = (UserRecorder) userRecorder.get(obj);
			recorder.setUpdatedUserId(userProcessService.getLoginUser().getId());
			recorder.setUpdatedDate(new Date());
			userRecorder.set(obj, recorder);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
