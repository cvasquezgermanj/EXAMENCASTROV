package com.castrovasquez.denunciaservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralServiceExceptions extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GeneralServiceExceptions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GeneralServiceExceptions(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public GeneralServiceExceptions(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public GeneralServiceExceptions(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public GeneralServiceExceptions(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
