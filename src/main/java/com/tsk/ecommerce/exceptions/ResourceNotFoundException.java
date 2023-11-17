package com.tsk.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private String entityName;
	private String entityId;
	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String entityName, String entityId){
		this.entityName = entityName;
		this.entityId = entityId;
	}

	public String getEntityName() {
		return entityName;
	}

	public String getEntityId() {
		return entityId;
	}
}
