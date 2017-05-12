package shenyu.pilot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sheyu on 5/12/2017.
 */
@Configuration
@ComponentScan("shenyu.pilot.agent")
public class AgentConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
