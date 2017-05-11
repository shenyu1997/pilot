package shenyu.pilot.dao.util;

import org.springframework.stereotype.Component;

/**
 * Created by sheyu on 5/11/2017.
 */
@Component
public class IdGenerator {

    public String next() {
        return String.valueOf(System.nanoTime());
    }
}
