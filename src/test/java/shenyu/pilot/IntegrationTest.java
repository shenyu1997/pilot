package shenyu.pilot;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import shenyu.pilot.config.DaoConfig;
import shenyu.pilot.config.DaoTestConfig;
import shenyu.pilot.config.ServiceConfig;
import shenyu.pilot.config.WebConfig;

/**
 * Created by Administrator on 2017/5/7.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class, DaoTestConfig.class, DaoConfig.class})
@Transactional
@ActiveProfiles("test")
@TestPropertySource("classpath:h2_test.properties")
public abstract class IntegrationTest {
}
