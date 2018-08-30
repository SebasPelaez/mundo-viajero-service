package com.co.mundoviajero.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class Validator {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
	        Pattern.CASE_INSENSITIVE);

	    public static String valideString(String cadena, String nombreCampo, int longitud, boolean obligatorio) {
	        if (obligatorio) {
	            if (StringUtils.isBlank(cadena) || StringUtils.length(cadena) > longitud) {
	                return String.format("%s-%s", nombreCampo, "Formato invalido|");
	            }
	        } else {
	            if (StringUtils.length(cadena) > longitud) {
	                return String.format("%s-%s", nombreCampo, "Formato invalido|");
	            }
	        }

	        return "";
	    }

	    public static String valideEmail(String nombreCampo, String correo) {
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(correo != null ? correo : "");
	        if (!matcher.find()) {
	            return String.format("%s-%s", nombreCampo, "Formato del correo invalido|");
	        }

	        return "";
	    }
}
