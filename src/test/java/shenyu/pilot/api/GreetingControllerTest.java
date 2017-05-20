package shenyu.pilot.api;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import shenyu.pilot.common.MockUser;
import shenyu.pilot.web.WebIntegrationTest;

import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static shenyu.pilot.api.GreetingController.MSG_GREETING;
import static shenyu.pilot.api.GreetingController.URL_HELLO;

/**
 * Created by sheyu on 2017/5/20.
 */
public class GreetingControllerTest extends WebIntegrationTest {

    @Autowired
    private MessageSource messageSource;

    @Test
    public void helloTest() throws Exception {
        mockMvc.perform(MockUser.admin(get(URL_HELLO)))
                .andExpect(status().isOk())
                .andExpect(content().string(greeting(Locale.ENGLISH)));
    }

    @Test
    public void helloTestFr() throws Exception {
        mockMvc.perform(MockUser.admin(get(URL_HELLO)).locale(Locale.FRANCE))
                .andExpect(status().isOk())
                .andExpect(content().string(greeting(Locale.FRANCE)));
    }

    public String greeting(Locale locale) {
        return messageSource.getMessage(MSG_GREETING,new Object[]{"admin"}, locale);
    }
}
