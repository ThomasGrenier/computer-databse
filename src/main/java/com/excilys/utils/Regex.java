package com.excilys.utils;

public enum Regex {
	DIGIT("^[0-9]+$"),
	DATE_FORMAT("^\\d{4}-(0[1-9]|1[012])-((0[1-9]|[12][0-9]|3[01]) (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2}))$");
	
	private String regex;
	
	private Regex(String regex) {
		this.regex = regex;
	}
	
	public String getRegex() {
		return regex;
	}
}
