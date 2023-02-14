package com.example.javaee.service;

import com.example.javaee.entity.User;
import com.example.javaee.utils.Role;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface UserService {

    boolean signup(Map<String, String> signupInfo);
    User checkLogin(Map<String, String> loginInfo);

    boolean modifyPwd(Map<String, String> passwordInfo);
    User getUserInfo(Map<String, String> userID);
    User updateUserInfo(Map<String, String> updateInfo);
    List<User> findAllUser();

    int deleteById(Map<String, String> updateInfo);
}
