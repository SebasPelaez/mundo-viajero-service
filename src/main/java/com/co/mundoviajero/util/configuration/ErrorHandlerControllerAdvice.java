package com.co.mundoviajero.util.configuration;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.util.exception.ExpiredTokenException;
import com.co.mundoviajero.util.exception.InvalidTokenException;
import com.co.mundoviajero.util.exception.ValidationException;

@ControllerAdvice
public class ErrorHandlerControllerAdvice {

   @Autowired
   private MessageSourceAccessor messageSource;

   @ExceptionHandler(InvalidTokenException.class)
   public ResponseEntity<ResponseDTO> handleInvalidToken(HttpServletRequest req, Exception ex) {
       return new ResponseEntity<>(
           new ResponseDTO(messageSource.getMessage("CODE_INVALID_TOKEN"),
               messageSource.getMessage("DESC_INVALID_TOKEN"), messageSource.getMessage("DESC_INVALID_TOKEN")),
           HttpStatus.UNAUTHORIZED);
   }

   @ExceptionHandler(ExpiredTokenException.class)
   public ResponseEntity<ResponseDTO> handleExpiredToken(HttpServletRequest req, Exception ex) {
       return new ResponseEntity<>(
           new ResponseDTO(messageSource.getMessage("CODE_EXPIRED_TOKEN"),
               messageSource.getMessage("DESC_EXPIRED_TOKEN"), messageSource.getMessage("DESC_EXPIRED_TOKEN")),
           HttpStatus.UNAUTHORIZED);
   }

   @ExceptionHandler(ValidationException.class)
   public ResponseEntity<ResponseDTO> handleValidation(HttpServletRequest req, Exception ex) {
       return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR_VALIDATION"),
           messageSource.getMessage("DESC_ERR_VALIDATION"), ex.getMessage()), HttpStatus.BAD_REQUEST);
   }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidation(HttpServletRequest req,
                                                        MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR_VALIDATION"),
                messageSource.getMessage("DESC_ERR_VALIDATION"), generateErrorDesc(ex.getBindingResult().getFieldErrors())),
                HttpStatus.BAD_REQUEST);
    }

    private String generateErrorDesc(List<FieldError> fields) {
        return fields.stream()
                .map(fe -> fe.getField() + " - " + fe.getDefaultMessage())
                .collect(Collectors.joining(", "));
    }
}