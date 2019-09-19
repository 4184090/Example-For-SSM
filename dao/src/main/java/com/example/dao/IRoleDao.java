package com.example.dao;

import com.example.domain.Permission;
import com.example.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface IRoleDao {

    @Select("select * from ROLE where id in (select roleId from USERS_ROLE where userId = #{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.example.dao.IPermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRoleByUserId(String userId) throws Exception;

    @Insert("insert into ROLE (roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    @Select("select * from ROLE")
    List<Role> findAll() throws Exception;

    @Select("select * from ROLE where id = #{roleId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.example.dao.IPermissionDao.findPermissionByRoleId"))
    })
    Role findById(String roleId) throws Exception;

    @Select("select * from PERMISSION where id not in (select permissionId from ROLE_PERMISSION where roleId = #{roleId})")
    List<Permission> findOtherPermission(String userId) throws Exception;

    @Insert("insert into ROLE_PERMISSION (permissionId,roleId) values (#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId) throws Exception;

    @Delete("delete from ROLE where id = #{id}")
    void deleteById(String id) throws Exception;

    @Delete("delete from ROLE_PERMISSION where roleId = #{id}")
    void deleteByRoleId(String id) throws Exception;

    @Delete("delete from USERS_ROLE where roleId = #{id}")
    void deleteByRoleIds(String id) throws Exception;
}
