package org.tech.hms.web.menu;

import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.tech.hms.menu.MainMenu;
import org.tech.hms.menu.MainMenuValue;
import org.tech.hms.menu.MenuItem;
import org.tech.hms.menu.MenuItemValue;
import org.tech.hms.menu.SubMenu;
import org.tech.hms.menu.SubMenuValue;
import org.tech.hms.process.interfaces.IUserProcessService;
import org.tech.hms.user.User;
import org.tech.java.web.common.BaseBean;
import org.tech.java.web.common.ParamId;

@SessionScoped
@ManagedBean(name = "MenuActionBean")
public class MenuActionBean extends BaseBean {

	@ManagedProperty(value = "#{UserProcessService}")
	private IUserProcessService userProcessService;

	public void setUserProcessService(IUserProcessService userProcessService) {
		this.userProcessService = userProcessService;
	}

	private User user;
	private MenuModel model;

	@PreDestroy
	public void destroy() {
		FacesContext.getCurrentInstance().getExternalContext().setSessionMaxInactiveInterval(0);
	}

	@PostConstruct
	public void init() {
		user = (User) getParam(ParamId.LOGIN_USER);
		if (user != null && user.getRole() != null) {
			createMenu();
		}
	}

	public void createMenu() {
		model = new DefaultMenuModel();
		DefaultSubMenu mainMenu;
		DefaultSubMenu subMenu;
		DefaultMenuItem menuItem;
		MainMenu roleMainMenu;
		SubMenu roleSubMenu;
		MenuItem roleMenuItem;
		Collections.sort(user.getRole().getMainMenuValueList());
		for (MainMenuValue mainMenuValue : user.getRole().getMainMenuValueList()) {
			roleMainMenu = mainMenuValue.getMainMenu();
			if (mainMenuValue.getSubMenuValueList().size() < 1 && haveAction(roleMainMenu.getAction())) {
				menuItem = DefaultMenuItem.builder().value(roleMainMenu.getName()).command(roleMainMenu.getAction()).build();
				model.getElements().add(menuItem);
			} else if (mainMenuValue.getSubMenuValueList().size() > 0) {
				mainMenu = DefaultSubMenu.builder().label(roleMainMenu.getName()).build();
				Collections.sort(mainMenuValue.getSubMenuValueList());
				for (SubMenuValue subMenuValue : mainMenuValue.getSubMenuValueList()) {
					roleSubMenu = subMenuValue.getSubMenu();
					if (subMenuValue.getMenuItemValueList().size() < 1 && haveAction(roleSubMenu.getAction())) {
						menuItem = DefaultMenuItem.builder().value(roleSubMenu.getName()).command(roleSubMenu.getAction()).build(); 
						mainMenu.getElements().add(menuItem);
					} else if (subMenuValue.getMenuItemValueList().size() > 0) {
						subMenu = DefaultSubMenu.builder().label(roleSubMenu.getName()).build();
						Collections.sort(subMenuValue.getMenuItemValueList());
						for (MenuItemValue menuItemValue : subMenuValue.getMenuItemValueList()) {
							roleMenuItem = menuItemValue.getMenuItem();
							menuItem = DefaultMenuItem.builder().value(roleMenuItem.getName()).command(roleMenuItem.getAction()).build();
							menuItem.setCommand(roleMenuItem.getAction());
							subMenu.getElements().add(menuItem);
						}
						mainMenu.getElements().add(subMenu);
					}
				}
				model.getElements().add(mainMenu);
			}
		}
	}
	
	private boolean haveAction(String action) {
		if (action != null && !action.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unused")
	private boolean haveUrl(String url) {
		if (url != null && url.contains(".xhtml")) {
			return true;
		}
		return false;
	}

	public MenuModel getModel() {
		return model;
	}

	public User getUser() {
		return user;
	}

}
