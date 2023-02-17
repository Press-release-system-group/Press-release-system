package com.example.javaee.controller;

import com.example.javaee.entity.User;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.UserService;
import com.example.javaee.utils.*;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller

@Api(tags = "这是用户类接口")
public class UserController {

    @Autowired
    UserService userService;



    @PostMapping("/user/signup")
    @ResponseBody
    @ApiOperation(value = "用户注册", notes = "{\n" +
            "    \"username\":\"周星驰\",\n" +
            "    \"password\":\"周星驰\",\n" +
            "    \"role\":\"普通用户\"\n" +
            "}")
    public Result<Void> signup(@ApiIgnore @RequestBody Map<String, String> signupInfo)
    {
        String username = signupInfo.get("username");
        String password = signupInfo.get("password");
        String role = signupInfo.get("role");

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

        if(userService.usernameExist(username))
            throw new BusinessException(ExceptionEnum.USERNAME_重复);

        try
        {
            Role userRole = Role.valueOf(role);
            userService.signup(username , password , userRole);
        }
        catch (IllegalArgumentException e)
        {
            throw new BusinessException(ExceptionEnum.ROLE_范围外);
        }

        return new Result<>(200,"注册成功");
    }

    @PostMapping("/user/login")
    @ResponseBody
    @ApiOperation(value = "用户登陆", notes = "无需token，但它返回的Header中会携带一个token，前端记得保存好" +
            "\n{\n" +
            "    \"username\":\"n\",\n" +
            "    \"password\":\"n\"\n" +
            "}")

    public Result<User> login(@ApiIgnore @RequestBody Map<String, String> loginInfo, HttpServletResponse response) {

        String username = loginInfo.get("username");
        String password = loginInfo.get("password");

        if(username == null || username.isEmpty())
            throw new BusinessException(ExceptionEnum.USERNAME_空);

        if(password == null || password.isEmpty())
            throw new BusinessException(ExceptionEnum.PASSWORD_空);

        if(!userService.usernameExist(username))
            throw new BusinessException(ExceptionEnum.USERNAME_无效);

        if(!userService.pwdCorrect(username , password))
            throw new BusinessException(ExceptionEnum.PASSWORD_错误);

      User user= userService.checkLogin(username , password);

        Result result=new Result();
        Map<String,Object> map=new HashMap<>();
        map.put("username",user.getUsername());
        map.put("userId",user.getUser_id());
        map.put("role",user.getRole());
        String token= JwtUtil.generate(map);
        response.setHeader("token",token);
        result.setMsg("登录成功");
        result.setCode(200);
        result.setData(user);
        return result;
    }

    @PutMapping("/common/modifyPwd")
    @ResponseBody
    @ApiOperation(value = "修改密码", notes = "注意这里传的是user_id而非username" +
            "\n{\n" +
            "    \"user_id\":3,\n" +
            "    \"old_password\": \"2\",\n" +
            "    \"new_password\": \"2\"\n" +
            "}")
    public Result<Void> modifyPwd(HttpServletRequest request,@ApiIgnore @RequestBody Map<String, String> passwordInfo)
    {
        int user_id = GetUserID(request);
        if(!userService.user_idExist(user_id))
            throw new BusinessException(ExceptionEnum.ID_无效);

        String old_pwd = passwordInfo.get("old_password");
        String new_pwd = passwordInfo.get("new_password");


        if(old_pwd == null || old_pwd.isEmpty() || new_pwd == null || new_pwd.isEmpty())
            throw new BusinessException(ExceptionEnum.PASSWORD_空);

        if(!userService.pwdCorrect(user_id , old_pwd))
            throw new BusinessException(ExceptionEnum.PASSWORD_错误);

        if(old_pwd.equals(new_pwd))
            throw new BusinessException(ExceptionEnum.PASSWORD_重复);

        try
        {
            userService.modifyPwd(user_id, new_pwd);
        }
        catch (NumberFormatException e)
        {
            throw new BusinessException(ExceptionEnum.ID_不合法);
        }
        return new Result<>(200,"修改成功");
    }

    @PutMapping("/common/updateUserInfo")
    @ResponseBody
    @ApiOperation(value = "更新单个用户信息", notes = "用来更新用户名、邮箱、电话、真实姓名" +
            "\n{\n" +
            "    \"user_id\": 2,\n" +
            "    \"username\": \"新用户名\",\n" +
            "    \"email\": \"1234567@qq.com\",\n" +
            "    \"phone\": \"123456789\",\n" +
            "    \"name\": \"丁一\"\n" +
            "}")
    public Result<User> updateUserInfo(HttpServletRequest request,@ApiIgnore @RequestBody Map<String, String> updateInfo)
    {
        try
        {
            int user_id = GetUserID(request);
            if(!userService.user_idExist(user_id))
                throw new BusinessException(ExceptionEnum.ID_无效);

            String username = updateInfo.get("username");
            String email = updateInfo.get("email");
            String phone = updateInfo.get("phone");
            String name = updateInfo.get("name");

            if(username == null || username.isEmpty())
                throw new BusinessException(ExceptionEnum.USERNAME_空);

            SensitiveWordFilter filter = new SensitiveWordFilter();
            Set<String> set = filter.getSensitiveWord(username, 1);
            if (set.size() > 0)
                throw new BusinessException(ExceptionEnum.USERNAME_敏感);

            if(userService.usernameExist(username))
                throw new BusinessException(ExceptionEnum.USERNAME_重复);

            return new Result<>(200,"更新成功",userService.updateUserInfo(user_id, username, email, phone, name));
        }
        catch (NumberFormatException e)
        {
            throw new BusinessException(ExceptionEnum.ID_不合法);
        }
    }

    @GetMapping("/admin/getUserInfo")
    @ResponseBody
    @ApiOperation(value = "获取单个用户信息", notes = "\n{\n" +
            "    \"user_id\":2\n" +
            "}")
    public Result<User> getUserInfo(HttpServletRequest request, @ApiIgnore @RequestBody Map<String, String> userID)
    {
        try
        {
            int user_id = GetUserID(request);
            if(!userService.user_idExist(user_id))
                throw new BusinessException(ExceptionEnum.ID_无效);

            return new Result(200,"获取成功",userService.getUserInfo(user_id));
        }
        catch (NumberFormatException e)
        {
            throw new BusinessException(ExceptionEnum.ID_不合法);
        }
    }

    @GetMapping("/admin/findAllUser")
    @ResponseBody
    @ApiOperation(value = "获取用户列表", notes = "返回一个列表，包含全部用户的信息，只需Header中带token，无需传参")
    public Result<List<User>> findAllUser()
    {
        return new Result<>(200,"查找全部成功",userService.findAllUser());
    }

    @DeleteMapping("/admin/delete")
    @ResponseBody
    @ApiOperation(value = "删除用户", notes = "由于各表的依赖，该功能可能引发各类问题，尚未处理，前端可以暂时搁置不接")
    public Result<Void> delete(HttpServletRequest request, @ApiIgnore @RequestBody Map<String, String> deleteInfo) {

        try
        {
            int user_id = GetUserID(request);
            if(!userService.user_idExist(user_id))
                throw new BusinessException(ExceptionEnum.ID_无效);

            int i = userService.deleteById(user_id);
            Result result = new Result();
            if(i == 1)
            {
                result.setCode(200);
                result.setMsg("删除成功");
                result.setData(true);
            }
            else
            {
                result.setCode(6000);
                result.setMsg("删除失败");
            }
            return result;
        }
        catch (NumberFormatException e)
        {
            throw new BusinessException(ExceptionEnum.ID_不合法);
        }
    }

    public int GetUserID(HttpServletRequest request)
    {
        String token = request.getHeader("token");
        Claims claims= JwtUtil.getClaim(token);
        return (int)claims.get("userId");
    }


}
