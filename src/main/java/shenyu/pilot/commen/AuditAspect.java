package shenyu.pilot.commen;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shenyu.pilot.model.Auditable;
import shenyu.pilot.model.UserContext;
import shenyu.pilot.service.AuditService;

/**
 * Created by sheyu on 5/16/2017.
 */
@Aspect
@Component
public class AuditAspect {

    @Autowired
    private AuditService auditService;

    @Autowired
    private UserContext userContext;

    @After("@annotation(audit)")
    public void audit(JoinPoint joinPoint, Audit audit) {
        Object arg = joinPoint.getArgs().length > 0? joinPoint.getArgs()[audit.value()]: null;
        if(arg instanceof Auditable) {
            Auditable auditable = (Auditable) arg;
            String action = "".equals(audit.action())? joinPoint.getSignature().getName() : audit.action();
            auditService.addAudit(userContext.getPrincipal().getName(), action, auditable);
        } else {
            throw new IllegalArgumentException("argument could not be casted to Entity: " + arg);
        }
    }
}
