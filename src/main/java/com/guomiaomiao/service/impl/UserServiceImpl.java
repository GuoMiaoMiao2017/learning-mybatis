package com.guomiaomiao.service.impl;

import com.guomiaomiao.pojo.User;
import com.guomiaomiao.service.IUserService;
import org.springframework.stereotype.Component;

/**
 * Created by 15295 on 2018/5/24.
 */
@Component
public class UserServiceImpl implements IUserService {
    public User getUser(User user) {
        System.out.println("我是一条测试语句");
        return user;
    }
}
