package shenyu.pilot.web;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import shenyu.pilot.config.AgentConfig;
import shenyu.pilot.config.DaoConfig;
import shenyu.pilot.config.ServiceConfig;
import shenyu.pilot.config.WebConfig;

import javax.servlet.Filter;

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

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] { new DelegatingFilterProxy("authFilter")};
    }
}
