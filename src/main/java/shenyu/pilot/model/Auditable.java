package shenyu.pilot.model;

import java.io.Serializable;

/**
 * Created by sheyu on 5/16/2017.
 */
public interface Auditable {
    String getType();
    Serializable getId();
}
