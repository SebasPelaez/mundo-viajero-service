package com.co.mundoviajero.business.Department;

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
import com.co.mundoviajero.dto.department.DepartmentResponseDTO;
import com.co.mundoviajero.persistence.dao.IDepartmentDAO;
import com.co.mundoviajero.persistence.entity.Department;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;
import com.co.mundoviajero.util.exception.ValidationException;
import com.co.mundoviajero.util.exception.dto.ErrorDTO;

@Service
public class DepartmentBusiness {
	
	@Autowired
	private IDepartmentDAO departmentDAO;

	@Autowired
	private MessageSourceAccessor messageSource;
	
	public ResponseEntity<ResponseDTO> getAllDepartments() throws Exception {
		List<Department> deparments = departmentDAO.getAllDeparments();
		if(CollectionUtils.isNotEmpty(deparments)){

			List<DepartmentResponseDTO> departmentResponse = new ArrayList<>();
			deparments.forEach(
					deparment -> departmentResponse
							.add(SetEntitiesIntoDTO.setDepartmentResponseDTO(deparment))
			);
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"),
					departmentResponse),HttpStatus.OK);
		}
		throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("GET_DESC_ERROR_DEPARMENT")));
	}

	public ResponseEntity<ResponseDTO> getDepartment(Long id) throws ValidationException {

		StringBuilder sb = new StringBuilder(Validator.validateLong(
				id, FieldConstants.DEPARTMENT_ID, FieldConstants.ID_OBLIGATORY));

		if (sb.toString().length() > 0) {
			throw new ValidationException(new ErrorDTO(messageSource.getMessage("MISS_QUERY_PARAMS"),
					sb.toString()));
		}

		Department department = departmentDAO.getDepartment(id);

		if(department != null) {

			DepartmentResponseDTO departmentResponseDTO = SetEntitiesIntoDTO.setDepartmentResponseDTO(department);
			
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"),
					departmentResponseDTO),HttpStatus.OK);
		}
		throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("GET_DESC_ERROR_DEPARMENT")));
	}

}
