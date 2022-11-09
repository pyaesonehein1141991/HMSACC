package org.tech.java.component.idgen;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.tech.hms.common.TableName;
import org.tech.java.component.idgen.service.IDInterceptor;

@Entity
@Table(name = TableName.CUSTOM_ID_GEN)
@TableGenerator(name = "CUSTOM_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "CUSTOM_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "IDGen.updateIDGen", query = "SELECT r FROM IDGen r WHERE r.generateItem = :generateItem"),
		@NamedQuery(name = "IDGen.findById", query = "SELECT r FROM IDGen r WHERE r.generateItem = :generateItem") })
@Access(value = AccessType.FIELD)
@EntityListeners(IDInterceptor.class)
public class IDGen implements Serializable {
	private static final long serialVersionUID = -6752088843819146101L;
	@Transient
	private String id;
	private String generateItem;
	private int maxValue;
	private String prefix;
	private String suffix;
	private String description;
	private int length;
	private boolean isDateBased;
	@Version
	private int version;
	private int year;
	private int month;

	public IDGen() {

	}

	public IDGen(String generateItem, int maxValue, String prefix, String suffix, String description, int length, boolean isDateBased,  int version, int year,
			int month) {
		this.generateItem = generateItem;
		this.maxValue = maxValue;
		this.prefix = prefix;
		this.suffix = suffix;
		this.description = description;
		this.length = length;
		this.isDateBased = isDateBased;
		this.version = version;
		this.year = year;
		this.month = month;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CUSTOM_GEN")
	@Access(value = AccessType.PROPERTY)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGenerateItem() {
		return generateItem;
	}

	public void setGenerateItem(String generateItem) {
		this.generateItem = generateItem;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isDateBased() {
		return isDateBased;
	}

	public void setDateBased(boolean isDateBased) {
		this.isDateBased = isDateBased;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((generateItem == null) ? 0 : generateItem.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isDateBased ? 1231 : 1237);
		result = prime * result + length;
		result = prime * result + maxValue;
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		result = prime * result + ((suffix == null) ? 0 : suffix.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IDGen other = (IDGen) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (generateItem == null) {
			if (other.generateItem != null)
				return false;
		} else if (!generateItem.equals(other.generateItem))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isDateBased != other.isDateBased)
			return false;
		if (length != other.length)
			return false;
		if (maxValue != other.maxValue)
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (suffix == null) {
			if (other.suffix != null)
				return false;
		} else if (!suffix.equals(other.suffix))
			return false;
		if (version != other.version)
			return false;
		return true;
	}
}
