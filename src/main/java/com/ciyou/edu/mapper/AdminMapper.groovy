package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Admin
import com.github.pagehelper.Page
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

/**
 * @Author C.
 * @Date 2018-02-02 16:55
 */
interface AdminMapper {

    @Insert("insert into Admin(adminName,password,name,phone) values(#{adminName},#{password},#{name},#{phone})")
    int addAdmin(Admin admin)

    @Select("select * from Admin where adminId = #{adminId} and isAvalible = 1")
    Admin findAdminById(@Param("adminId")Integer id)

    @Select("select * from Admin where adminName = #{adminName} and isAvalible = 1")
    Admin findAdminByName(@Param("adminName")String name)


    @Select("select * from Admin where adminId <> #{adminId} and isAvalible = 1")
    Page<Admin> findAllAdmin(@Param("adminId")Integer id)

    @Update("update Admin set name = #{name} , phone = #{phone} where adminId = #{adminId}")
    int updateAdmin(Admin admin)

    @Update("update Admin set isAvalible = 0 where adminId = #{adminId}")
    int deleteAdmin(@Param("adminId")Integer id)

    //在mybatis里面写就是应该是 like  '%${name} %' 而不是 '%#{name} %'
    //否则报错：Parameter index out of range (1 > number of parameters, which is 0)类似的错误
    @Select("select * from Admin where adminId <> #{adminId} and isAvalible = 1 and ( adminId like '%\${value}%' or adminName like '%\${value}%' or  name like '%\${value}%' or phone like '%\${value}%' )")
    Page<Admin> queryAdmin(@Param("adminId")Integer id,@Param("value")String value)

    @Update("update Admin set password = #{password} where adminId = #{adminId}")
    int updatePassword(@Param("adminId")Integer id, @Param("password")String password)
}
