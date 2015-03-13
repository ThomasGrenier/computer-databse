package model;

import java.time.LocalDateTime;

public class ComputerModel {

	private long id;
	
	private String name;
	
	private LocalDateTime introduced;
	
	private LocalDateTime discontinued;
	
	private long idCompany;
	
	public ComputerModel(long id, String name, LocalDateTime introduced, LocalDateTime discontinued, long idCompany) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.idCompany = idCompany;
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

	public long getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(long idCompany) {
		this.idCompany = idCompany;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(id)
		.append(" | " + name)
		.append(" | " + introduced)
		.append(" | " + discontinued)
		.append(" | " + idCompany + "\n");
		return result.toString();
	}
}
