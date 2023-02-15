package com.example.javaee.service;

import com.example.javaee.entity.User;
import com.example.javaee.utils.Role;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface UserService {

    boolean usernameExist(String username);
    boolean user_idExist(int user_id);
    boolean pwdCorrect(int user_id , String pwd);
    boolean pwdCorrect(String username , String pwd);

    boolean signup(String username , String password , Role role);
    User checkLogin(String username , String password);
    boolean modifyPwd(int user_id , String new_pwd);
    User getUserInfo(int user_id);
    User updateUserInfo(int user_id, String username, String email, String phone, String name);
    List<User> findAllUser();

    int deleteById(int user_id);
}
