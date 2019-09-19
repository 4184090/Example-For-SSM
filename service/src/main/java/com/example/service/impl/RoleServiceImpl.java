package com.example.service.impl;

import com.example.dao.IRoleDao;
import com.example.domain.Permission;
import com.example.domain.Role;
import com.example.service.IRoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao iRoleDao;

    @Override
    public Role findById(String roleId) throws Exception {
        return iRoleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermission(String userId) throws Exception {
        return iRoleDao.findOtherPermission(userId);
    }

    @Override
    public void save(Role role) throws Exception {
        iRoleDao.save(role);
    }

    @Override
    public List<Role> findAll(Integer page,Integer pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return iRoleDao.findAll();
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds){
            iRoleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public void deleteById(String id) throws Exception {
        iRoleDao.deleteById(id);
    }

    @Override
    public void deleteByRoleId(String id) throws Exception {
        iRoleDao.deleteByRoleId(id);
    }

    @Override
    public void deleteByRoleIds(String id) throws Exception {
        iRoleDao.deleteByRoleIds(id);
    }
}
