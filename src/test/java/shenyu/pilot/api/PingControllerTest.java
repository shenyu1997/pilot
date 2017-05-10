package shenyu.pilot.api;

import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import shenyu.pilot.WebIntegrationTest;

import static shenyu.pilot.api.PingController.URI_PING;

/**
 * Created by Administrator on 2017/5/7.
 */
public class PingControllerTest extends WebIntegrationTest {

    @Test
    public void pingTest() throws Exception{
        mockMvc.perform(get(URI_PING))
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));
    }
}
