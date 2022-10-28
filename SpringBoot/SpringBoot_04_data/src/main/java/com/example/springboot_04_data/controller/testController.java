package com.example.springboot_04_data.controller;

import com.example.springboot_04_data.pojo.Student;
import com.example.springboot_04_data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * springboot整合mybatis访问数据库流程
 * ● 导入mybatis官方starter
 * ● 编写mapper接口。标准@Mapper注解
 * ● 编写sql映射文件并绑定mapper接口
 * ● 在application.yaml中指定Mapper配置文件的位置，以及指定全局配置文件的信息 （建议；配置在mybatis.configuration）
 */
@Controller
public class testController {
    @Autowired
    StudentService studentService;

    @ResponseBody
    @RequestMapping("/find")
    public Student findStudent(@RequestParam("id") Integer id) {
        return studentService.getStudentById(id);
    }

    @ResponseBody
    @RequestMapping("/findName")
    public Student findByName(@RequestParam("name") String name) {
        return studentService.findByName(name);
    }

    @RequestMapping("/toinsert")
    public String toinsert() {
        return "register";
    }

    @ResponseBody
    @PostMapping("/insert")
    public String insert(@RequestParam("name") String name,
                         @RequestParam("age") String age,
                         @RequestParam("score") String score,
                         @RequestParam("birthday") String birthday) throws ParseException {
        //获取系统当前时间
        Date insert_time = new Date();
        studentService.insert(name, Integer.parseInt(age), Double.parseDouble(score), new SimpleDateFormat("yyyy-MM-dd").parse(birthday), insert_time);
        return "保存成功";
    }

}
