package com.boke.pojo;


public class Menu implements Comparable<Menu> {
    private Integer id;

    private String description;

    private String permission;

    private Integer available;

    private Integer children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public int compareTo(Menu o) {
        // TODO Auto-generated method stub
        return this.id-o.id;
    }
}