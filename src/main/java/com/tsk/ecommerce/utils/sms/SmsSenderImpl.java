package com.tsk.ecommerce.utils.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsk.ecommerce.configs.twilio.TwilioConfiguration;
import com.tsk.ecommerce.dto.SmsRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

@Service
public class SmsSenderImpl implements SmsSender {
	
	private final Logger LOGGER = LoggerFactory.getLogger(SmsSenderImpl.class);

	
	@Autowired
	private TwilioConfiguration config;

	@Override
	public void sendSms(SmsRequest smsRequest) {
		
		if(isPhoneNumberValid(smsRequest.getPhoneNumber())) {
			
			PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
			PhoneNumber from = new PhoneNumber(config.getTrialNumber());
			String message = smsRequest.getMessage();
			MessageCreator creator = Message.creator(to, from, message);
			creator.create();

			
		}else {
			throw new IllegalArgumentException(" Votre numero de téléphone est invalid");
		}
		
		
	}

	private boolean isPhoneNumberValid(String phoneNumber) {
		// TODO Auto-generated method stub
		return true;
	}

}
