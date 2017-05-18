package shenyu.pilot.model;

/**
 * Created by sheyu on 5/18/2017.
 */
public class Principal {
    private String name;
    private String credential;

    public Principal(String name, String credential) {
        this.name = name;
        this.credential = credential;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "name='" + name + '\'' +
                '}';
    }
}
