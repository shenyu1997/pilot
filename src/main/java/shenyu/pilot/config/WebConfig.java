package shenyu.pilot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2017/5/7.
 */
@Configuration
@ComponentScan({"shenyu.pilot.api"})
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

}
