package shenyu.pilot.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import shenyu.pilot.IntegrationTest;
import shenyu.pilot.model.AuditObject;
import shenyu.pilot.model.Entity;

import java.util.Collections;
import java.util.List;

/**
 * Created by sheyu on 5/16/2017.
 */
public class AuditServiceTest extends IntegrationTest {
    @Autowired
    private AuditService auditService;

    @Test
    public void addAndGetTest() {
        AuditObject auditObject = auditService.addAudit("peter", "save", new Entity() {
            @Override
            public String getId() {
                return "1234";
            }

            @Override
            public String getType() { return "Test"; }

            public String getName() {
                return "test";
            }
        });
        AuditObject persistenceAuditObj = auditService.getAuditById(auditObject.getId());
        Assert.assertEquals(persistenceAuditObj.getWho(), "peter");
        Assert.assertEquals(persistenceAuditObj.getAction(),"save");
        Assert.assertEquals(persistenceAuditObj.getTargetId(),"1234");

        List<AuditObject> audits = auditService.getAuditsByEntity(new Entity() {
            @Override
            public String getId() {
                return "1234";
            }

            @Override
            public String getType() { return "Test"; }
        });

        Assert.assertEquals(audits.size(),1);
    }
}
