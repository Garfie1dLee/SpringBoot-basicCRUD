package com.yu.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.pojo.User;
import com.yu.service.UserService;
import com.yu.mapper.UserMapper;
import com.yu.utils.JwtHelper;
import com.yu.utils.MD5Util;
import com.yu.utils.Result;
import com.yu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author Garfi
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-03-17 03:30:01
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;
    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        User login = userMapper.selectOne(wrapper);
        if(null != login)
        {
            user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
            if(login.getUserPwd().equals(user.getUserPwd()))
            {
                String token = jwtHelper.createToken(Long.valueOf(login.getUid()));
                Map map=new HashMap<>();
                map.put("token",token);
                return Result.ok(map);
            }
            else
            {
                return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
            }

        }
        else {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
    }

    @Override
    public Result getUserInfo(String token) {
        if(StringUtils.isEmpty(token))
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        if(jwtHelper.isExpiration(token))
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        Integer uid= Math.toIntExact(jwtHelper.getUserId(token));
        User user = userMapper.selectById(uid);
        if(null!=user)
        {
            user.setUserPwd("");
            Map map=new HashMap<>();
            map.put("loginUser",user);
            return Result.ok(map);
        }
        else
            return Result.build(null,ResultCodeEnum.NOTLOGIN);


    }

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        User user = userMapper.selectOne(wrapper);
        if(null!=user)
        {
            return Result.build(null, 505,"sb");

        }
        else
        {
            return Result.ok(null);
        }
    }

    @Override
    public Result regist(User user) {
        String username=user.getUsername();
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        if(userMapper.selectCount(wrapper)!=0)
        return Result.build(null,ResultCodeEnum.USERNAME_USED);
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        int insert = userMapper.insert(user);
        if(insert>0)
            return Result.ok(null);
        else
            return Result.build(null,ResultCodeEnum.USERNAME_USED);

    }

    @Override
    public Result checkLogin(String token) {
        boolean expiration = jwtHelper.isExpiration(token);
        if(expiration)
        {
            return Result.build(null,504,"expired");
        }
        return Result.ok(null);
    }
}




