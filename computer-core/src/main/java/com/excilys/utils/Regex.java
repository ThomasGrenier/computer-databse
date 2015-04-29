package com.excilys.utils;

// TODO: Auto-generated Javadoc
/**
 * The Enum Regex.
 */
public enum Regex {
	
	/** The digit regex. */
	DIGIT("^[0-9]+$"),
	
	/** The date format regex. */
	DATE_FORMAT("^\\d{4}-(0[1-9]|1[012])-((0[1-9]|[12][0-9]|3[01]) (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2}))$");
	
	/** The regex. */
	private String regex;
	
	/**
	 * Instantiates a new regex.
	 *
	 * @param regex the regex
	 */
	private Regex(String regex) {
		this.regex = regex;
	}
	
	/**
	 * Gets the regex.
	 *
	 * @return the regex
	 */
	public String getRegex() {
		return regex;
	}
}
