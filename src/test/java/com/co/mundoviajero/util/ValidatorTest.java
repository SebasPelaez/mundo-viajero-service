package com.co.mundoviajero.util;

import static org.junit.Assert.*;
import org.junit.Test;

public class ValidatorTest {

	@Test
	public void testValideStringObligatoryVoid() {
		String expectedMessage = "Test Field-Formato invalido|";
		String actualMessage = Validator.valideString("", "Test Field", 5, true);
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testValideStringObligatoryMoreData() {
		String expectedMessage = "Test Field-Formato invalido|";
		String actualMessage = Validator.valideString("Test Data", "Test Field", 5, true);
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testValideStringObligatoryCorrectData() {
		String expectedMessage = "";
		String actualMessage = Validator.valideString("Test", "Test Field", 5, true);
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testValideStringOptionalCorrectData() {
		String expectedMessage = "";
		String actualMessage = Validator.valideString("Test", "Test Field", 5, false);
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testValideStringOptionalMoreData() {
		String expectedMessage = "Test Field-Formato invalido|";
		String actualMessage = Validator.valideString("Test Data", "Test Field", 5, false);
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testValideCorrectEmail() {
		String mail = "test@mail.com";
		String fieldName = "Test Mail";
		String expectedMessage = "";
		String actualMessage = Validator.valideEmail(fieldName, mail);
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testValideNullEmail() {
		String mail = null;
		String fieldName = "Test Mail";
		String expectedMessage = "Test Mail-Formato del correo invalido|";
		String actualMessage = Validator.valideEmail(fieldName, mail);
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testValideMailWithOutArroba() {
		String mail = "testmail.com";
		String fieldName = "Test Mail";
		String expectedMessage = "Test Mail-Formato del correo invalido|";
		String actualMessage = Validator.valideEmail(fieldName, mail);
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testValideMailWithOutCompany() {
		String mail = "test@.com";
		String fieldName = "Test Mail";
		String expectedMessage = "Test Mail-Formato del correo invalido|";
		String actualMessage = Validator.valideEmail(fieldName, mail);
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testValideMailWithOutDomain() {
		String mail = "test@mail";
		String fieldName = "Test Mail";
		String expectedMessage = "Test Mail-Formato del correo invalido|";
		String actualMessage = Validator.valideEmail(fieldName, mail);
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testValideMailWithOutName() {
		String mail = "@mail.com";
		String fieldName = "Test Mail";
		String expectedMessage = "Test Mail-Formato del correo invalido|";
		String actualMessage = Validator.valideEmail(fieldName, mail);
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testValidateOblygatoryNumber() {
		String data = "1017";
		String fieldName = "Test Field";
		int fieldLength = 5;
		boolean mandatory = true;
		
		String expectedMessage = "";
		String actualMessage = Validator.validateNumber(data, fieldName, fieldLength, mandatory);
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testValidateNumberObligatoryEmptyData() {
		String data = "";
		String fieldName = "Test Field";
		int fieldLength = 5;
		boolean mandatory = true;
		
		String expectedMessage = "Test Field-Is Not Number|";
		String actualMessage = Validator.validateNumber(data, fieldName, fieldLength, mandatory);
		
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testValidateNumberObligatoryMuchData() {
		String data = "1017225764";
		String fieldName = "Test Field";
		int fieldLength = 5;
		boolean mandatory = true;
		
		String expectedMessage = "Test Field-Formato invalido|";
		String actualMessage = Validator.validateNumber(data, fieldName, fieldLength, mandatory);
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testValidateOptionalNumber() {
		String data = "1017";
		String fieldName = "Test Field";
		int fieldLength = 5;
		boolean mandatory = false;
		
		String expectedMessage = "";
		String actualMessage = Validator.validateNumber(data, fieldName, fieldLength, mandatory);
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testValidateNumberOptionalEmptyData() {
		String data = "";
		String fieldName = "Test Field";
		int fieldLength = 5;
		boolean mandatory = false;
		
		String expectedMessage = "Test Field-Is Not Number|";
		String actualMessage = Validator.validateNumber(data, fieldName, fieldLength, mandatory);
		
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testValidateNumberOptionalMuchData() {
		String data = "1017225764";
		String fieldName = "Test Field";
		int fieldLength = 5;
		boolean mandatory = false;
		
		String expectedMessage = "Test Field-Formato invalido|";
		String actualMessage = Validator.validateNumber(data, fieldName, fieldLength, mandatory);
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testValidateIsNotNumber() {
		String data = "No Number";
		String fieldName = "Test Field";
		int fieldLength = 5;
		boolean mandatory = false;
		
		String expectedMessage = "Test Field-Is Not Number|";
		String actualMessage = Validator.validateNumber(data, fieldName, fieldLength, mandatory);
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testValidateBirthdaySmallerThan18() {
		boolean expectedBirthday = false;
		String data = "2001-12-06";
		
		boolean actualBirthday = Validator.validateBirthday(data);
		
		assertEquals(expectedBirthday, actualBirthday);
	}
	
	@Test
	public void testValidateIncorrectBirthdayFormatString() {
		boolean expectedBirthday = false;
		String data = "TestBirthday";
		
		boolean actualBirthday = Validator.validateBirthday(data);
		
		assertEquals(expectedBirthday, actualBirthday);
	}
	
	@Test
	public void testValidateIncorrectBirthdayFormatDate() {
		boolean expectedBirthday = false;
		String data = "06/12/1994";
		
		boolean actualBirthday = Validator.validateBirthday(data);
		
		assertEquals(expectedBirthday, actualBirthday);
	}
	
	@Test
	public void testValidateIncorrectBirthdaySqlFormatDate() {
		boolean expectedBirthday = true;
		String data = "06-12-1994";
		
		boolean actualBirthday = Validator.validateBirthday(data);
		assertEquals(expectedBirthday, actualBirthday);
	}

	@Test
	public void testValidateEventCreatedDateLessHours() {
		
		boolean expectedValue = false;
		String startDate = "06-12-2018 13:00:00";
		String endDate = "06-12-2018 13:00:00";
		String type = Constants.EVENT_CREATED_DATE;
		
		boolean actualValue = Validator.validateDate(startDate, endDate, type);
		
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testValidateEventCreatedDateGreaterHours() {
		
		boolean expectedValue = true;
		String startDate = "06-12-2018 13:00:00";
		String endDate = "06-12-2018 22:00:00";
		String type = Constants.EVENT_CREATED_DATE;
		
		boolean actualValue = Validator.validateDate(startDate, endDate, type);
		
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testValidateEventDurationDateLessHours() {
		
		boolean expectedValue = false;
		String startDate = "06-12-2018 13:00:00";
		String endDate = "06-12-2018 13:00:00";
		String type = Constants.EVENT_DURATION;
		
		boolean actualValue = Validator.validateDate(startDate, endDate, type);
		
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testValidateEventDurationDateGreaterHours() {
		
		boolean expectedValue = true;
		String startDate = "06-12-2018 13:00:00";
		String endDate = "06-12-2018 22:00:00";
		String type = Constants.EVENT_DURATION;
		
		boolean actualValue = Validator.validateDate(startDate, endDate, type);
		
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testValidateEventVoidDateLessHours() {
		
		boolean expectedValue = true;
		String startDate = "06-12-2018 13:00:00";
		String endDate = "06-12-2018 13:00:00";
		String type = "";
		
		boolean actualValue = Validator.validateDate(startDate, endDate, type);
		
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testValidateEventVoidDateGreaterHours() {
		
		boolean expectedValue = true;
		String startDate = "06-12-2018 13:00:00";
		String endDate = "06-12-2018 22:00:00";
		String type = "";
		
		boolean actualValue = Validator.validateDate(startDate, endDate, type);
		
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testValidateEventDateFormat() {
		
		boolean expectedValue = false;
		String startDate = "TestDate";
		String endDate = "TestDate";
		String type = Constants.EVENT_DURATION;
		
		boolean actualValue = Validator.validateDate(startDate, endDate, type);
		
		assertEquals(expectedValue, actualValue);
	}

}
