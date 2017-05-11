package shenyu.pilot.model;

/**
 * Created by Administrator on 2017/5/8.
 */
public class Tenant {
    private String id;
    private String name;
    private String state;

    public Tenant() {
    }

    public Tenant(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
