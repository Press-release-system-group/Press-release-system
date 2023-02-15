package com.example.javaee.controller;

import com.example.javaee.entity.User;
import com.example.javaee.service.UserService;
import com.example.javaee.utils.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

@Api(tags = "这是用户类接口")
public class UserController {

    @Autowired
    UserService userService;



    @PostMapping("/user/signup")
    @ResponseBody
    @ApiOperation(value = "用户注册", notes = "{\n" +
            "    \"username\":\"8\",\n" +
            "    \"password\":\"8\",\n" +
            "    \"role\":\"管理员\"\n" +
            "}")
    //@ApiImplicitParams({@ApiImplicitParam(name = "signupInfo",value ="这里是参数的描述" ,dataType = "Map",defaultValue = "ssssss",paramType = "body")})//    @ApiImplicitParam(name = "signupInfo" , paramType = "body",examples = @Example({
//            @ExampleProperty(value = "{'user':'id'}", mediaType = "application/json"),
//            @ExampleProperty(value = "{'user':'id'}", mediaType = "application/json"),
//            @ExampleProperty(value = "{'user':'id'}", mediaType = "application/json"),
//            @ExampleProperty(value = "{'user':'id'}", mediaType = "application/json")
//    }))
//    @ApiJsonObject(name = "params", value = {
//            @ApiJsonProperty(type = Integer.class,key = "mobile", example = "18614242538", description = "user mobile"),
//            @ApiJsonProperty(type = Integer.class,key = "password", example = "123456", description = "user password"),
//            @ApiJsonProperty(type = String.class,key = "name", example = "", description = "user 姓名"),
//            @ApiJsonProperty(type = Integer.class,key = "page", example = "", description = "当前页"),
//            @ApiJsonProperty(type = Integer.class,key = "rows", example = "15", description = "行数")
//    })

    public Result signup(@RequestBody Map<String, String> signupInfo) {

         userService.signup(signupInfo);

         return new Result<Void>(200,"注册成功");
    }

    @PostMapping("/user/login")
    @ResponseBody
    @ApiOperation(value = "用户登陆", notes = "无需token，但它返回的Header中会携带一个token，前端记得保存好" +
            "\n{\n" +
            "    \"username\":\"n\",\n" +
            "    \"password\":\"n\"\n" +
            "}")
    public Result login(@RequestBody Map<String, String> loginInfo, HttpServletResponse response) {

      User user= userService.checkLogin(loginInfo);

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
    public Result modifyPwd(@RequestBody Map<String, String> passwordInfo) {
        userService.modifyPwd(passwordInfo);
            Result result =new Result(200,"修改成功");
            return result;
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
    public Result<User> updateUserInfo(@RequestBody Map<String, String> updateInfo) {
        return new Result<>(200,"更新成功",userService.updateUserInfo(updateInfo));
    }

    @GetMapping("/admin/getUserInfo")
    @ResponseBody
    @ApiOperation(value = "获取单个用户信息", notes = "\n{\n" +
            "    \"user_id\":2\n" +
            "}")
    public Result<User> getUserInfo(@RequestBody Map<String, String> userID) {
        return new Result(200,"获取成功",userService.getUserInfo(userID));
    }

    @GetMapping("/admin/findAllUser")
    @ResponseBody
    @ApiOperation(value = "获取用户列表", notes = "返回一个列表，包含全部用户的信息，只需Header中带token，无需传参")
    public Result<List<User>> findAllUser() {
        return new Result<>(200,"查找全部成功",userService.findAllUser());
    }

    @DeleteMapping("/admin/delete")
    @ResponseBody
    @ApiOperation(value = "删除用户", notes = "由于各表的依赖，该功能可能引发各类问题，尚未处理，前端可以暂时搁置不接")
    public Result delete(@RequestBody Map<String, String> deleteInfo) {
        int i=userService.deleteById(deleteInfo);
        Result result=new Result();
        if(i==1){
            result.setCode(200);
            result.setMsg("删除成功");
        }else {
            result.setCode(6000);
            result.setMsg("删除失败");
        }
        return result;
    }

}
