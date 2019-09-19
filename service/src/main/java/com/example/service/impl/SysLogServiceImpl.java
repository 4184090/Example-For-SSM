package com.example.service.impl;

import com.example.dao.ISysLogDao;
import com.example.domain.SysLog;
import com.example.service.ISysLogService;
import com.github.pagehelper.PageHelper;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao iSysLogDao;

    @Override
    public void save(SysLog sysLog) throws Exception {
        iSysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer page,Integer pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return iSysLogDao.findAll();
    }
}
