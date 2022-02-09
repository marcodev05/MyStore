package com.tsk.ecommerce.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SmsRequest {
	
	@NotBlank
	private String phoneNumber;
	@NotBlank
	private String message;
	
	public SmsRequest(@JsonProperty("phoneNumber")String phoneNumber,
					  @JsonProperty("message")String message) {
		super();
		this.phoneNumber = phoneNumber;
		this.message = message;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
