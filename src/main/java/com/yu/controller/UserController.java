package com.yu.controller;

import com.yu.pojo.User;
import com.yu.service.UserService;
import com.yu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody User user)
    {
        Result data=userService.login(user);
        return data;
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token)
    {
        Result data=userService.getUserInfo(token);
        return data;
    }

    @PostMapping("checkUserName")
    public Result checkUserName(@RequestParam String username)
    {
        Result data=userService.checkUserName(username);
        return data;
    }

    @PostMapping("regist")
    public Result regist(@RequestBody User user)
    {
        Result data=userService.regist(user);
        return data;
    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token)
    {
        Result data=userService.checkLogin(token);
        return data;
    }
}
