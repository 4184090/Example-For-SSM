package com.example.service.impl;

import com.example.dao.IPermissionDao;
import com.example.domain.Permission;
import com.example.service.IPermissionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao iPermissionDao;

    @Override
    public void save(Permission permission) throws Exception {
        iPermissionDao.save(permission);
    }

    @Override
    public List<Permission> findAll(Integer page, Integer pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return iPermissionDao.findAll();
    }

    @Override
    public void deleteById(String id) throws Exception {
        iPermissionDao.deleteById(id);
    }

    @Override
    public void deleteByPermissionId(String id) throws Exception {
        iPermissionDao.deleteByPermissionId(id);
    }
}
