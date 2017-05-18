package shenyu.pilot.common;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Base64;

/**
 * Created by sheyu on 5/17/2017.
 */
public class MockUser {
    private String username;
    private String password;
    public static String BASIC_AUTH_HEAD = "Authorization";

    public MockUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public MockHttpServletRequestBuilder with(MockHttpServletRequestBuilder builder) {
        return builder.header(BASIC_AUTH_HEAD, mockUserPwdStr());
    }

    public String mockUserPwdStr() {
        return "Basic " + Base64.getEncoder().encodeToString(String.format("%s:%s",this.username, this.password).getBytes());
    }

    public static MockHttpServletRequestBuilder admin(MockHttpServletRequestBuilder builder) {
        return new MockUser("admin", "123456").with(builder);
    }
}
