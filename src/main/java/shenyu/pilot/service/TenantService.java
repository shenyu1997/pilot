package shenyu.pilot.service;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shenyu.pilot.agent.SawApi;
import shenyu.pilot.dao.TenantOperationRepository;
import shenyu.pilot.dao.TenantRepository;
import shenyu.pilot.model.Tenant;
import shenyu.pilot.model.TenantOperation;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
@Service
@Transactional
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private TenantOperationRepository tenantOperationRepository;

    @Autowired
    private SawApi sawApi;

    private static Logger logger = Logger.getLogger(TenantService.class);

    @Transactional(readOnly = true)
    public List<Tenant> getAll() {
        return tenantRepository.getAll();
    }

    public Tenant getTenant(String id) {
        return tenantRepository.getById(id);
    }

    public Tenant createTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    public void updateTenant(String id, Tenant tenant) {
        Tenant original = tenantRepository.getById(id);
        original.setState(tenant.getState());
        tenantRepository.update(original);
    }

    public TenantOperation createTenantOperation(TenantOperation operation) {
        tenantRepository.getById(operation.getTenantId());

        boolean isSuccessful = false;
        try {
            isSuccessful = sawApi.operateTenant(operation);
        } catch (Exception e) {
            logger.error(e);
        }

        operation.setIsSuccessful(isSuccessful);
        TenantOperation createdOperation = tenantOperationRepository.save(operation);
        return createdOperation;
    }

    public TenantOperation getTenantOperation(String tenantId, String operationId) {
        TenantOperation operation = tenantOperationRepository.getById(operationId);
        Assert.assertEquals(operation.getTenantId(), tenantId);
        return operation;
    }
}
