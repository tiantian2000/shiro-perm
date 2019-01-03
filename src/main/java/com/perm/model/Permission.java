package com.perm.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/12/23.
 */
public class Permission implements Serializable {

    private Integer id;

    private String permCode;

    private String permName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermCode() {
        return permCode;
    }

    public void setPermCode(String permCode) {
        this.permCode = permCode;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permCode='" + permCode + '\'' +
                ", permName='" + permName + '\'' +
                '}';
    }
}
