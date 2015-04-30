package com.excilys.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerModel.
 */
@Entity
@Table(name="computer")
public class ComputerModel {

	/** The id. */
	@Id
	@Column(name="id")
	private long id;

	/** The name. */
	@Column(name="name")
	private String name;

	/** The introduced. */
	@Column(name="introduced")
	@Type(type="com.excilys.utils.CustomLocalDateTimeUserType")
	private LocalDateTime introduced;

	/** The discontinued. */
	@Column(name="discontinued")
	@Type(type="com.excilys.utils.CustomLocalDateTimeUserType")
	private LocalDateTime discontinued;

	/** The company. */
	@OneToOne
	private CompanyModel company;
	
	/**
	 * Instantiates a new computer model.
	 *
	 * @param id the id
	 * @param name the name
	 * @param introduced the introduced
	 * @param discontinued the discontinued
	 * @param company the company
	 */
	/*public ComputerModel(long id, String name, LocalDateTime introduced, LocalDateTime discontinued, CompanyModel company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}*/
	
	/**
	 * Instantiates a new computer model.
	 */
	public ComputerModel() {
		this.name = "";
		this.introduced = null;
		this.discontinued = null;
		this.company = null;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the introduced.
	 *
	 * @return the introduced
	 */
	public LocalDateTime getIntroduced() {
		return introduced;
	}

	/**
	 * Sets the introduced.
	 *
	 * @param introduced the new introduced
	 */
	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}

	/**
	 * Gets the discontinued.
	 *
	 * @return the discontinued
	 */
	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	/**
	 * Sets the discontinued.
	 *
	 * @param discontinued the new discontinued
	 */
	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public CompanyModel getCompany() {
		return company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company the new company
	 */
	public void setCompany(CompanyModel company) {
		this.company = company;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComputerModel other = (ComputerModel) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(id)
		.append(" | " + name)
		.append(" | " + introduced)
		.append(" | " + discontinued)
		.append(" | " + ((company == null) ? "null" : company.toString()) + "\n");
		return result.toString();
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		ComputerModel c;
		
		private Builder() {
			c = new ComputerModel();
		}
		
		public Builder id(long id) {
			c.id = id;
			return this;
		}
		
		public Builder name(String name) {
			c.name = name;
			return this;
		}
		
		public Builder introduced(LocalDateTime intro) {
			c.introduced = intro;
			return this;
		}
		
		public Builder discontinued(LocalDateTime disco) {
			c.discontinued = disco;
			return this;
		}
		
		public Builder company(CompanyModel company) {
			c.company = company;
			return this;
		}
		
		public ComputerModel build() {
			return c;
		}
	}
}
