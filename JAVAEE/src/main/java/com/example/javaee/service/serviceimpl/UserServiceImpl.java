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
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDao userDao;

    public boolean signup(@RequestBody Map<String, String> loginInfo) {
        String username = loginInfo.get("username");
        String password = loginInfo.get("password");
        String role = loginInfo.get("role");

        if(username == null || username.isEmpty())
           throw new BusinessException(ExceptionEnum.USERNAME_空);


        if(password == null || password.isEmpty())
            throw new BusinessException(ExceptionEnum.PASSWORD_空);

        if(role == null || role.isEmpty())
            throw new BusinessException(ExceptionEnum.ROLE_空);

        SensitiveWordFilter filter = new SensitiveWordFilter();// 加载敏感词库
        Set<String> set = filter.getSensitiveWord(username, 1);// 比对敏感词
        if (set.size() > 0)
            throw new BusinessException(ExceptionEnum.USERNAME_敏感);

        if(userDao.findByUsername(username) != null)
            throw new BusinessException(ExceptionEnum.USERNAME_重复);

        try
        {

            Role userRole = Role.valueOf(role);
            userDao.insertNewUser(username , password , userRole);
            return true;
        }
        catch (IllegalArgumentException e)
        {
           throw new BusinessException(ExceptionEnum.ROLE_范围外);
        }
    }

    public User checkLogin(@RequestBody Map<String, String> loginInfo) {
        String username = loginInfo.get("username");
        String password = loginInfo.get("password");

        if(username == null || username.isEmpty())
            throw new BusinessException(ExceptionEnum.USERNAME_空);

        if(password == null || password.isEmpty())
            throw new BusinessException(ExceptionEnum.PASSWORD_空);

        User user = userDao.findByUsername(username);

        if(user == null)
            throw new BusinessException(ExceptionEnum.USERNAME_无效);

        if( ! user.getPassword().equals(password))
            throw new BusinessException(ExceptionEnum.PASSWORD_错误);

        return user;
    }

    @Override
    public boolean modifyPwd(Map<String, String> passwordInfo) {
        try
        {
            int user_id = Integer.parseInt(passwordInfo.get("user_id"));
            String old_pwd = passwordInfo.get("old_password");
            String new_pwd = passwordInfo.get("new_password");

            User user = userDao.findByUserId(user_id);

            if(user == null)
                throw new BusinessException(ExceptionEnum.ID_无效);

            if(old_pwd == null || old_pwd.isEmpty() || new_pwd == null || new_pwd.isEmpty())
                throw new BusinessException(ExceptionEnum.PASSWORD_空);

            if(!user.getPassword().equals(old_pwd))
                throw new BusinessException(ExceptionEnum.PASSWORD_错误);

            if(old_pwd.equals(new_pwd))
                throw new BusinessException(ExceptionEnum.PASSWORD_重复);

            return true;
        }
        catch (NumberFormatException e)
        {
//            return Result.fail("该用户id类型不合法" , false);
            throw new BusinessException(ExceptionEnum.ID_不合法);
        }
    }

    public User getUserInfo(Map<String, String> userID) {
        try
        {
            int user_id = Integer.parseInt(userID.get("user_id"));
            User user = userDao.findByUserId(user_id);
            return user;
        }
        catch (NumberFormatException e)
        {
            throw new BusinessException(ExceptionEnum.ID_不合法);
        }
    }

    public User updateUserInfo(Map<String, String> updateInfo) {
        try
        {
            int user_id = Integer.parseInt(updateInfo.get("user_id"));
            String username = updateInfo.get("username");
            String password = updateInfo.get("password");
            String email = updateInfo.get("email");
            String phone = updateInfo.get("phone");
            String name = updateInfo.get("name");

            if(username == null || username.isEmpty())
                throw new BusinessException(ExceptionEnum.USERNAME_空);

            if(password == null || password.isEmpty())
                throw new BusinessException(ExceptionEnum.PASSWORD_空);

            User user = userDao.findByUserId(user_id);
            if(user == null)
                throw new BusinessException(ExceptionEnum.ID_无效);

            SensitiveWordFilter filter = new SensitiveWordFilter();
            Set<String> set = filter.getSensitiveWord(username, 1);
            if (set.size() > 0)
                throw new BusinessException(ExceptionEnum.USERNAME_敏感);

            User repeatUser = userDao.findByUsername(username);
            if(repeatUser != null && repeatUser.getUser_id() != user_id)
                throw new BusinessException(ExceptionEnum.USERNAME_重复);

            userDao.updateUserInfo(new User(user.getUser_id(), username , password , user.getRole() , email , phone , name));
            User newUser = userDao.findByUsername(username);
            System.out.println("updateDone");
            return newUser;
        }
        catch (NumberFormatException e)
        {
            throw new BusinessException(ExceptionEnum.ID_不合法);
        }
    }

    public List<User> findAllUser() {
        return  userDao.findAll();
    }

    public int deleteById(Map<String, String> deleteInfo) {
        try
        {
            int user_id = Integer.parseInt(deleteInfo.get("user_id"));
            User user = userDao.findByUserId(user_id);
            if(user == null)
                throw new BusinessException(ExceptionEnum.ID_无效);

            return  userDao.deleteById(user_id);

        }
        catch (NumberFormatException e)
        {
            throw new BusinessException(ExceptionEnum.ID_不合法);
        }
    }

}
