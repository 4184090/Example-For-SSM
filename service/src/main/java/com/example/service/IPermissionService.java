package com.example.service;

import com.example.domain.Permission;


import java.util.List;

public interface IPermissionService {

   void save(Permission permission) throws Exception;

   List<Permission> findAll(Integer page, Integer pageSize) throws Exception;

    void deleteById(String id) throws Exception;

    void deleteByPermissionId(String id) throws Exception;
}
