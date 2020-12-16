package com.yxkj.service.impl;

import com.yxkj.dao.UserDao;
import com.yxkj.pojo.User;
import com.yxkj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserDao userDao;

    public User getUserBy(int uid) {

        return userDao.findById(uid).get();
    }
}
