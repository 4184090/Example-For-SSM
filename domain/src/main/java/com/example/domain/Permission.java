package com.example.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class Permission implements Serializable {
    private final static Long SerialVersionUID = 1L;

    private String id;//主键
    private String permissionName;//权限名
    private String url;//资源路径
    private List<Role> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
