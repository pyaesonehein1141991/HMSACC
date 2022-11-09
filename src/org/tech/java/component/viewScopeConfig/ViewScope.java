package org.tech.java.component.viewScopeConfig;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.FacesRequestAttributes;

public class ViewScope implements Scope {
	public static final String VIEW_SCOPE_CALLBACKS = "viewScope.callbacks";

	public synchronized Object get(String name, ObjectFactory<?> objectFactory) {
		Object instance = getViewMap().get(name);
		if (instance == null) {
			instance = objectFactory.getObject();
			getViewMap().put(name, instance);
		}
		return instance;
	}

	// NEED TO CHECK => This method will fix for memory leak
	// public Object get(String name, ObjectFactory objectFactory) {
	// Map<String, Object> viewMap =
	// FacesContext.getCurrentInstance().getViewRoot().getViewMap();
	// if (viewMap.containsKey(name)) {
	// return viewMap.get(name);
	// } else {
	// LRUMap lruMap = (LRUMap)
	// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("com.sun.faces.application.view.activeViewMaps");
	// if (lruMap != null && !lruMap.isEmpty() && lruMap.size() > 1) {
	// Iterator itr = lruMap.entrySet().iterator();
	// while (itr.hasNext()) {// Not req
	// Entry entry = (Entry) itr.next();
	// Map<String, Object> map = (Map<String, Object>) entry.getValue();
	// map.clear();
	// itr.remove();
	// break;
	// }
	// }
	// Object object = objectFactory.getObject();
	// viewMap.put(name, object);
	// return object;
	// }
	// }

	@SuppressWarnings("unchecked")
	public Object remove(String name) {
		Object instance = getViewMap().remove(name);
		if (instance != null) {
			Map<String, Runnable> callbacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
			if (callbacks != null) {
				callbacks.remove(name);
			}
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public void registerDestructionCallback(String name, Runnable runnable) {
		Map<String, Runnable> callbacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
		if (callbacks != null) {
			callbacks.put(name, runnable);
		}
	}

	public Object resolveContextualObject(String name) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
		return facesRequestAttributes.resolveReference(name);
	}

	public String getConversationId() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
		return facesRequestAttributes.getSessionId() + "-" + facesContext.getViewRoot().getViewId();
	}

	private Map<String, Object> getViewMap() {
		return FacesContext.getCurrentInstance().getViewRoot().getViewMap();
	}

}
