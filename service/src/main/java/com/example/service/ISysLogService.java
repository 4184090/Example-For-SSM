package com.example.service;

import com.example.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll(Integer page,Integer pageSize) throws Exception;

}
