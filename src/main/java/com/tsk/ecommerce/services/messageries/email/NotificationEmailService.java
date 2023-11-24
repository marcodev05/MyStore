package com.tsk.ecommerce.services.messageries.email;


import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;

import com.tsk.ecommerce.entities.Orders;
import org.springframework.stereotype.Component;

@Component
public class NotificationEmailService {
	
	private static final String MAIL_SHOP = "";
	private final JavaMailSender javaMailSender;
	private final MailContentBuilder mailContent;

	public NotificationEmailService(JavaMailSender javaMailSender,
									MailContentBuilder mailContent) {
		this.javaMailSender = javaMailSender;
		this.mailContent = mailContent;
	}

	@Async
	public void sendNotification(Orders orders) {
		//MimeMessagePreparator messagePreparator2 = emailFactory;
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom(MAIL_SHOP);
			messageHelper.setSubject("Confirmation de l'envoie d'une commande");
			String html = mailContent.build(orders);
			messageHelper.setText(html, true);
		};
		javaMailSender.send(messagePreparator);

		
//		SimpleMailMessage mail = new SimpleMailMessage();
//		mail.setTo(customer.getEmail());
//		mail.setFrom(MAIL_SHOP);
//		mail.setSubject("Confirmation de commande chez Nearby SHOP");
//		mail.setText("Vous avez fait un achat en ligne chez 'Nearby SHOP' tels que: Telephone sumsung de 10 000Ar."
//				+ "Votre produit sera livré dans moins de 48H."
//				+ "Merci de votre confiance."
//				+ ":");
//	
//		javaMailSender.send(mail);
		
		
	}


}
