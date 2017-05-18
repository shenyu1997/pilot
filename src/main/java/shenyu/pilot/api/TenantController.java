package shenyu.pilot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import shenyu.pilot.model.AuditObject;
import shenyu.pilot.model.TenantOperation;
import shenyu.pilot.service.AuditService;
import shenyu.pilot.service.TenantService;
import shenyu.pilot.model.Tenant;

import java.net.URI;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
@RestController
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @Autowired
    private AuditService auditService;

    public static final String TENANT_ID = "tenantId";
    public static final String OPERATION_ID = "operationId";
    public static final String URL_TENANTS = "/tenant/";
    public static final String URL_THE_TENANT = URL_TENANTS + "{" + TENANT_ID + "}";
    public static final String URL_TENANT_OPERATIONS = URL_THE_TENANT + "/operation/";
    public static final String URL_THE_TENANT_OPERATION = URL_TENANT_OPERATIONS + "{" + OPERATION_ID + "}";
    public static final String URL_THE_TENANT_AUDITS = URL_THE_TENANT + "/audit/";

    @GetMapping(URL_TENANTS)
    public List<Tenant> all() {
        return tenantService.getAll();
    }

    @GetMapping(URL_THE_TENANT)
    public Tenant getTenant(@PathVariable(TENANT_ID) String id) {
        return tenantService.getTenant(id);
    }

    @GetMapping(URL_THE_TENANT_AUDITS)
    public List<AuditObject> auditObjects(@PathVariable(TENANT_ID) String id) {
        Tenant tenant = new Tenant();
        tenant.setId(id);
        return auditService.getAuditsByEntity(tenant);
    }

    @PostMapping(URL_TENANTS)
    public ResponseEntity<Tenant> createTenant(@RequestBody Tenant tenant, UriComponentsBuilder ucb) {
        Tenant newTenant = tenantService.createTenant(tenant);
        URI uri = ucb.path(URL_TENANTS).path(newTenant.getId()).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(URL_THE_TENANT)
    public void updateTenant(@PathVariable(TENANT_ID) String id, @RequestBody Tenant tenant) {
        tenantService.updateTenant(id, tenant);
    }

    @PutMapping(URL_TENANT_OPERATIONS)
    public ResponseEntity<Tenant> addOperation(@PathVariable(TENANT_ID) String id, @RequestBody TenantOperation operation, UriComponentsBuilder ucb) {
        operation.setTenantId(id);
        TenantOperation tenantOperation = tenantService.createTenantOperation(operation);
        URI uri = ucb.path(URL_TENANT_OPERATIONS).path(tenantOperation.getId()).build().expand(tenantOperation.getTenantId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(URL_THE_TENANT_OPERATION)
    public TenantOperation getTenantOperation(@PathVariable(TENANT_ID)String tenantId, @PathVariable(OPERATION_ID)String operationId) {
        return tenantService.getTenantOperation(tenantId, operationId);
    }

}
