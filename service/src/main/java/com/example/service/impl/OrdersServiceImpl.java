package com.example.service.impl;

import com.example.dao.IOrdersDao;
import com.example.domain.Orders;
import com.example.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao iOrdersDao;
    @Override
    public List<Orders> findAll(int page, int pageSize) throws Exception {
        PageHelper.startPage(page, pageSize);//放在执行语句前，中间不能有其他语句
        return iOrdersDao.findAll();
    }

    @Override
    public Orders findById(String id) throws Exception {
        return iOrdersDao.findById(id);
    }
}
