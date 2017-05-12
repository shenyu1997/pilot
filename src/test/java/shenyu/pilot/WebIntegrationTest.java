package shenyu.pilot;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockMvcClientHttpRequestFactory;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import shenyu.pilot.config.WebConfig;

/**
 * Created by Administrator on 2017/5/7.
 */
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public abstract class WebIntegrationTest extends IntegrationTest {
    public static final String SERVER_PREFIX = "http://localhost";

    @Autowired
    protected WebApplicationContext webApplicationContext;


    protected MockMvc mockMvc;

    protected MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    @Profile("test")
    public RestTemplate restTemplate() {
        return new RestTemplate(new MockMvcClientHttpRequestFactory(mockMvc));
    }

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockServer = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @After
    public void after() {
        mockServer.verify();
    }
}
