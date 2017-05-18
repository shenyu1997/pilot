package shenyu.pilot.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by sheyu on 5/18/2017.
 */
@Component
@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserContext {
    private Principal principal;

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public Principal getPrincipal() {
        return principal;
    }
}
