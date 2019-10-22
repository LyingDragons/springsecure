package com.mj.controller;

import com.mj.entity.User;
import com.mj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/regist")
    public int regeist(String username,String psw){
        String encode = new BCryptPasswordEncoder().encode(psw);
        int insert = userMapper.insert(username, encode);
        return insert;
    }
}
