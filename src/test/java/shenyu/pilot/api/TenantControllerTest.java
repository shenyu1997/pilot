package shenyu.pilot.api;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static shenyu.pilot.api.TenantController.URL_GET_TENANT;
import static shenyu.pilot.api.TenantController.URL_TENANT;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import shenyu.pilot.WebIntegrationTest;
import shenyu.pilot.common.JsonUtil;
import shenyu.pilot.model.Tenant;

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

    @Test
    @Sql("get_all_tenants_test.sql")
    public void getTenantTest() throws Exception {
        mockMvc.perform(get(URL_GET_TENANT, 123456))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("tenant a"))
                .andExpect(jsonPath("id").value("123456"))
                .andExpect(jsonPath("state").value("active"));
    }

    @Test
    public void createTenantTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post(URL_TENANT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.asJsonString(new Tenant("test xxx", "active"))))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString(SERVER_PREFIX + URL_TENANT)))
                .andReturn();
        String location = mvcResult.getResponse().getHeader("location");
        mockMvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("test xxx"))
                .andExpect(jsonPath("state").value("active"));
    }
}
