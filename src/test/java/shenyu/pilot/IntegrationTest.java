package shenyu.pilot;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import shenyu.pilot.config.*;

/**
 * Created by Administrator on 2017/5/7.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@Transactional
@ActiveProfiles("test")
@TestPropertySource("classpath:h2_test.properties")
public abstract class IntegrationTest {
}
