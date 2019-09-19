package com.example.service.impl;

import com.example.dao.IProductDao;
import com.example.domain.Product;
import com.example.service.IProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao iProductDao;
    @Override
    public List<Product> findAll(Integer page, Integer pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return iProductDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        iProductDao.save(product);
    }
}
