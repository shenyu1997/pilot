package shenyu.pilot.model;

/**
 * Created by sheyu on 5/16/2017.
 */
public class AuditObject {
    private String id;
    private String who;
    private String action;
    private Long actionTime;
    private String target;
    private String targetType;
    private String targetId;

    public AuditObject(String who, String action, Long actionTime, String target, String targetType, String targetId) {
        this.who = who;
        this.action = action;
        this.actionTime = actionTime;
        this.target = target;
        this.targetType = targetType;
        this.targetId = targetId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getActionTime() {
        return actionTime;
    }

    public void setActionTime(Long actionTime) {
        this.actionTime = actionTime;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    @Override
    public String toString() {
        return "AuditObject{" +
                "id='" + id + '\'' +
                ", who='" + who + '\'' +
                ", action='" + action + '\'' +
                ", actionTime=" + actionTime +
                ", target='" + target + '\'' +
                ", targetType='" + targetType + '\'' +
                ", targetId='" + targetId + '\'' +
                '}';
    }
}
