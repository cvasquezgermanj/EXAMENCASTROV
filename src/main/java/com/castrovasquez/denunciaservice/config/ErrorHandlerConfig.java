package com.castrovasquez.denunciaservice.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.castrovasquez.denunciaservice.exceptions.GeneralServiceExceptions;
import com.castrovasquez.denunciaservice.exceptions.NoDataFoundException;
import com.castrovasquez.denunciaservice.exceptions.ValidateServiceExceptions;
import com.castrovasquez.denunciaservice.utils.WrapperReponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice

public class ErrorHandlerConfig extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> all(Exception e,WebRequest request){
		log.error(e.getMessage(),e);
		WrapperReponse<?> response = new WrapperReponse<>(false,"Internal Server Error",null);
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(ValidateServiceExceptions.class)
	public ResponseEntity<?> validateService(ValidateServiceExceptions e,WebRequest request){
		log.info(e.getMessage(),e);
		WrapperReponse<?> response = new WrapperReponse<>(false,e.getMessage(),null);
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<?> noData(NoDataFoundException e,WebRequest request){
		log.info(e.getMessage(),e);
		WrapperReponse<?> response = new WrapperReponse<>(false,e.getMessage(),null);
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(GeneralServiceExceptions.class)
	public ResponseEntity<?> generalService(GeneralServiceExceptions e,WebRequest request){
		log.error(e.getMessage(),e);
		WrapperReponse<?> response = new WrapperReponse<>(false,"Internal Server Error",null);
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
