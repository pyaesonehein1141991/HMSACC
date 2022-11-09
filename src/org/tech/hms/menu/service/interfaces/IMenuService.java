package org.tech.hms.menu.service.interfaces;

import java.util.List;

import org.tech.hms.menu.MainMenu;
import org.tech.hms.menu.MenuItem;
import org.tech.hms.menu.SubMenu;
import org.tech.java.component.SystemException;


public interface IMenuService {
	public void addNewMainMenu(MainMenu mainMenu) throws SystemException;

	public void updateMainMenu(MainMenu mainMenu) throws SystemException;

	public void deleteMainMenu(MainMenu mainMenu) throws SystemException;

	public void addNewSubMenu(SubMenu subMenu) throws SystemException;

	public void updateSubMenu(SubMenu subMenu) throws SystemException;

	public void deleteSubMenu(SubMenu subMenu) throws SystemException;

	public void addNewMenuItem(MenuItem menuItem) throws SystemException;

	public void updateMenuItem(MenuItem menuItem) throws SystemException;

	public void deleteMenuItem(MenuItem menuItem) throws SystemException;

	public MainMenu findMainMenuById(String id) throws SystemException;

	public List<MainMenu> findAllMainMenu() throws SystemException;
}
