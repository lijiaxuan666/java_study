package com.example.admin.mapper;

import com.example.admin.pojo.User;
import com.example.admin.pojo.UserImg;
import com.example.admin.pojo.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Select("select password from user where username=#{name}")
    public String findUser(String name);

    @Insert("insert into user values(null,#{username},#{password})")
    public boolean saveUser(User user);

    @Insert("insert into userinfo values(#{username},#{name},#{email},#{sex},#{phonenumber},#{birthday},#{address},#{insert_time})")
    public boolean saveUserInfo(UserInfo userInfo);

    @Insert("insert into userimg (username,img) value(#{username},#{img})")
    public void saveImg(UserImg userImg);

    @Select("select img from userimg where username=#{name}")
    public byte[] findImg(String name);
}
