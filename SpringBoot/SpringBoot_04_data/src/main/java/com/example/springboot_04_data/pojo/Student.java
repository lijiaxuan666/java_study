package com.example.springboot_04_data.pojo;

import java.sql.Date;

public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private Double score;
    private Date birthday;
    private Date insert_time;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setBrithday(Date brithday) {
        this.birthday = brithday;
    }

    public void setInsert_time(Date insert_time) {
        this.insert_time = insert_time;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Double getScore() {
        return score;
    }

    public Date getBrithday() {
        return birthday;
    }

    public Date getInsert_time() {
        return insert_time;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", brithday=" + birthday +
                ", insert_time=" + insert_time +
                '}';
    }
}
