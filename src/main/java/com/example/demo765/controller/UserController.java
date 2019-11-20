package com.example.demo765.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo765.entity.Result;
import com.example.demo765.entity.User;
import com.example.demo765.mapper.UserMapper;
import com.sun.org.glassfish.external.statistics.annotations.Reset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserMapper userMapper;
    JSONObject jsonObject = new JSONObject();
    @RequestMapping("/login")
    public Object login(@RequestBody User user){
        String username = user.getUsername();
        System.out.println(username);
        String password = user.getPassword();
        System.out.println(password);
            User user1 = userMapper.findUserByUsernameAndPassword(username,password);
//            System.out.println(user1);
//            jsonObject.put("data",user1);
        if(user1 == null){
            System.out.println("账号密码错误");
            return new Result(400);
        }else{
            return new Result(200);
        }
    }
}
