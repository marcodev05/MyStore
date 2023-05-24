package com.tsk.ecommerce.controllers;

import javax.validation.Valid;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsk.ecommerce.dtos.requests.SmsRequest;
import com.tsk.ecommerce.services.messageries.sms.SmsSender;

@RestController
@RequestMapping("api/v1")
public class SmsController {

	private final SmsSender smsSender;

	public SmsController(SmsSender smsSender) {
		this.smsSender = smsSender;
	}

	@PostMapping("/sms")
	public void sendSms(@Valid @RequestBody SmsRequest smsRequest) {
		smsSender.sendSms(smsRequest);
	}
	
}