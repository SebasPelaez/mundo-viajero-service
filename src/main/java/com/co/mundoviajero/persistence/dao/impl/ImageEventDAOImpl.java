package com.co.mundoviajero.persistence.dao.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.dto.event.imageevent.ImageEventDTO;
import com.co.mundoviajero.persistence.dao.IImageEventDAO;
import com.co.mundoviajero.persistence.entity.ImageEvent;

@Repository(value = "ImageEventDAOImpl")
@Transactional
public class ImageEventDAOImpl extends BaseDAO implements IImageEventDAO{

	@Override
	public ImageEventDTO getImageEvent(Long id) {
		String queryString = "select ie from ImageEvent ie where ie.id = :id";
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("id", id);

		ImageEvent imageEvent = (ImageEvent) query.getSingleResult();

		if (imageEvent == null)
			return null;

		return setImageEventDTO(imageEvent);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImageEventDTO> getAllImageEvent(Long idEvent) {
		String queryString = "select ie from ImageEvent ie where ie.eventId = :idEvent";
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("idEvent", idEvent);

		List<ImageEvent> images = (List<ImageEvent>) query.getResultList();
		List<ImageEventDTO> imagesDTO = new ArrayList<>();

		if (images.isEmpty())
			return null;

		for (ImageEvent ie : images) {
			imagesDTO.add(setImageEventDTO(ie));
		}
		return imagesDTO;
	}

	@Override
	public boolean createImageEvent(List<String> images, Long eventId) {
		List<ImageEvent> imagesEvent = setImageEvent(images, eventId);

		try {
			for (ImageEvent imageEvent : imagesEvent) {
				getCurrentSession().saveOrUpdate(imageEvent);
			}

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	@Override
	public boolean updateImageEvent(Map<String, String> parameters, Long identifier) {
		StringBuffer parametersQueryString = new StringBuffer();
		String baseQueryString = "update ImageEvent ie set ";
		String conditionQueryString = " where ie.id = :id";

		try {

			for (String parameter : parameters.keySet()) {
				parametersQueryString.append("ie." + parameter + " = '" + parameters.get(parameter) + "', ");
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
	
	private ImageEventDTO setImageEventDTO(ImageEvent imageEvent) {
		
		ImageEventDTO imageEventDTO = new ImageEventDTO();
		try {
			imageEventDTO.setId(imageEvent.getId());
			imageEventDTO.setEventId(imageEvent.getEventId());
			imageEventDTO.setImagePath(imageEvent.getImagePath().trim());
			imageEventDTO.setUploadDate(imageEvent.getUploadDate().toString().trim());
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return imageEventDTO;
	}

	private List<ImageEvent> setImageEvent(List<String> images, Long eventId) {

		List<ImageEvent> imageEvents  = new ArrayList<>();
		try {
			for (String image : images) {

				ImageEvent imageEvent = new ImageEvent();

				imageEvent.setEventId(eventId);
				imageEvent.setImagePath(image);

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startDate = format.parse(LocalDateTime.now().toString().replace("T", " "));
				java.sql.Timestamp uploadDateSql = new java.sql.Timestamp(startDate.getTime());
				imageEvent.setUploadDate(uploadDateSql);

				imageEvents.add(imageEvent);
			}

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

		return imageEvents;
	}
}
