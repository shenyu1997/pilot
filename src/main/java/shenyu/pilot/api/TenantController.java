package shenyu.pilot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shenyu.pilot.service.TenantService;
import shenyu.pilot.model.Tenant;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
@RestController
public class TenantController {

    @Autowired
    private TenantService tenantService;

    public static final String URL_TENANT = "/tenant/";

    @GetMapping(URL_TENANT)
    public List<Tenant> all() {
        return tenantService.getAll();
    }


}
