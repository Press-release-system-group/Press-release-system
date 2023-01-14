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
        return userService.checkLogin(loginInfo);
    }

    @PutMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Map<String, String> updateInfo) {
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
