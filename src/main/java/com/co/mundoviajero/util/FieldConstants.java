package com.co.mundoviajero.util;

public class FieldConstants {

	public static final String PERSON_IDENTIFICATION = "identification";
	public static final int PERSON_IDENTIFICATION_LENGTH = 20;
	public static final boolean PERSON_IDENTIFICATION_OBLIGATORY = false;

	public static final String PERSON_RNT = "rnt";
	public static final int PERSON_RNT_LENGTH = 25;
	public static final boolean PERSON_RNT_OBLIGATORY = false;

	public static final String PERSON_NAME = "name";
	public static final int PERSON_NAME_LENGTH = 100;
	public static final boolean PERSON_NAME_OBLIGATORY = true;

	public static final String PERSON_LASTNAME = "lastName";
	public static final int PERSON_LASTNAME_LENGTH = 100;
	public static final boolean PERSON_LASTNAME_OBLIGATORY = true;

	public static final String PERSON_BIRTHDAY = "birthday";
	public static final int PERSON_BIRTHDAY_LENGTH = 12;
	public static final boolean PERSON_BIRTHDAY_OBLIGATORY = true;

	public static final String PERSON_EMAIL = "email";

	public static final String PERSON_PHONENUMBER = "phoneNumber";
	public static final int PERSON_PHONENUMBER_LENGTH = 13;
	public static final boolean PERSON_PHONENUMBER_OBLIGATORY = true;

	public static final String PERSON_ADDRESS = "address";
	public static final int PERSON_ADDRESS_LENGTH = 150;
	public static final boolean PERSON_ADDRESS_OBLIGATORY = false;

	public static final String PERSON_PASSWORD = "password";
	public static final int PERSON_PASSWORD_LENGTH = 500;
	public static final boolean PERSON_PASSWORD_OBLIGATORY = false;

	public static final String PERSON_CALIFICATION = "calification";
	public static final int PERSON_CALIFICATION_LENGTH = 4;
	public static final boolean PERSON_CALIFICATION_OBLIGATORY = false;

	public static final String PERSON_TOKEN = "token";
	public static final int PERSON_TOKEN_LENGTH = 150;
	public static final boolean PERSON_TOKEN_OBLIGATORY = false;

	public static final String PERSON_PROFILEPHOTO = "profilePhoto";
	public static final int PERSON_PROFILEPHOTO_LENGTH = 500;
	public static final boolean PERSON_PROFILEPHOTO_OBLIGATORY = false;

	public static final String PERSON_PROFILEID = "profileId";

	/**
	 * EVENT FIELDS
	 */

	public static final String EVENT_NAME = "name";
	public static final int EVENT_NAME_LENGTH = 150;
	public static final boolean EVENT_NAME_OBLIGATORY = true;
	
	public static final String EVENT_DESCRIPTION = "description";
	public static final int EVENT_DESCRIPTION_LENGTH = 500;
	public static final boolean EVENT_DESCRIPTION_OBLIGATORY = true;
	
	public static final String EVENT_STARTDATE = "startDate";
	public static final int EVENT_STARTDATE_LENGTH = 24;
	public static final boolean EVENT_STARTDATE_OBLIGATORY = true;
	
	public static final String EVENT_ENDDATE = "endDate";
	public static final int EVENT_ENDDATE_LENGTH = 24;
	public static final boolean EVENT_ENDDATE_OBLIGATORY = true;
	
	public static final String EVENT_LONGITUDEMEETINGPOINT = "longitudeMeetingPoint";	
	public static final String EVENT_LATITUDEMEETINGPOINT = "latitudeMeetingPoint";
	
	public static final String EVENT_CAPACITY = "capacity";
	public static final int EVENT_CAPACITY_LENGTH = 4;
	public static final boolean EVENT_CAPACITY_OBLIGATORY = true;
	
	public static final String EVENT_FARE = "fare";
	public static final int EVENT_FARE_LENGTH = 8;
	public static final boolean EVENT_FARE_OBLIGATORY = true;
	
	public static final String EVENT_PERSONIDRESPONSIBLE = "personIdResponsible";
	
	/**
	 * EVENTPLACE FIELDS
	 */	
	public static final String EVENT_ID = "eventId";
	public static final String CITY_ID = "cityId";
	public static final String EVENTPLACE_ALTITUDE = "AltitudeEventPlace";	
	public static final String EVENTPLACE_LATITUDE = "latitudeEventPlace";
	
	/**
	 * COMMONS
	 */
	
	public static final String STATEID = "stateId";
	public static final int ID_LENGTH = 5;
	public static final boolean ID_OBLIGATORY = true;
	
	public static final int LONGITUDE_LENGTH = 250;
	public static final boolean LONGITUDE_OBLIGATORY = true;
	
	public static final int LATITUDE_LENGTH = 250;
	public static final boolean LATITUDE_OBLIGATORY = true;
	
}
