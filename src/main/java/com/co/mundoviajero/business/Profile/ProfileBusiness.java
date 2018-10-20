package com.co.mundoviajero.business.Profile;

import java.util.ArrayList;
import java.util.List;

import com.co.mundoviajero.dto.state.StateResponseDTO;
import com.co.mundoviajero.persistence.entity.Profile;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;
import com.co.mundoviajero.util.exception.dto.ErrorDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.dto.profile.ProfileResponseDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.persistence.dao.IProfileDAO;
import com.co.mundoviajero.util.exception.ValidationException;

@Service
public class ProfileBusiness {
	
	@Autowired
	private IProfileDAO profileDAO;
	
	@Autowired
	private MessageSourceAccessor messageSource;
	
	public ResponseEntity<ResponseDTO> getAllProfiles() throws Exception {
		List<Profile> profiles = profileDAO.getAllProfiles();

		if(CollectionUtils.isNotEmpty(profiles)){

			List<ProfileResponseDTO> profilesResponseDTO = new ArrayList<>();
			profiles.forEach(
					profile -> profilesResponseDTO
							.add(new ProfileResponseDTO(profile.getId(),profile.getDescription(),
									new StateResponseDTO(profile.getState().getId(),profile.getState().getDescription(),
											profile.getState().getBelongsTo())
							))
			);

			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"),
					profilesResponseDTO),HttpStatus.OK);

		}
		throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("GET_DESC_ERROR_PROFILE")));
	}
	
	public ResponseEntity<ResponseDTO> getProfile(Long id) throws ValidationException {

		StringBuilder sb = new StringBuilder(Validator.validateLong(
				id, FieldConstants.PROFILE_ID, FieldConstants.ID_OBLIGATORY));

		if (sb.toString().length() > 0) {
			throw new ValidationException(new ErrorDTO(messageSource.getMessage("MISS_QUERY_PARAMS"),
					sb.toString()));
		}

		Profile profile = profileDAO.getProfile(id);
		
		if(profile != null) {

			ProfileResponseDTO profileResponseDTO =
					new ProfileResponseDTO(profile.getId(),profile.getDescription(),
							new StateResponseDTO(profile.getState().getId(),profile.getState().getDescription(),
									profile.getState().getBelongsTo()));

			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"),
					profileResponseDTO),HttpStatus.OK);
		}

		throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("GET_DESC_ERROR_PROFILE")));
	}

}
