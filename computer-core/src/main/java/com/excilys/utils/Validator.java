package com.excilys.utils;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

public class Validator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Validator.class);

	public static boolean isValidDate(String date) {
		if (date != null) {
			if (!date.isEmpty()) {
				String locale = LocaleContextHolder.getLocale().toString();
				if (locale.equals("fr")) {
					if (Pattern.matches(Regex.DATE_FORMAT_FR.getRegex(), date.trim())) {
						LOGGER.error("Valid FR date");
						return true;
					}
				} else if (locale.equals("en")) {
					if (Pattern.matches(Regex.DATE_FORMAT_EN.getRegex(), date.trim())) {
						LOGGER.error("Valid EN date");
						return true;
					}
				}
			}
		} 
		LOGGER.error("Error because of date is null");
		return false;
	}
}
