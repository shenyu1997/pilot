package shenyu.pilot.common;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by sheyu on 5/11/2017.
 */
public class JsonUtil {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
