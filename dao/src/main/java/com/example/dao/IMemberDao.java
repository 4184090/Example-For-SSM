package com.example.dao;

import com.example.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberDao {

    @Select("select * from MEMBER where id=#{id}")
    Member findById(String id) throws Exception;
}
