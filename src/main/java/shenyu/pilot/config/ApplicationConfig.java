package shenyu.pilot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/5/8.
 */
@Configuration
@ComponentScan(value = "shenyu.pilot",
        excludeFilters = {@ComponentScan.Filter(value = RestController.class,type= FilterType.ANNOTATION),
        @ComponentScan.Filter(value = WebConfig.class, type = FilterType.ASSIGNABLE_TYPE)})
@EnableAspectJAutoProxy
public class ApplicationConfig {
}
