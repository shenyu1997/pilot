package shenyu.pilot.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

/**
 * Created by Administrator on 2017/5/8.
 */
@Configuration
@ComponentScan(value = "shenyu.pilot",
        excludeFilters = {@ComponentScan.Filter(value = RestController.class,type= FilterType.ANNOTATION),
        @ComponentScan.Filter(value = WebConfig.class, type = FilterType.ASSIGNABLE_TYPE)})
@EnableAspectJAutoProxy
public class ApplicationConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasenames("l10n/messages");
        return resourceBundleMessageSource;
    }

}
