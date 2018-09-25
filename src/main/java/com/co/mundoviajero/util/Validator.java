package com.co.mundoviajero.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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
	
	public static boolean validateBirthday(String data) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		LocalDate localDate = LocalDate.now();		
		
	    Date currentDate = null;
	    Date birthDay = null;
	    
		try {
			currentDate = sdf.parse(data);
			birthDay = sdf.parse(localDate.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}	    
	 
	    long diffInMillies = Math.abs(birthDay.getTime() - currentDate.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return diff >= 6570;
	}
	
	public static boolean validateDate(String startDate, String endDate,String type) {
		
		int comparableHour;
		
		switch (type) {
			case Constants.EVENT_CREATED_DATE:
				comparableHour = 8;
				break;
			case Constants.EVENT_DURATION:
				comparableHour = 1;
				break;
			default:
				comparableHour = 0;
				break;
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parsedStartDate = null;
		Date parsedEndDate = null;
		
		try {
			parsedStartDate = format.parse(startDate);
			parsedEndDate = format.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		long diffInMillies = parsedEndDate.getTime() - parsedStartDate.getTime();
	    long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    
	    System.out.println("Diff: " + diff);
	    System.out.println("Validation: " + (diff >= comparableHour));
	    return diff >= comparableHour;
		
	}
	
}
