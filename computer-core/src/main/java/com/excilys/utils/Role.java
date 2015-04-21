package com.excilys.utils;

public enum Role {
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	private String name;
	
	private Role(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
