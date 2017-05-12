package shenyu.pilot.model;

/**
 * Created by sheyu on 5/12/2017.
 */
public class TenantOperation {
    private String id;
    private String tenantId;
    private String action;
    private Long actionTime;
    private boolean isSuccessful;

    public TenantOperation() {
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TenantOperation(String id, String tenantId, String action, Long actionTime, boolean isSuccessful) {
        this.id = id;
        this.tenantId = tenantId;
        this.action = action;
        this.actionTime = actionTime;
        this.isSuccessful = isSuccessful;
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

    public boolean getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    @Override
    public String toString() {
        return "TenantOperation{" +
                "id='" + id + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", action='" + action + '\'' +
                ", actionTime=" + actionTime +
                ", isSuccessful=" + isSuccessful +
                '}';
    }
}
