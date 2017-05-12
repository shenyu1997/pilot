package shenyu.pilot.agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import shenyu.pilot.model.TenantOperation;

import java.net.MalformedURLException;

/**
 * Created by sheyu on 5/12/2017.
 */
@Component
@PropertySource("classpath:saw.properties")
public class SawAgent {

    @Value("${saw.schema}")
    private String schema;

    @Value("${saw.host}")
    private String host;

    @Value("${saw.port}")
    private String port;

    @Value("${saw.uri.prefix}")
    private String uriPrefix;

    @Autowired
    private RestTemplate restTemplate;

    public boolean operate(TenantOperation tenantOperation) {
        try {
            switch (tenantOperation.getAction()) {
                case "create":
                    create(tenantOperation);
                    break;
                default:
                    return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String createURL() {
        try {
            return new UriTemplate("{schema}://{host}:{port}{uriPrefix}tenantManagement/create")
                    .expand(this.schema, this.host, this.port, this.uriPrefix)
                    .toURL().toString();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void create(TenantOperation tenantOperation) {
        restTemplate.put(createURL(), tenantOperation);
    }
}
