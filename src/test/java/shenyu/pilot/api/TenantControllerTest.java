package shenyu.pilot.api;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static shenyu.pilot.api.TenantController.URL_TENANT_OPERATIONS;
import static shenyu.pilot.api.TenantController.URL_THE_TENANT;
import static shenyu.pilot.api.TenantController.URL_TENANTS;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.UriTemplate;
import shenyu.pilot.common.MockUser;
import shenyu.pilot.web.WebIntegrationTest;
import shenyu.pilot.agent.SawAgent;
import shenyu.pilot.common.JsonUtil;
import shenyu.pilot.model.Tenant;
import shenyu.pilot.model.TenantOperation;

/**
 * Created by Administrator on 2017/5/8.
 */
public class TenantControllerTest extends WebIntegrationTest {


    @Test
    @Sql("insert_test_tenants.sql")
    public void allTest() throws Exception {
        mockMvc.perform(MockUser.admin(get(URL_TENANTS)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("tenant a"))
                .andExpect(jsonPath("$[0].id").value("123456"))
                .andExpect(jsonPath("$[0].state").value("active"));
    }

    @Test
    @Sql("insert_test_tenants.sql")
    public void getTenantTest() throws Exception {
        mockMvc.perform(MockUser.admin(get(URL_THE_TENANT, 123456)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("tenant a"))
                .andExpect(jsonPath("id").value("123456"))
                .andExpect(jsonPath("state").value("active"));
    }

    @Test
    public void createTenantTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockUser.admin(post(URL_TENANTS))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.asJsonString(new Tenant("test xxx", "active"))))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString(SERVER_PREFIX + URL_TENANTS)))
                .andReturn();
        String location = mvcResult.getResponse().getHeader("location");
        mockMvc.perform(MockUser.admin(get(location)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("test xxx"))
                .andExpect(jsonPath("state").value("active"));

        String auditLocation = location + "/audit/";
        mockMvc.perform(MockUser.admin(get(auditLocation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    @Sql("insert_test_tenants.sql")
    public void updateTenant() throws Exception {
        mockMvc.perform(MockUser.admin(put(URL_THE_TENANT, 123456))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.asJsonString(new Tenant(null, "inactive"))))
                .andExpect(status().isOk());

        mockMvc.perform(MockUser.admin(get(URL_THE_TENANT, 123456)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("tenant a"))
                .andExpect(jsonPath("id").value("123456"))
                .andExpect(jsonPath("state").value("inactive"));
    }

    @Autowired
    private SawAgent sawAgent;

    @Test
    @Sql("insert_test_tenants.sql")
    public void createTenantOperationTest() throws Exception {
        mockServer.expect(requestTo(sawAgent.createURL())).andExpect(method(HttpMethod.PUT)).andRespond(withSuccess());
        MvcResult mvcResult = mockMvc.perform(MockUser.admin(put(URL_TENANT_OPERATIONS, "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.asJsonString(new TenantOperation(null, null, "create", null, false))))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString(new UriTemplate(SERVER_PREFIX + URL_TENANT_OPERATIONS).expand(123456).toURL().toString())))
                .andReturn();

        String location = mvcResult.getResponse().getHeader("location");
        mockMvc.perform(new MockUser("admin","123").with(get(location)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("isSuccessful").value(true));
    }
}
