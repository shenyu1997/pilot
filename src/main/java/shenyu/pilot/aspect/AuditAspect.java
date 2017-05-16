package shenyu.pilot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shenyu.pilot.model.Entity;
import shenyu.pilot.service.AuditService;

/**
 * Created by sheyu on 5/16/2017.
 */
@Aspect
@Component
public class AuditAspect {

    @Autowired
    private AuditService auditService;

    @After("@annotation(auditable)")
    public void audit(JoinPoint joinPoint, Auditable auditable) {
        Object arg = joinPoint.getArgs().length > 0? joinPoint.getArgs()[auditable.value()]: null;
        if(arg instanceof Entity) {
            Entity entity = (Entity) arg;
            String action = "".equals(auditable.action())? joinPoint.getSignature().getName() : auditable.action();
            auditService.addAudit(null, action, entity);
        } else {
            throw new IllegalArgumentException("argument could not be casted to Entity: " + arg);
        }
    }
}
