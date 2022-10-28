package com.example.admin.service.impl;

import com.example.admin.mapper.UserDao;
import com.example.admin.pojo.User;
import com.example.admin.pojo.UserImg;
import com.example.admin.pojo.UserInfo;
import com.example.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    UserDao userDao;

    @Override
    public String findUser(String name) {
        return userDao.findUser(name);
    }

    @Override
    public boolean saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public boolean saveUserInfo(UserInfo userInfo) {
        return userDao.saveUserInfo(userInfo);
    }

    @Override
    public void saveImg(UserImg userImg) {
        userDao.saveImg(userImg);
        return;
    }

    @Override
    public byte[] findImg(String name) {
        return userDao.findImg(name);
    }
}
