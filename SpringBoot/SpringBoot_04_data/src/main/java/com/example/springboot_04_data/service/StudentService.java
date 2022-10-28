package com.example.springboot_04_data.service;

import com.example.springboot_04_data.mapper.StudentMapper;
import com.example.springboot_04_data.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    public Student getStudentById(Integer id){
        return studentMapper.getStudent(id);
    }

    public Student findByName(String name){
        return studentMapper.findByName(name);
    }

    public boolean insert(String name, int age, double score, Date birthday, Date insert_time){
        return studentMapper.insert(name,age,score,birthday,insert_time);
    }
}
