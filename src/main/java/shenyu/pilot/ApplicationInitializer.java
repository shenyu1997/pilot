package shenyu.pilot;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import shenyu.pilot.config.AgentConfig;
import shenyu.pilot.config.DaoConfig;
import shenyu.pilot.config.ServiceConfig;
import shenyu.pilot.config.WebConfig;

/**
 * Created by Administrator on 2017/5/7.
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {ServiceConfig.class, DaoConfig.class, AgentConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[] {"/api/*"};
    }
}
