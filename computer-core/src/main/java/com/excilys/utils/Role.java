package com.excilys.utils;

// TODO: Auto-generated Javadoc
/**
 * The Enum Role.
 */
public enum Role {
	
	/** The admin. */
	ADMIN("ROLE_ADMIN"),
	
	/** The user. */
	USER("ROLE_USER");
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new role.
	 *
	 * @param name the name
	 */
	private Role(String name) {
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return name;
	}
}
