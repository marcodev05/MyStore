package com.tsk.ecommerce.services.messageries.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.tsk.ecommerce.entities.Orders;

@Component
public class NotificationEmailService {
	
	private static final String MAIL_SHOP = "";
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private MailContentBuilder mailContent;

	@Async
	public void sendNotification(Orders orders) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom(MAIL_SHOP);
			messageHelper.setTo(orders.getCustomer().getEmail());
			messageHelper.setSubject("Confirmation de l'envoie d'une commande");
			
			//String name = orders.getCustomer().getFirstName() + " " + orders.getCustomer().getLastName();
			//String html2 = mailContent.build(notifMail.getClientName(), notifMail.getNumCommand(), null, notifMail.getAddress());
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
