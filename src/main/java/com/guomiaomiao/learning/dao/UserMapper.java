package com.guomiaomiao.learning.dao;

import com.guomiaomiao.learning.pojo.User;

import java.util.List;

/**
 * Created by 15295 on 2018/5/23.
 */
public interface UserMapper {
   List<User> selectUserByName(String name);
   int insertUser(User user);
   int deleteUserById(int id);
   int updateUser(User user);
   User getUser(int id);
}
