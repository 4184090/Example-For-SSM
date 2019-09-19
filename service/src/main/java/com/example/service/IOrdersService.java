package com.example.service;

import com.example.domain.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll(int page, int pageSize) throws Exception;

    Orders findById(String id) throws Exception;

}
