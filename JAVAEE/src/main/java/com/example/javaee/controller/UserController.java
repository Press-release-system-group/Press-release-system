package com.example.javaee.controller;

import com.example.javaee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@RequestMapping({"/user"})
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    @ResponseBody
    public Map<String, Object> signup(@RequestBody Map<String, String> signupInfo) {
        return userService.signup(signupInfo);
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody Map<String, String> loginInfo) {
// 这块地方需要返回一个token给前端，但现在异常没写好，所以还没写
        return userService.checkLogin(loginInfo);
    }
    @PutMapping("/modifyPwd")
    @ResponseBody
    public Map<String, Object> modifyPwd(@RequestBody Map<String, String> passwordInfo) {
        return userService.modifyPwd(passwordInfo);
    }


    @GetMapping("/getUserInfo")
    @ResponseBody
    public Map<String, Object> getUserInfo(@RequestBody Map<String, String> userID) {
        return userService.getUserInfo(userID);
    }

    @PutMapping("/updateUserInfo")
    @ResponseBody
    public Map<String, Object> updateUserInfo(@RequestBody Map<String, String> updateInfo) {
        return userService.updateUserInfo(updateInfo);
    }

    @GetMapping("/findAllUser")
    @ResponseBody
    public Map<String, Object> findAllUser() {
        return userService.findAllUser();
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestBody Map<String, String> deleteInfo) {
        return userService.deleteById(deleteInfo);
    }

}
