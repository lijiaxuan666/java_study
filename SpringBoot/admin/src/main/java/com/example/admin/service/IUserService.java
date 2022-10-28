package com.example.admin.service;


import com.example.admin.pojo.User;
import com.example.admin.pojo.UserImg;
import com.example.admin.pojo.UserInfo;

public interface IUserService {
    //登录根据用户名查询密码
    public String findUser(String name);
    //保存账号密码
    public boolean saveUser(User user);
    //保存用户信息
    public boolean saveUserInfo(UserInfo userInfo);
    //保存图片
    public void saveImg(UserImg userImg);
    //读取图片
    public byte[] findImg(String name);
}
