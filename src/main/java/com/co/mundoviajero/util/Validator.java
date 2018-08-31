package com.co.mundoviajero.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public class Validator {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static String valideString(String data, String fieldName, int fieldLength, boolean mandatory) {
		if (mandatory) {
			if (StringUtils.isBlank(data) || StringUtils.length(data) > fieldLength) {
				return String.format("%s-%s", fieldName, "Formato invalido|");
			}
		} else {
			if (StringUtils.length(data) > fieldLength) {
				return String.format("%s-%s", fieldName, "Formato invalido|");
			}
		}

		return "";
	}

	public static String valideEmail(String fieldName, String mail) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail != null ? mail : "");
		if (!matcher.find()) {
			return String.format("%s-%s", fieldName, "Formato del correo invalido|");
		}

		return "";
	}

	public static String validateNumber(String data, String fieldName, int fieldLength, boolean mandatory) {
		if (!NumberUtils.isNumber(data)) {
			if (mandatory) {
				if (StringUtils.isBlank(data) || StringUtils.length(data) > fieldLength) {
					return String.format("%s-%s", fieldName, "Formato invalido|");
				}
			} else {
				if (StringUtils.length(data) > fieldLength) {
					return String.format("%s-%s", fieldName, "Formato invalido|");
				}
			}
		}
		return "";
	}
}
