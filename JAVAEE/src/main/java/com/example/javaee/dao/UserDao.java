package com.example.javaee.dao;

import com.example.javaee.entity.User;
import com.example.javaee.utils.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("SELECT user_id, username, password, role, email, phone, name FROM Users WHERE username = #{username}")
    User findByUsername(String username);

    @Select("SELECT user_id, username, password, role, email, phone, name FROM Users WHERE user_id = #{user_id}")
    User findByUserId(int user_id);

    @Select("SELECT * FROM Users")
    List<User> findAll();

    @Insert("INSERT INTO Users (username,password,role) VALUES (#{username},#{password},#{role})")
    int insertNewUser(String username , String password , Role role);

    @Update("UPDATE Users SET password = #{password} WHERE user_id = #{user_id}")
    int modifyPwd(int user_id , String password);

    int updateUserInfo(User user);

    @Delete("DELETE * FROM Users WHERE user_id=#{user_id}")
    int deleteById(int user_id);




}
