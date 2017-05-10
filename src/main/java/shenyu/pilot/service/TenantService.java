package shenyu.pilot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shenyu.pilot.dao.TenantRepository;
import shenyu.pilot.model.Tenant;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
@Service
@Transactional
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Transactional(readOnly = true)
    public List<Tenant> getAll() {
        return tenantRepository.getAll();
    }
}
