package com.tsk.ecommerce.utils.sms;

import com.tsk.ecommerce.payload.SmsRequest;

public interface SmsSender {
	
	public void sendSms(SmsRequest smsRequest);
 
}
