package com.example.javaee.service;

import com.example.javaee.entity.User;
import com.example.javaee.utils.Role;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<String, Object> signup(Map<String, String> loginInfo);
    Map<String, Object> checkLogin(Map<String, String> loginInfo);

    Map<String, Object> updateUserInfo(Map<String, String> updateInfo);
    Map<String, Object> findAllUser();

    Map<String, Object> deleteById(Map<String, String> updateInfo);
}
