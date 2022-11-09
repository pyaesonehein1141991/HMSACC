 package org.tech.hms.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

 public class USR001 implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String userCode;
	private Date dateOfBirth;
	
	public USR001() {
		super();
	}
	
	public USR001(String id, String name, String userCode, Date dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.userCode = userCode;
		this.dateOfBirth = dateOfBirth;
	}
	
	public USR001(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.userCode = user.getUserCode();
		this.dateOfBirth = user.getDateOfBirth();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUserCode() {
		return userCode;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateOfBirth, id, name, userCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		USR001 other = (USR001) obj;
		return Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(userCode, other.userCode);
	}

 }
