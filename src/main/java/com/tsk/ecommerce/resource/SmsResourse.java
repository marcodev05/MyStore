package com.tsk.ecommerce.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsk.ecommerce.model.SmsRequest;
import com.tsk.ecommerce.utils.sms.SmsSender;

@RestController
@RequestMapping("api/v1")
public class SmsResourse {

	@Autowired
	private SmsSender smsSender;
	
	@PostMapping("/sms")
	public void sendSms(@Valid @RequestBody SmsRequest smsRequest) {
		
		smsSender.sendSms(smsRequest);
		
	}
	
}
