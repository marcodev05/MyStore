package com.tsk.ecommerce.services.messageries.email;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.tsk.ecommerce.entities.Order;

@Component
public class MailContentBuilder {
	private final TemplateEngine templateEngine;

	public MailContentBuilder(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	public String build(Order order) {
		Context context = new Context();
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("order", order);

		final String pattern = "dd-MM-yyyy HH:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String stringDate = simpleDateFormat.format(new Date());
		props.put("date", stringDate);
		context.setVariables(props);
		return templateEngine.process("MailTemplate", context);
	}

}
