package com.co.mundoviajero.persistence.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.persistence.dao.IImageEventDAO;
import com.co.mundoviajero.persistence.entity.ImageEvent;
import com.co.mundoviajero.util.FieldConstants;

@Repository(value = "ImageEventDAOImpl")
@Transactional
public class ImageEventDAOImpl extends BaseDAO implements IImageEventDAO {

	@Override
	public ImageEvent getImageEvent(Long id) {
		return getCurrentSession().find(ImageEvent.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImageEvent> getAllImageEvent(Long idEvent) {
		String queryString = "select ie from ImageEvent ie where ie.eventId = :idEvent";
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("idEvent", idEvent);

		List<ImageEvent> images = (List<ImageEvent>) query.getResultList();
		return images;
	}

	@Override
	public boolean createImageEvent(List<ImageEvent> images) {

		try {
			for (ImageEvent imageEvent : images) {
				getCurrentSession().saveOrUpdate(imageEvent);
			}

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updateImageEvent(Map<String, String> parameters) {

		String queryString = "update ImageEvent ie set ie.state = '" + parameters.get(FieldConstants.STATE_ID)
				+ "' where ie.id = :id";

		try {

			Query query = getCurrentSession().createQuery(queryString);
			query.setParameter("id", Long.parseLong(parameters.get(FieldConstants.ID)));
			query.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

}
