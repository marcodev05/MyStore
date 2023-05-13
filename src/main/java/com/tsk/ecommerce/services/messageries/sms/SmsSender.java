package com.tsk.ecommerce.services.messageries.sms;

import com.tsk.ecommerce.dtos.requests.SmsRequest;

public interface SmsSender {
	 void sendSms(SmsRequest smsRequest);
}
