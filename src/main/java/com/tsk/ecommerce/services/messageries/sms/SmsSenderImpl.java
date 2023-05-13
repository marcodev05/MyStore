package com.tsk.ecommerce.services.messageries.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tsk.ecommerce.configs.twilio.TwilioConfiguration;
import com.tsk.ecommerce.dtos.requests.SmsRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

@Component
public class SmsSenderImpl implements SmsSender {
	private final Logger LOGGER = LoggerFactory.getLogger(SmsSenderImpl.class);
	private final TwilioConfiguration config;

	public SmsSenderImpl(TwilioConfiguration config) {
		this.config = config;
	}

	@Override
	public void sendSms(SmsRequest smsRequest) {
		if(isPhoneNumberValid(smsRequest.getPhoneNumber())) throw new IllegalArgumentException(" Votre numero de téléphone est invalid");
		PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
		PhoneNumber from = new PhoneNumber(config.getTrialNumber());
		String message = smsRequest.getMessage();
		MessageCreator creator = Message.creator(to, from, message);
		creator.create();
		LOGGER.info("sms to : " + smsRequest.getPhoneNumber() );
	}

	private boolean isPhoneNumberValid(String phoneNumber) {
		// TODO Auto-generated method stub
		return true;
	}

}
