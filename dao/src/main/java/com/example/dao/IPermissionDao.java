package com.example.dao;

import com.example.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Insert("insert into PERMISSION (permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;

    @Select("select * from PERMISSION")
    List<Permission> findAll() throws Exception;

    @Select("select * from PERMISSION where id in (select permissionId from ROLE_PERMISSION where roleId = #{id})")
    List<Permission> findPermissionByRoleId(String id)throws Exception;

    @Delete("delete from PERMISSION where id = #{id}")
    void deleteById(String id) throws Exception;

    @Delete("delete from ROLE_PERMISSION where permissionId = #{id}")
    void deleteByPermissionId(String id) throws Exception;
}
