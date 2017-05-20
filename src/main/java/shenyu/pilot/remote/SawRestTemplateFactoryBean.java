package shenyu.pilot.remote;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import shenyu.pilot.model.Principal;
import shenyu.pilot.model.UserContext;

import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by sheyu on 5/12/2017.
 */
@Component
@Qualifier("saw")
@PropertySource("classpath:saw.properties")
public class SawRestTemplateFactoryBean implements FactoryBean<RestTemplate> {

    @Value("${saw.schema}")
    private String sawSchema;

    @Value("${saw.host}")
    private String sawHost;

    @Value("${saw.port}")
    private String sawPort;

    @Value("${saw.uri.prefix}")
    private String sawUriPrefix;

    @Autowired
    private UserContext userContext;

    public URI createURL(String location) {
        try {
            return new UriTemplate("{schema}://{host}:{port}{uriPrefix}{uri}")
                    .expand(this.sawSchema, this.sawHost, this.sawPort, this.sawUriPrefix, location)
                    .toURL().toURI();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public ClientHttpRequestInterceptor authHeaderClientHttpRequestInterceptor() {
        return (final HttpRequest request, byte[] content, ClientHttpRequestExecution execution) -> {
            Principal principal = userContext.getPrincipal();
            String authStr = "Basic " + Base64.getEncoder().encodeToString(String.format("%s:%s", principal.getName(), principal.getCredential()).getBytes());
            request.getHeaders().set("Authorization", authStr);

            return execution.execute(new HttpRequest() {
                @Override
                public HttpMethod getMethod() {
                    return request.getMethod();
                }

                @Override
                public URI getURI() {
                    return SawRestTemplateFactoryBean.this.createURL(request.getURI().toString());
                }

                @Override
                public HttpHeaders getHeaders() {
                    return request.getHeaders();
                }
            }, content);
        };
    }

    @Autowired
    private List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors;

    @Override
    public RestTemplate getObject() throws Exception {
        RestTemplate restTemplate =  new RestTemplate();
        List<ClientHttpRequestInterceptor> sawInterceptors = new ArrayList(clientHttpRequestInterceptors);
        sawInterceptors.add(authHeaderClientHttpRequestInterceptor());
        restTemplate.setInterceptors(sawInterceptors);
        return  restTemplate;
    }

    @Override
    public Class<?> getObjectType() {
        return RestTemplate.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
