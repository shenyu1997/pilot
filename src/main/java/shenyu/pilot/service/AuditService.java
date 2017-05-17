package shenyu.pilot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shenyu.pilot.dao.AuditRepository;
import shenyu.pilot.model.AuditObject;
import shenyu.pilot.model.Auditable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sheyu on 5/16/2017.
 */
@Service
@Transactional
public class AuditService {

    @Autowired
    private AuditRepository auditRepository;

    public AuditObject addAudit(String who, String action, Auditable target) {
        try {
            String targetStr = "";
            String targetType = "";
            Serializable targetId = "";
            if(target != null) {
                targetStr = new ObjectMapper().writeValueAsString(target);
                targetType = target.getType();
                targetId = target.getId();
            }

            AuditObject auditObject = new AuditObject(who, action, System.currentTimeMillis(), targetStr, targetType, targetId);
            return auditRepository.save(auditObject);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("audit target can not convert to json string, " + e.getMessage(), e);
        }
    }

    public AuditObject getAuditById(String id) {
        return auditRepository.getById(id);
    }

    public List<AuditObject> getAuditsByEntity(Auditable auditable) {
        return auditRepository.getByTargetTypeAndTargetId(auditable.getType(), auditable.getId());
    }
}
