package com.example.javaee.serviceimpl;

import com.example.javaee.dao.UserDao;
import com.example.javaee.entity.User;
import com.example.javaee.service.UserService;
import com.example.javaee.utils.Result;
import com.example.javaee.utils.Role;
import com.example.javaee.utils.SensitiveWordFilter;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDao userDao;

    public Map<String, Object> signup(@RequestBody Map<String, String> loginInfo) {
        String username = loginInfo.get("username");
        String password = loginInfo.get("password");
        String role = loginInfo.get("role");

        if(username == null || username.isEmpty())
            return Result.fail("用户名不能为空" , false);

        if(password == null || password.isEmpty())
            return Result.fail("密码不能为空" , false);

        if(role == null || role.isEmpty())
            return Result.fail("用户角色值不能为空" , false);

        SensitiveWordFilter filter = new SensitiveWordFilter();// 加载敏感词库
        Set<String> set = filter.getSensitiveWord(username, 1);// 比对敏感词
        if (set.size() > 0)
            return Result.fail("用户名包含敏感词" , false);

        if(userDao.findByUsername(username) != null)
            return Result.fail("用户名重复" , false);

        try
        {
            Role userRole = Role.valueOf(role);
            userDao.insertNewUser(username , password , userRole);
            return Result.success("注册成功" , true);
        }
        catch (IllegalArgumentException e)
        {
            return Result.fail("用户角色值不在枚举范围内" , false);
        }
    }

    public Map<String, Object> checkLogin(@RequestBody Map<String, String> loginInfo) {
        String username = loginInfo.get("username");
        String password = loginInfo.get("password");

        if(username == null || username.isEmpty())
            return Result.fail("用户名不能为空" , null);

        if(password == null || password.isEmpty())
            return Result.fail("密码不能为空" , null);

        User user = userDao.findByUsername(username);

        if(user == null)
            return Result.fail("该用户名无效" , null);

        if( ! user.getPassword().equals(password))
            return Result.fail("密码错误" , null);

        return Result.success("登录成功" , user);
    }

    @Override
    public Map<String, Object> modifyPwd(Map<String, String> passwordInfo) {
        try
        {
            int user_id = Integer.parseInt(passwordInfo.get("user_id"));
            String old_pwd = passwordInfo.get("old_password");
            String new_pwd = passwordInfo.get("new_password");

            User user = userDao.findByUserId(user_id);

            if(user == null)
                return Result.fail("此用户id无效" , false);

            if(old_pwd == null || old_pwd.isEmpty() || new_pwd == null || new_pwd.isEmpty())
                return Result.fail("密码不能为空" , null);

            if(!user.getPassword().equals(old_pwd))
                return Result.fail("密码错误" , false);

            if(old_pwd.equals(new_pwd))
                return Result.fail("和原有密码一致，请修改" , false);

            return Result.success("修改成功" , true);
        }
        catch (NumberFormatException e)
        {
            return Result.fail("该用户id类型不合法" , false);
        }
    }

    public Map<String, Object> getUserInfo(Map<String, String> userID) {
        try
        {
            int user_id = Integer.parseInt(userID.get("user_id"));
            User user = userDao.findByUserId(user_id);
            return Result.success("查询成功" , user);
        }
        catch (NumberFormatException e)
        {
            return Result.fail("该用户id类型不合法" , null);
        }
    }

    public Map<String, Object> updateUserInfo(Map<String, String> updateInfo) {
        try
        {
            int user_id = Integer.parseInt(updateInfo.get("user_id"));
            String username = updateInfo.get("username");
            String password = updateInfo.get("password");
            String email = updateInfo.get("email");
            String phone = updateInfo.get("phone");
            String name = updateInfo.get("name");

            if(username == null || username.isEmpty())
                return Result.fail("用户名不能为空" , null);

            if(password == null || password.isEmpty())
                return Result.fail("密码不能为空" , null);

            User user = userDao.findByUserId(user_id);
            if(user == null)
                return Result.fail("该用户id无效" , null);

            SensitiveWordFilter filter = new SensitiveWordFilter();
            Set<String> set = filter.getSensitiveWord(username, 1);
            if (set.size() > 0)
                return Result.fail("用户名包含敏感词" , false);

            User repeatUser = userDao.findByUsername(username);
            if(repeatUser != null && repeatUser.getUser_id() != user_id)
                return Result.fail("用户名重复" , null);

            userDao.updateUserInfo(new User(user.getUser_id(), username , password , user.getRole() , email , phone , name));
            User newUser = userDao.findByUsername(username);
            System.out.println("updateDone");
            return Result.success("修改成功" , newUser);
        }
        catch (NumberFormatException e)
        {
            return Result.fail("该用户id类型不合法" , null);
        }
    }

    public Map<String, Object> findAllUser() {
        return Result.success("查询用户列表成功", userDao.findAll());
    }

    public Map<String, Object> deleteById(Map<String, String> deleteInfo) {
        try
        {
            int user_id = Integer.parseInt(deleteInfo.get("user_id"));
            User user = userDao.findByUserId(user_id);
            if(user == null)
                return Result.fail("该用户id无效" , null);

            return Result.success("删除成功" , userDao.deleteById(user_id)) ;
        }
        catch (NumberFormatException e)
        {
            return Result.fail("该用户id类型不合法" , null);
        }
    }

}
