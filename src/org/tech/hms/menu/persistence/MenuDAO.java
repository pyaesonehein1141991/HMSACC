package org.tech.hms.menu.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tech.hms.menu.MainMenu;
import org.tech.hms.menu.MainMenuValue;
import org.tech.hms.menu.MenuItem;
import org.tech.hms.menu.MenuItemValue;
import org.tech.hms.menu.SubMenu;
import org.tech.hms.menu.SubMenuValue;
import org.tech.hms.menu.persistence.interfaces.IMenuDAO;
import org.tech.java.component.persistence.BasicDAO;
import org.tech.java.component.persistence.exception.DAOException;

@Repository("MenuDAO")
public class MenuDAO extends BasicDAO implements IMenuDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(MainMenu mainMenu) throws DAOException {
		try {
			em.persist(mainMenu);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert MainMenu", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(MainMenu mainMenu) throws DAOException {
		try {
			em.merge(mainMenu);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MainMenu", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(MainMenu mainMenu) throws DAOException {
		try {
			mainMenu = em.merge(mainMenu);
			em.remove(mainMenu);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MainMenu", pe);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MainMenuValue> findMainMenuValueByMainMenuId(String mainMenuId) throws DAOException {
		List<MainMenuValue> results = null;
		try {
			Query query = em.createNamedQuery("MainMenuValue.findByMainMenuId");
			query.setParameter("mainMenuId", mainMenuId);
			results = query.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MainMenu", pe);
		}

		return results;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(MainMenuValue mainMenuValue) throws DAOException {
		try {
			mainMenuValue = em.merge(mainMenuValue);
			em.remove(mainMenuValue);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MainMenu", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(SubMenu subMenu) throws DAOException {
		try {
			em.persist(subMenu);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert SubMenu", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(SubMenu subMenu) throws DAOException {
		try {
			em.merge(subMenu);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update SubMenu", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(SubMenu subMenu) throws DAOException {
		try {
			subMenu = em.merge(subMenu);
			em.remove(subMenu);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update SubMenu", pe);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<SubMenuValue> findSubMenuValueBySubMenuId(String subMenuId) throws DAOException {
		List<SubMenuValue> results = null;
		try {
			Query query = em.createNamedQuery("SubMenuValue.findBySubMenuId");
			query.setParameter("subMenuId", subMenuId);
			results = query.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update SubMenu", pe);
		}

		return results;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(SubMenuValue subMenuValue) throws DAOException {
		try {
			subMenuValue = em.merge(subMenuValue);
			em.remove(subMenuValue);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update SubMenu", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(MenuItem menuItem) throws DAOException {
		try {
			em.persist(menuItem);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert MenuItem", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(MenuItem menuItem) throws DAOException {
		try {
			em.merge(menuItem);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MenuItem", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(MenuItem menuItem) throws DAOException {
		try {
			menuItem = em.merge(menuItem);
			em.remove(menuItem);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MenuItem", pe);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MenuItemValue> findMenuItemValueByMenuItemId(String menuItemId) throws DAOException {
		List<MenuItemValue> results = null;
		try {
			Query query = em.createNamedQuery("MenuItemValue.findByMenuItemId");
			query.setParameter("menuItemId", menuItemId);
			results = query.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MenuItem", pe);
		}

		return results;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(MenuItemValue menuItemValue) throws DAOException {
		try {
			menuItemValue = em.merge(menuItemValue);
			em.remove(menuItemValue);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MenuItem", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MainMenu findById(String id) throws DAOException {
		MainMenu result = null;
		try {
			result = em.find(MainMenu.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MainMenu", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MainMenu> findAll() throws DAOException {
		List<MainMenu> result = null;
		try {
			Query q = em.createNamedQuery("MainMenu.findAll");
			result = q.getResultList();
			q.setMaxResults(50);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of MainMenu", pe);
		}
		return result;
	}

}
