package org.tech.hms.nrcPrefix;

import java.io.Serializable;

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
@Table (name = TableName.NRCPREFIX)
@TableGenerator (name = "NRCPREFIX_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL" ,pkColumnValue = "NRCPREFIX_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "NrcPrefix.findAll", query = "SELECT n FROM NrcPrefix n ORDER BY n.id ASC"),
		@NamedQuery(name = "NrcPrefix.findByCode", query = "SELECT n FROM NrcPrefix n WHERE n.nrcCode = :nrcCode"),
		@NamedQuery(name = "NrcPrefix.findById", query = "SELECT n FROM NrcPrefix n WHERE n.id = :id")
		})

@EntityListeners(IDInterceptor.class)
public class NrcPrefix implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.TABLE,generator = "NRCPREFIX_GEN")
	private String id;
	private String nrcCode;
	private  String township;
	
	@Version
	private int version;
	
	@Embedded
	private UserRecorder userRecorder;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNrcCode() {
		return nrcCode;
	}

	public void setNrcCode(String nrcCode) {
		this.nrcCode = nrcCode;
	}

	public String gettownship() {
		return township;
	}

	public void settownship(String township) {
		this.township = township;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nrcCode == null) ? 0 : nrcCode.hashCode());
		result = prime * result + ((township == null) ? 0 : township.hashCode());
		result = prime * result + ((userRecorder == null) ? 0 : userRecorder.hashCode());
		result = prime * result + version;
		return result;
	}

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
		NrcPrefix other = (NrcPrefix) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (nrcCode == null) {
			if (other.nrcCode != null) {
				return false;
			}
		} else if (!nrcCode.equals(other.nrcCode)) {
			return false;
		}
		if (township == null) {
			if (other.township != null) {
				return false;
			}
		} else if (!township.equals(other.township)) {
			return false;
		}
		if (userRecorder == null) {
			if (other.userRecorder != null) {
				return false;
			}
		} else if (!userRecorder.equals(other.userRecorder)) {
			return false;
		}
		if (version != other.version) {
			return false;
		}
		return true;
	}
	
	
	
	

}
