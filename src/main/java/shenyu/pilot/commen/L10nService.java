package shenyu.pilot.commen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by sheyu on 2017/5/20.
 */
@Service
public class L10nService {

    @Autowired
    private MessageSource messageSource;

    public String l10n(String key, String... args) {
        return messageSource.getMessage(key, args, key, LocaleContextHolder.getLocale());
    }
}
