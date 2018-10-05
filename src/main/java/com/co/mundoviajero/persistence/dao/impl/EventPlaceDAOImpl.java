package com.co.mundoviajero.persistence.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.dto.EventPlaceDTO;
import com.co.mundoviajero.persistence.dao.IEventPlaceDAO;
import com.co.mundoviajero.persistence.entity.EventPlace;
import com.co.mundoviajero.util.BoundingBoxDistance.BoundingBox;

@Repository(value = "EventPlaceDAOImpl")
@Transactional
public class EventPlaceDAOImpl extends BaseDAO implements IEventPlaceDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<EventPlaceDTO> getAllEventPlaces(Long eventId) {

		String queryString = "select ep from EventPlace ep where ep.eventId = :eventId";
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("eventId", eventId);

		List<EventPlace> places = (List<EventPlace>) query.getResultList();
		List<EventPlaceDTO> placesDTO = new ArrayList<>();

		if (places.isEmpty())
			return null;

		for (EventPlace ep : places) {
			placesDTO.add(setEventPlaceDTO(ep));
		}
		return placesDTO;
	}

	@Override
	public boolean createEventPlaces(List<EventPlaceDTO> eventPlacesDTO, Long eventId) {

		List<EventPlace> eventPlaces = setEventPlace(eventPlacesDTO, eventId);

		try {
			for (EventPlace ep : eventPlaces) {
				getCurrentSession().saveOrUpdate(ep);
			}

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updateEventPlace(Map<String, String> parameters, Long identifier) {
		StringBuffer parametersQueryString = new StringBuffer();
		String baseQueryString = "update EventPlace ep set ";
		String conditionQueryString = " where ep.id = :id";

		try {

			for (String parameter : parameters.keySet()) {
				parametersQueryString.append("ep." + parameter + " = '" + parameters.get(parameter) + "', ");
			}

			parametersQueryString.replace(parametersQueryString.length() - 2, parametersQueryString.length(), "");
			String fullQueryString = baseQueryString + parametersQueryString.toString() + conditionQueryString;

			Query query = getCurrentSession().createQuery(fullQueryString);
			query.setParameter("id", identifier);
			query.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	@Override
	public EventPlaceDTO getEventPlace(Long id) {
		String queryString = "select ep from EventPlace ep where ep.id = :id";
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("id", id);

		EventPlace eventPlace = (EventPlace) query.getSingleResult();

		if (eventPlace == null)
			return null;

		return setEventPlaceDTO(eventPlace);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> findNearestEvents(BoundingBox boundingBox) {

		double latitudeMin = boundingBox.getMinPoint().getLatitude();
		double latitudeMax = boundingBox.getMaxPoint().getLatitude();
		double longitudeMin = boundingBox.getMinPoint().getLongitude();
		double longitudeMax = boundingBox.getMaxPoint().getLongitude();

		String queryString = "select ep.eventId from EventPlace ep where "
				+ "(ep.latitudeEventPlace BETWEEN :latitudeMin AND :latitudeMax) AND "
				+ "(ep.longitudeEventPlace BETWEEN :longitudeMin AND :longitudeMax)";
		
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("latitudeMin", latitudeMin);
		query.setParameter("latitudeMax", latitudeMax);
		query.setParameter("longitudeMin", longitudeMin);
		query.setParameter("longitudeMax", longitudeMax);
		
		List<Long> eventsId = (List<Long>) query.getResultList();
		
		if (eventsId.isEmpty())
			return null;

		return eventsId;
	}

	private EventPlaceDTO setEventPlaceDTO(EventPlace eventPlace) {
		EventPlaceDTO eventPlaceDTO = new EventPlaceDTO();

		try {
			eventPlaceDTO.setId(eventPlace.getId());
			eventPlaceDTO.setEventId(eventPlace.getEventId());
			eventPlaceDTO.setCity(eventPlace.getCityId());
			eventPlaceDTO.setEventPlaceStartDate(eventPlace.getEventPlaceStartDate().toString().trim());
			eventPlaceDTO.setEventPlaceEndDate(eventPlace.getEventPlaceEndDate().toString().trim());
			eventPlaceDTO.setLongitudeEventPlace(String.valueOf(eventPlace.getLongitudeEventPlace()));
			eventPlaceDTO.setLatitudeEventPlace(String.valueOf(eventPlace.getLatitudeEventPlace()));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return eventPlaceDTO;
	}

	private List<EventPlace> setEventPlace(List<EventPlaceDTO> eventPlacesDTO, Long eventId) {

		List<EventPlace> eventPlaces = new ArrayList<>();

		try {
			for (EventPlaceDTO evtDTO : eventPlacesDTO) {

				EventPlace eventPlace = new EventPlace();
				eventPlace.setEventId(eventId);
				evtDTO.setEventId(eventId);

				eventPlace.setCityId(evtDTO.getCity());
				eventPlace.setLongitudeEventPlace(Double.parseDouble(evtDTO.getLongitudeEventPlace()));
				eventPlace.setLatitudeEventPlace(Double.parseDouble(evtDTO.getLatitudeEventPlace()));

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startDate = format.parse(evtDTO.getEventPlaceStartDate());
				Date endDate = format.parse(evtDTO.getEventPlaceEndDate());

				java.sql.Timestamp startDateSql = new java.sql.Timestamp(startDate.getTime());
				java.sql.Timestamp endDateSql = new java.sql.Timestamp(endDate.getTime());

				eventPlace.setEventPlaceStartDate(startDateSql);
				eventPlace.setEventPlaceEndDate(endDateSql);

				eventPlaces.add(eventPlace);
			}

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

		return eventPlaces;
	}

}
