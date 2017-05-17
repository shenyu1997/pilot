package shenyu.pilot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Administrator on 2017/5/8.
 */
@Configuration
@ComponentScan({"shenyu.pilot.service","shenyu.pilot.aspect","shenyu.pilot.web"})
@EnableAspectJAutoProxy
public class ServiceConfig {
}
