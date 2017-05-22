package shenyu.pilot.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import shenyu.pilot.model.TenantOperation;

/**
 * Created by sheyu on 5/12/2017.
 */
@Component
public class SawApi {
    public static final String REST_CREATE_TENANT = "tenantManagement/create";


    @Autowired
    @Qualifier("saw")
    private RestTemplate restTemplate;

    public boolean operateTenant(TenantOperation tenantOperation) {
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

    private void create(TenantOperation tenantOperation) {
        restTemplate.put(REST_CREATE_TENANT, tenantOperation);
    }
}
