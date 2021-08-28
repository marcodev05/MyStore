package com.tsk.ecommerce.utils.email;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.tsk.ecommerce.entities.Address;
import com.tsk.ecommerce.entities.OrderLine;

@Component
@Service
public class MailContentBuilder {
	
	
	@Autowired
	private TemplateEngine templateEngine;
	
	
	public MailContentBuilder() {
		super();
	}

	public String build(String nameClient, String numCommand, List<OrderLine> orderLines, Address address) {
		
		Context context = new Context();
		
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("nameClient", nameClient);
		props.put("numCommand", numCommand);
		props.put("address", address);
		
		context.setVariables(props);
		
		return templateEngine.process("MailTemplate", context);
	}

}
