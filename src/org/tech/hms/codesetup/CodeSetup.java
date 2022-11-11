package org.tech.hms.codesetup;



import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.tech.hms.common.TableName;
import org.tech.hms.common.UserRecorder;
import org.tech.java.component.idgen.service.IDInterceptor;


@Entity
@Table(name = TableName.CODESETUP)
@TableGenerator(name = "CODESETUP_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "CODESETUP_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "CodeSetup.findAll", query = "SELECT c FROM CodeSetup c  ORDER BY c.name ASC"),
		@NamedQuery(name = "Currency.findById", query = "SELECT c FROM CodeSetup c WHERE c.id = :id") })
@EntityListeners(IDInterceptor.class)
public class CodeSetup implements Serializable {

	private static final long serialVersionUID = -5785537862626257490L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CODESETUP_GEN")
	private String id;

	@Column(name = "CUR", unique = true)
	private String name;
	private String description;


	@Version
	private int version;

	@Embedded
	private UserRecorder userRecorder;

	public CodeSetup() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
	@Override
	public int hashCode() {
		return Objects.hash(description, id, name, userRecorder, version);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CodeSetup other = (CodeSetup) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(userRecorder, other.userRecorder)
				&& version == other.version;
	}

	
	

}
