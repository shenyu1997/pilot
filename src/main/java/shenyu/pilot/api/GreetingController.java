package shenyu.pilot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shenyu.pilot.commen.L10nService;
import shenyu.pilot.model.UserContext;

import java.util.Locale;

/**
 * Created by sheyu on 2017/5/20.
 */
@RestController
public class GreetingController {
    public static final String MSG_GREETING = "msg.greeting";
    public static final String URL_HELLO = "/hello";

    @Autowired
    private UserContext userContext;

    @Autowired
    private L10nService l10nService;


    @GetMapping(URL_HELLO)
    public String hello() {
        return l10nService.l10n(MSG_GREETING, userContext.getPrincipal().getName());
    }

}
