package com.tsk.ecommerce.utils.sms;

import com.tsk.ecommerce.dto.SmsRequest;

public interface SmsSender {
	
	public void sendSms(SmsRequest smsRequest);
 
}
