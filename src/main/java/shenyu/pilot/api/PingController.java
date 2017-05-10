package shenyu.pilot.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static shenyu.pilot.api.PingController.URI_PING;

/**
 * Created by Administrator on 2017/5/7.
 */
@RestController
@RequestMapping(URI_PING)
public class PingController {
    public static final String URI_PING = "/ping";

    @GetMapping("/")
    public String ping() {
        return "ok";
    }
}
