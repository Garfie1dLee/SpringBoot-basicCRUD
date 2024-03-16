package com.yu;

import com.yu.mapper.UserMapper;
import com.yu.pojo.User;
import com.yu.utils.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class testne {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;
    @Test
    public void start_test(){
        List<User> users = userMapper.selectList(null);
        System.out.println("users = " + users);
    }

    @Test
    public void test_jwt(){
        String token = jwtHelper.createToken(1L);
        System.out.println("token = " + token);

        int userId= Math.toIntExact(jwtHelper.getUserId(token));
        System.out.println("userId = " + userId);

        boolean expiration = jwtHelper.isExpiration(token);
        System.out.println("expiration = " + expiration);

    }
}
