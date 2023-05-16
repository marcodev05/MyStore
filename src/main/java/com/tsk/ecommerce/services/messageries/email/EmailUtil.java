package com.tsk.ecommerce.services.messageries.email;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtil {

	private static final String EMAIL_REGEX = "([A-Za-z0-9\\.\\_\\-]+[\\.\\_\\-]*[A-Za-z0-9\\.\\_\\-]*)+@([A-Za-z0-9\\.\\_\\-]+[\\.]*[A-Za-z0-9\\.\\_\\-]+)+\\.[A-Za-z]+";

	public static boolean isEmailValid(String valueToValidate) throws IOException {
		final String regexExpression = EMAIL_REGEX;
		Pattern regexPattern = Pattern.compile(regexExpression);
		boolean valid = false;
		if (valueToValidate != null) {
			if (valueToValidate.indexOf("@") <= 0) {
				return false;
			}
			Matcher matcher = regexPattern.matcher(valueToValidate);
			valid = matcher.matches();
		} else { // The case of empty Regex expression must be accepted
			Matcher matcher = regexPattern.matcher("");
			valid = matcher.matches();
		}
		return valid;
	}

}
