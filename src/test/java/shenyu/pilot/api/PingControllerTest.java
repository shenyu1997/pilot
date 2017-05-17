package shenyu.pilot.api;

import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import shenyu.pilot.common.MockUser;
import shenyu.pilot.web.WebIntegrationTest;

import java.util.Base64;

import static shenyu.pilot.api.PingController.URI_PING;

/**
 * Created by Administrator on 2017/5/7.
 */
public class PingControllerTest extends WebIntegrationTest {

    @Test
    public void pingTest() throws Exception{
        mockMvc.perform(MockUser.admin(get(URI_PING)))
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));
    }
}
