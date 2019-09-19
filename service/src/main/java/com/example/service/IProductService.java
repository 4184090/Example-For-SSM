package com.example.service;

import com.example.domain.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll(Integer page, Integer pageSize) throws Exception;

    void save(Product product) throws Exception;
}
