package com.co.mundoviajero.business;

import com.co.mundoviajero.dto.city.CityResponseDTO;
import com.co.mundoviajero.dto.department.DepartmentResponseDTO;
import com.co.mundoviajero.dto.event.EventResponseDTO;
import com.co.mundoviajero.dto.event.eventplace.EventPlaceResponseDTO;
import com.co.mundoviajero.dto.event.imageevent.ImageEventResponseDTO;
import com.co.mundoviajero.dto.person.PersonResponseDTO;
import com.co.mundoviajero.dto.profile.ProfileResponseDTO;
import com.co.mundoviajero.dto.state.StateResponseDTO;
import com.co.mundoviajero.persistence.entity.City;
import com.co.mundoviajero.persistence.entity.Department;
import com.co.mundoviajero.persistence.entity.Event;
import com.co.mundoviajero.persistence.entity.EventPlace;
import com.co.mundoviajero.persistence.entity.ImageEvent;
import com.co.mundoviajero.persistence.entity.Person;
import com.co.mundoviajero.persistence.entity.Profile;
import com.co.mundoviajero.persistence.entity.State;

public class SetEntitiesIntoDTO {

	public static CityResponseDTO setCityResponseDTO(City city) {

		CityResponseDTO cityDTO = new CityResponseDTO(city.getId(), city.getName(),
				setDepartmentResponseDTO(city.getDepartmentId()));

		return cityDTO;
	}

	public static DepartmentResponseDTO setDepartmentResponseDTO(Department department) {

		DepartmentResponseDTO departmentDTO = new DepartmentResponseDTO(department.getId(), department.getName());

		return departmentDTO;
	}

	public static PersonResponseDTO setPersonResponseDTO(Person person) {

		PersonResponseDTO personResponseDTO = new PersonResponseDTO(person.getId(), person.getIdentification(),
				person.getRNT(), person.getName(), person.getLastName(), person.getBirthday().toString(),
				person.getEmail(), person.getPhoneNumber(), person.getAddress(), person.getCalification(),
				person.getProfilePhoto(), person.getToken(), setProfileResponseDTO(person.getProfile()),
				setStateResponseDTO(person.getState()));

		return personResponseDTO;
	}

	public static StateResponseDTO setStateResponseDTO(State state) {

		StateResponseDTO stateResponseDTO = new StateResponseDTO(state.getId(), state.getDescription(),
				state.getBelongsTo());

		return stateResponseDTO;
	}

	public static ProfileResponseDTO setProfileResponseDTO(Profile profile) {

		ProfileResponseDTO profileResponseDTO = new ProfileResponseDTO(profile.getId(), profile.getDescription(),
				setStateResponseDTO(profile.getState()));

		return profileResponseDTO;
	}

	public static EventResponseDTO setEventResponseDTO(Event event) {

		EventResponseDTO eventResponseDTO = new EventResponseDTO(event.getId(), event.getName(), event.getDescription(),
				event.getStartDate().toString(), event.getEndDate().toString(), event.getLongitudeMeetingPoint(),
				event.getLatitudeMeetingPoint(), event.getCapaciticy(), event.getFare(),
				setPersonResponseDTO(event.getPersonIdResponsible()), setStateResponseDTO(event.getState()), null,
				null);

		return eventResponseDTO;
	}

	public static ImageEventResponseDTO setImageEventDTO(ImageEvent imageEvent) {

		ImageEventResponseDTO imageEventDTO = new ImageEventResponseDTO(imageEvent.getId(), imageEvent.getEventId(),
				imageEvent.getUploadDate().toString(), imageEvent.getImagePath(),
				setStateResponseDTO(imageEvent.getState()));

		return imageEventDTO;
	}

	public static EventPlaceResponseDTO setEventPlaceDTO(EventPlace eventPlace) {

		EventPlaceResponseDTO eventPlaceResponseDTO = new EventPlaceResponseDTO(eventPlace.getId(),
				eventPlace.getName(), eventPlace.getEventId(), eventPlace.getEventPlaceStartDate().toString(),
				eventPlace.getEventPlaceEndDate().toString(), eventPlace.getLongitudeEventPlace(),
				eventPlace.getLatitudeEventPlace(), setCityResponseDTO(eventPlace.getCityId()));

		return eventPlaceResponseDTO;
	}

}
