package com.example.service;

import com.example.domain.Permission;
import com.example.domain.Role;

import java.util.List;

public interface IRoleService {

    Role findById(String roleId) throws Exception;

    List<Permission> findOtherPermission(String userId) throws Exception;

    void save(Role role) throws Exception;

    List<Role> findAll(Integer page,Integer pageSize) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;

    void deleteById(String id) throws Exception;

    void deleteByRoleId(String id) throws Exception;

    void deleteByRoleIds(String id) throws Exception;
}
