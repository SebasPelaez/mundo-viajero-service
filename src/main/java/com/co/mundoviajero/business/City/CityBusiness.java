package com.co.mundoviajero.business.City;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.business.SetEntitiesIntoDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.city.CityResponseDTO;
import com.co.mundoviajero.persistence.dao.ICityDAO;
import com.co.mundoviajero.persistence.entity.City;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;
import com.co.mundoviajero.util.exception.ValidationException;
import com.co.mundoviajero.util.exception.dto.ErrorDTO;

@Service
public class CityBusiness {
	
	@Autowired
	private ICityDAO cityDAO;

	@Autowired
	private MessageSourceAccessor messageSource;
	
	public ResponseEntity<ResponseDTO> getAllCities() throws Exception {
		List<City> cities = cityDAO.getAllCities();
		if(CollectionUtils.isNotEmpty(cities)){

			List<CityResponseDTO> cityResponse = new ArrayList<>();
			cities.forEach(
					city -> cityResponse
							.add(SetEntitiesIntoDTO.setCityResponseDTO(city))
			);
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"),
					cityResponse),HttpStatus.OK);
		}
		throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("GET_DESC_ERROR_CITY")));
	}
	
	public ResponseEntity<ResponseDTO> getDepartmentCities(Long id) throws Exception {
		List<City> cities = cityDAO.getDepartmentCities(id);
		if(CollectionUtils.isNotEmpty(cities)){

			List<CityResponseDTO> cityResponse = new ArrayList<>();
			cities.forEach(
					city -> cityResponse
							.add(SetEntitiesIntoDTO.setCityResponseDTO(city))
			);
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"),
					cityResponse),HttpStatus.OK);
		}
		throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("GET_DESC_ERROR_CITY")));
	}

	public ResponseEntity<ResponseDTO> getCity(Long id) throws ValidationException {

		StringBuilder sb = new StringBuilder(Validator.validateLong(
				id, FieldConstants.CITY_ID, FieldConstants.ID_OBLIGATORY));

		if (sb.toString().length() > 0) {
			throw new ValidationException(new ErrorDTO(messageSource.getMessage("MISS_QUERY_PARAMS"),
					sb.toString()));
		}

		City city = cityDAO.getCity(id);

		if(city != null) {

			CityResponseDTO cityResponseDTO = SetEntitiesIntoDTO.setCityResponseDTO(city);
			
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"),
					cityResponseDTO),HttpStatus.OK);
		}
		throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("GET_DESC_ERROR_CITY")));
	}

}
