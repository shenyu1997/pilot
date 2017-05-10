package shenyu.pilot.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static shenyu.pilot.api.TenantController.URL_TENANT;

import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;
import shenyu.pilot.WebIntegrationTest;

/**
 * Created by Administrator on 2017/5/8.
 */
public class TenantControllerTest extends WebIntegrationTest {


    @Test
    @Sql("get_all_tenants_test.sql")
    public void allTest() throws Exception {
        mockMvc.perform(get(URL_TENANT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("tenant a"))
                .andExpect(jsonPath("$[0].id").value("123456"))
                .andExpect(jsonPath("$[0].state").value("active"));
    }
}
