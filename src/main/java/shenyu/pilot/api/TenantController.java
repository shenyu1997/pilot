package shenyu.pilot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
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

    public static final String URL_TENANT = "/tenant/";
    public static final String URL_GET_TENANT = URL_TENANT + "{id}";

    @GetMapping(URL_TENANT)
    public List<Tenant> all() {
        return tenantService.getAll();
    }

    @GetMapping(URL_GET_TENANT)
    public Tenant getTenant(@PathVariable("id") String id) {
        return tenantService.getTenant(id);
    }

    @PostMapping(URL_TENANT)
    public ResponseEntity<Tenant> createTenant(@RequestBody Tenant tenant, UriComponentsBuilder ucb) {
        Tenant newTenant = tenantService.createTenant(tenant);
        URI uri = ucb.path(URL_TENANT).path(newTenant.getId()).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> exceptionHandler(EmptyResultDataAccessException e) {
        return ResponseEntity.notFound().build();
    }
}
