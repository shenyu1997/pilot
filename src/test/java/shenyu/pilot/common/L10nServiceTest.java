package shenyu.pilot.common;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import shenyu.pilot.IntegrationTest;
import shenyu.pilot.commen.L10nService;

/**
 * Created by sheyu on 2017/5/20.
 */
public class L10nServiceTest extends IntegrationTest {
    @Autowired
    private L10nService l10nService;

    @Test
    public void fallbackKeyReturn() {
        Assert.assertEquals(l10nService.l10n("abc.xyz"), "abc.xyz");
    }
}
