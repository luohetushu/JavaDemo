package com.mytest.bean;

import java.io.Serializable;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-11-25
 */
public class User implements Serializable {
    String name;
    String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
