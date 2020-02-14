package com.example.demo765.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo765.result.Result;
import com.example.demo765.entity.User;
import com.example.demo765.mapper.UserMapper;
import com.example.demo765.result.ResultCode;
import com.example.demo765.result.ResultFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.elasticsearch.Build;
import org.springframework.beans.factory.annotation.Autowired;
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
        String password = user.getPassword();
        Subject subject = SecurityUtils.getSubject();
//        User user1 = userMapper.findUserByUsernameAndPassword(username,password);
//            System.out.println(user1);
//            jsonObject.put("data",user1);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        try{
        subject.login(usernamePasswordToken);
        return ResultFactory.buildSuccessResult(usernamePasswordToken);
        }catch (AuthenticationException e){
            return ResultFactory.buildFailResult("登录失败");
        }
    }
//        if(user1 == null){
//            System.out.println("账号密码错误");
//            return ResultFactory.buildFailResult("登录失败");
//        }else{
//            return ResultFactory.buildResult(ResultCode.SUCCESS,"登录成功");
//        }
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        User userByUsername = userMapper.findUserByUsername(username);
        if (userByUsername != null) {
            String message = "用户名已被使用";
            return ResultFactory.buildFailResult(message);
        }
        // 生成盐,默认长度 16 位
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 设置 hash 算法迭代次数
        int times = 2;
        // 得到 hash 后的密码
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        // 存储用户信息，包括 salt 与 hash 后的密码
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userMapper.insert(user);
        return ResultFactory.buildSuccessResult(user);
    }
}
