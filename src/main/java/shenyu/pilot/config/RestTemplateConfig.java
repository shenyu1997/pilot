package shenyu.pilot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import shenyu.pilot.model.Principal;
import shenyu.pilot.model.UserContext;
import shenyu.pilot.web.CorrelationIdFilter;

/**
 * Created by sheyu on 5/18/2017.
 */
@Configuration
public class RestTemplateConfig {

    @Autowired
    private UserContext userContext;

    @Bean
    public ClientHttpRequestInterceptor clientHttpRequestInterceptor() {
        return (final HttpRequest request, byte[] content, ClientHttpRequestExecution execution) -> {
            Principal principal = userContext.getPrincipal();
            request.getHeaders().set(CorrelationIdFilter.HEAD_CORRELATION_ID, userContext.getCorrelationId());
            return execution.execute(request, content);
        };
    }
}
