package com.example.springboot_01_start.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "people")
//这个注解表明他是一个配置，上面会爆红，但不影响使用,可以添加一个spring-boot-configuration-processor依赖
//它将配置文件中的每一个属性值都映射到这个文件中
//它可以通过参数将yaml文件中的对象与这个类绑定起来
public class People {
    private String name;
    private Integer age;
    private Date birth;
    private List<Object> hobby;
    private Map<String,Object> maps;
    private Dog dog;

    public People() {
    }
    public People(String name, Integer age, Date birth, List<Object> hobby, Map<String, Object> maps) {
        this.name = name;
        this.age = age;
        this.birth = birth;
        this.hobby = hobby;
        this.maps = maps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public List<Object> getHobby() {
        return hobby;
    }

    public void setHobby(List<Object> hobby) {
        this.hobby = hobby;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birth=" + birth +
                ", hobby=" + hobby +
                ", maps=" + maps +
                '}';
    }
}
