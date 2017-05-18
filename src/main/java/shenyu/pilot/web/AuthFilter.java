package shenyu.pilot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import shenyu.pilot.model.Principal;
import shenyu.pilot.model.UserContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sheyu on 5/17/2017.
 */
@Component
public class AuthFilter implements Filter {
    private Pattern pattern = Pattern.compile("^Basic ([\\w=]+)$");

    @Autowired
    private UserContext userContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String authStr = httpServletRequest.getHeader("Authorization");
            if(StringUtils.isEmpty(authStr)) {
                ((HttpServletResponse)servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                servletResponse.getOutputStream().flush();
                servletResponse.getOutputStream().close();
                return;
            }
            Matcher matcher = pattern.matcher(authStr);
            if(matcher.find()) {
                String nameAndPwdBase64Str = matcher.group(1);
                String nameAndPwd = new String(Base64.getDecoder().decode(nameAndPwdBase64Str));
                int index = nameAndPwd.indexOf(":");
                String name = nameAndPwd.substring(0,index);
                String pwd = nameAndPwd.substring(index + 1);
                userContext.setPrincipal(new Principal(name, pwd));
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            ((HttpServletResponse)servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            servletResponse.getOutputStream().flush();
            servletResponse.getOutputStream().close();
            return;
        }
        servletResponse.getOutputStream().flush();
        servletResponse.getOutputStream().close();
    }

    @Override
    public void destroy() {

    }
}
