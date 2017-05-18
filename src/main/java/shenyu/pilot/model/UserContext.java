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
    private String correlationId;

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public Principal getPrincipal() {
        return principal;
    }

    @Override
    public String toString() {
        return "UserContext{" +
                "principal=" + principal +
                ", correlationId='" + correlationId + '\'' +
                '}';
    }
}
