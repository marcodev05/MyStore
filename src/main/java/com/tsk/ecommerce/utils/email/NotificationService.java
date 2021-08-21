package com.tsk.ecommerce.utils.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tsk.ecommerce.entities.Customer;

@Service
public class NotificationService {
	
	private static final String MAIL_SHOP = "maminiainamarco@gmail.com";
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendNotification(Customer customer) {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(customer.getEmail());
		mail.setFrom(MAIL_SHOP);
		mail.setSubject("Confirmation de commande chez Nearby SHOP");
		mail.setText("Vous avez fait un achat en ligne chez 'Nearby SHOP' tels que: Telephone sumsung de 10 000Ar."
				+ "Votre produit sera livré dans moins de 48H."
				+ "Merci de votre confiance."
				+ ":");
	
		javaMailSender.send(mail);
	}	
		

}
