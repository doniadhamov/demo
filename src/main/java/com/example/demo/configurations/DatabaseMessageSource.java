package com.example.demo.configurations;

import com.example.demo.domains.Translation;
import com.example.demo.services.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

@Component
public class DatabaseMessageSource extends AbstractMessageSource {

    @Autowired
    private TranslationService translationService;

    @Override
    protected MessageFormat resolveCode(String s, Locale locale) {
        Translation translation = translationService.findByName(s);
        if (translation == null) return null;
        switch (locale.getLanguage()) {
            case "ru":
                return new MessageFormat(translation.getRussian());
            case "uz":
                return new MessageFormat(translation.getUzbek());
            case "en":
                return new MessageFormat(translation.getEnglish());
        }
        return null;
    }
}
