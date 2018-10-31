package com.co.mundoviajero.persistence.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.persistence.dao.IEventPlaceDAO;
import com.co.mundoviajero.persistence.entity.EventPlace;
import com.co.mundoviajero.util.BoundingBoxDistance.BoundingBox;

@Repository(value = "EventPlaceDAOImpl")
@Transactional
public class EventPlaceDAOImpl extends BaseDAO implements IEventPlaceDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<EventPlace> getAllEventPlaces(Long eventId) {

		String queryString = "select ep from EventPlace ep where ep.eventId = :eventId";
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("eventId", eventId);

		List<EventPlace> places = (List<EventPlace>) query.getResultList();
		
		return places;
	}

	@Override
	public boolean createEventPlaces(List<EventPlace> eventPlaces) {
		
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
	public EventPlace getEventPlace(Long id) {
		return getCurrentSession().find(EventPlace.class, id);
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
	
	@Override
	public boolean validCity(Long cityId) {
		String queryString = "select c from City c where c.id = :cityId";
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("cityId", cityId);
		return !query.getResultList().isEmpty();
	}

}
