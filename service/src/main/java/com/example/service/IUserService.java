package com.example.service;

import com.example.domain.Role;
import com.example.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    void addRoleToUser(String userId,String[] roleIds) throws Exception;

    List<Role> findOtherRoles(String userId) throws Exception;

    UserInfo findById(String id) throws Exception;

    void save(UserInfo userInfo) throws Exception;

    List<UserInfo> findAll(int page,int pageSize) throws Exception;

    void deleteById(String id) throws Exception;

    void deleteByUserId(String id) throws Exception;
}
