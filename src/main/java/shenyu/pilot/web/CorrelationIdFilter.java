package shenyu.pilot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import shenyu.pilot.model.UserContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by sheyu on 5/18/2017.
 */
@Component
public class CorrelationIdFilter implements Filter {
    public static final String HEAD_CORRELATION_ID = "CORRELATION_ID";

    @Autowired
    private UserContext userContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String correlationId = UUID.randomUUID().toString();
        if(servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            if(!StringUtils.isEmpty(httpServletRequest.getHeader("CORRELATION_ID"))) {
                correlationId = httpServletRequest.getHeader("CORRELATION_ID");
            }
        }
        userContext.setCorrelationId(correlationId);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
