package com.tsk.ecommerce.services.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class I18nService {

    private final MessageSource messageSource;

    public I18nService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String get(String key){
        return messageSource.getMessage(key, null, "", LocaleContextHolder.getLocale());
    }
}
