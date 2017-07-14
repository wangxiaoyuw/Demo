package com.boke.pojo;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{

    private static final long serialVersionUID = -2116772777052364467L;

    private Integer id;

    private String username;

    private String password;

    private Integer locked;

    private List<Role> roleList;

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}