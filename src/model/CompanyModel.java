package model;

public class CompanyModel {

	private long id;
	
	private String name;
	
	public CompanyModel(long id, String name) {
		this.id = id;
		this.name = name;
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
	
	public String toString() {
		StringBuilder result = new StringBuilder()
		.append(id)
		.append(" | " + name + "\n");
		return result.toString();
	}
}
