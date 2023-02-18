package com.example.javaee.service.serviceimpl;

import com.example.javaee.dao.UserDao;
import com.example.javaee.entity.User;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.UserService;
import com.example.javaee.utils.Result;
import com.example.javaee.utils.Role;
import com.example.javaee.utils.SensitiveWordFilter;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional(rollbackFor =RuntimeException.class)
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDao userDao;

    public boolean usernameExist(String username)
    {
        User user = userDao.findByUsername(username);
        return user != null;
    }

    public boolean user_idExist(int user_id)
    {
        User user = userDao.findByUserId(user_id);
        return user != null;
    }

    public boolean pwdCorrect(int user_id , String pwd)
    {
        User user = userDao.findByUserId(user_id);
        return user.getPassword().equals(pwd);
    }

    public boolean pwdCorrect(String username , String pwd)
    {
        User user = userDao.findByUsername(username);
        return user.getPassword().equals(pwd);
    }



    public boolean signup(String username, String password,Role role) {
        userDao.insertNewUser(username , password , role);
        return true;
    }

    public User checkLogin(String username , String password) {
        User user = userDao.findByUsername(username);
        return user;
    }

    @Override
    public boolean modifyPwd(int user_id , String new_pwd) {
        userDao.modifyPwd(user_id , new_pwd);
        return true;
    }

    public User getUserInfo(int user_id)
    {
        return userDao.findByUserId(user_id);
    }

    public User updateUserInfo(int user_id, String username, String email, String phone, String name)
    {
        User user = userDao.findByUserId(user_id);
        userDao.updateUserInfo(new User(user.getUser_id(), username , user.getPassword() , user.getRole() , email , phone , name));
        return userDao.findByUserId(user_id);
    }

    public List<User> findAllUser() {
        return  userDao.findAll();
    }

    public int deleteById(int user_id) {
        return  userDao.deleteById(user_id);
    }

}
