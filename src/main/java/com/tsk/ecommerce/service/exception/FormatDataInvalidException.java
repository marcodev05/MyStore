package com.tsk.ecommerce.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FormatDataInvalidException extends RuntimeException {

	public FormatDataInvalidException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
	
}
