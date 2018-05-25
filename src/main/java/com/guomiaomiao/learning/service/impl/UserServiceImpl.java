package com.guomiaomiao.learning.service.impl;

import com.guomiaomiao.learning.pojo.User;
import com.guomiaomiao.learning.service.IUserService;
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
