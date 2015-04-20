package com.excilys.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="computer")
public class ComputerModel {

	@Id
	@Column(name="id")
	private long id;

	@Column(name="name")
	private String name;

	@Column(name="introduced")
	@Type(type="com.excilys.utils.CustomLocalDateTimeUserType")
	private LocalDateTime introduced;

	@Column(name="discontinued")
	@Type(type="com.excilys.utils.CustomLocalDateTimeUserType")
	private LocalDateTime discontinued;

	@OneToOne
	private CompanyModel company;
	
	public ComputerModel(long id, String name, LocalDateTime introduced, LocalDateTime discontinued, CompanyModel company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}
	
	public ComputerModel() {
		this.name = "";
		this.introduced = null;
		this.discontinued = null;
		this.company = null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}

	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}

	public CompanyModel getCompany() {
		return company;
	}

	public void setCompany(CompanyModel company) {
		this.company = company;
	}
	
	
	
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

	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(id)
		.append(" | " + name)
		.append(" | " + introduced)
		.append(" | " + discontinued)
		.append(" | " + ((company == null) ? "null" : company.toString()) + "\n");
		return result.toString();
	}
}
