package group.tonight.databingdemo;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -4464353392111348347L;
    private String name;
    private int age;
    private Object avatorUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Object getAvatorUrl() {
        return avatorUrl;
    }

    public void setAvatorUrl(Object avatorUrl) {
        this.avatorUrl = avatorUrl;
    }
}
