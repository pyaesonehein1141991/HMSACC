package org.tech.hms.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.tech.hms.common.ContactInfo;
import org.tech.hms.common.TableName;
import org.tech.hms.common.UserRecorder;
import org.tech.hms.role.Role;
import org.tech.java.component.idgen.service.IDInterceptor;

@Entity
@Table(name = TableName.USER)
@TableGenerator(name = "USERS_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "USERS_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u ORDER BY u.name ASC"),
		@NamedQuery(name = "User.findByUsercode", query = "SELECT u FROM User u WHERE u.userCode = :userCode"),
		@NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
		@NamedQuery(name = "User.deleteById", query = "DELETE FROM User u WHERE u.id = :id"),
		@NamedQuery(name = "User.findUserId", query = "SELECT u.id FROM User u ORDER BY u.name ASC") })
@EntityListeners(IDInterceptor.class)
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "USERS_GEN")
	private String id;

	private String userCode;

	private String password;

	private String name;

	private boolean isAdmin;

	private boolean isEditAllow;

	private boolean isDeleteAllow;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLEID", referencedColumnName = "ID")
	private Role role;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfBirth;

	@Embedded
	private ContactInfo contactInfo;

	@Version
	private int version;

	@Embedded
	private UserRecorder userRecorder;

	public User() {
		this.contactInfo = new ContactInfo();
	}

	public User(String id, String usercode, String password, String name) {
		this.id = id;
		this.userCode = usercode;
		this.password = password;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public UserRecorder getUserRecorder() {
		return userRecorder;
	}

	public void setUserRecorder(UserRecorder userRecorder) {
		this.userRecorder = userRecorder;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isEditAllow() {
		return isEditAllow;
	}

	public void setEditAllow(boolean isEditAllow) {
		this.isEditAllow = isEditAllow;
	}

	public boolean isDeleteAllow() {
		return isDeleteAllow;
	}

	public void setDeleteAllow(boolean isDeleteAllow) {
		this.isDeleteAllow = isDeleteAllow;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contactInfo, dateOfBirth, id, isAdmin, isDeleteAllow, isEditAllow, name, password, userCode, userRecorder, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(contactInfo, other.contactInfo) && Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(id, other.id) && isAdmin == other.isAdmin
				&& isDeleteAllow == other.isDeleteAllow && isEditAllow == other.isEditAllow && Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(userCode, other.userCode) && Objects.equals(userRecorder, other.userRecorder) && version == other.version;
	}

}
