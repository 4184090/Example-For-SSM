package com.example.dao;

import com.example.domain.Product;
import com.github.pagehelper.ISelect;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductDao {

    @Select("select * from PRODUCT where id=#{id}")
    Product findById(String id) throws Exception;

    @Select("select * from PRODUCT")
    List<Product> findAll() throws Exception;

    @Insert("insert into PRODUCT(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;
}
