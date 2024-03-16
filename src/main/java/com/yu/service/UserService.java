package com.yu.service;

import com.yu.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.utils.Result;

/**
* @author Garfi
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-03-17 03:30:01
*/
public interface UserService extends IService<User> {

    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result regist(User user);

    Result checkLogin(String token);
}
