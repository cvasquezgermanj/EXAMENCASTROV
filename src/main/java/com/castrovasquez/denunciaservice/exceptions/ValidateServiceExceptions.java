package com.castrovasquez.denunciaservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class ValidateServiceExceptions extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidateServiceExceptions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValidateServiceExceptions(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ValidateServiceExceptions(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ValidateServiceExceptions(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ValidateServiceExceptions(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}