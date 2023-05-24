package com.tsk.ecommerce.services.messageries.email;

import com.tsk.ecommerce.entities.auth.UserEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class EmailFactory implements MimeMessagePreparator {

    private static final String MAIL_SHOP = "";
    private final UserEntity user;
    private final MailContentBuilder mailContent;

    public EmailFactory(UserEntity user, MailContentBuilder mailContent) {
        this.user = user;
        this.mailContent = mailContent;
    }

    @Override
    public void prepare(MimeMessage mimeMessage) throws Exception {
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        messageHelper.setFrom(MAIL_SHOP);
        messageHelper.setTo(user.getEmail());
        messageHelper.setSubject("Confirmation de l'envoie d'une commande");
        String html = mailContent.build(null);
        messageHelper.setText(html, true);
    }
}
