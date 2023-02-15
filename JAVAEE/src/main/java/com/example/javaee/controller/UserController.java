package com.example.javaee.controller;

import com.example.javaee.entity.User;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.UserService;
import com.example.javaee.utils.JwtUtil;
import com.example.javaee.utils.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/user"})
@Api(tags = "这是用户类接口")
public class UserController {

    @Autowired
    UserService userService;



    @PostMapping("/signup")
    @ResponseBody
@ApiOperation(value = "这是描述用户接口",
notes = "这里是更详细的描述，可以用html语言")

    @ApiImplicitParams({@ApiImplicitParam(name = "signupInfo",value ="这里是参数的描述" )})

//    @ApiImplicitParams({@ApiImplicitParam(name = "signupInfo",value ="这里是参数的描述" ,dataType = "Map"
//            ,defaultValue = "ssssss",paramType = "body")})
    public Result signup(@RequestBody Map<String, String> signupInfo) {

         userService.signup(signupInfo);

         return new Result<Void>(200,"注册成功");
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody Map<String, String> loginInfo, HttpServletResponse response) {
// 这块地方需要返回一个token给前端，但现在异常没写好，所以还没写

      User user= userService.checkLogin(loginInfo);

        Result result=new Result();


            Map<String,Object> map=new HashMap<>();
            map.put("username",user.getUsername());
            map.put("userId",user.getUser_id());
            String token= JwtUtil.generate(map);
            response.setHeader("token",token);
            result.setMsg("登录成功");
            result.setCode(200);
        return result;

    }
    @PutMapping("/modifyPwd")
    @ResponseBody
    public Result modifyPwd(@RequestBody Map<String, String> passwordInfo) {
        userService.modifyPwd(passwordInfo);
            Result result =new Result(200,"修改成功");
            return result;


    }


    @GetMapping("/getUserInfo")
    @ResponseBody
    public Result<User> getUserInfo(@RequestBody Map<String, String> userID) {
        return new Result(200,"获取成功",userService.getUserInfo(userID));
    }

    @PutMapping("/updateUserInfo")
    @ResponseBody
    public Result<User> updateUserInfo(@RequestBody Map<String, String> updateInfo) {
        return new Result<>(200,"更新成功",userService.updateUserInfo(updateInfo));
    }

    @GetMapping("/findAllUser")
    @ResponseBody
    public Result<List<User>> findAllUser() {
        return new Result<>(200,"查找全部成功",userService.findAllUser());
    }

    @DeleteMapping("/delete")
    @ResponseBody
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
