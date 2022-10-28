package com.example.springboot_04_data.mapper;

import com.example.springboot_04_data.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper//需要加上这个注解才会被扫描进来，不然会报错
//也可以在主启动类上加上@MapperScan("指定扫描的包")注解
public interface StudentMapper {

    //纯配置文件形式
    public Student getStudent(Integer id);

    //纯注解形式,使用注解后就不需要xml配置文件
    @Select("select * from stu where name = #{name}")
    public Student findByName(String name);

    //也可以注解+联合，一般简单sql用注解，复杂sql就可以用配置文件
    public boolean insert(String name, int age, double score, Date birthday,Date insert_time);
}
