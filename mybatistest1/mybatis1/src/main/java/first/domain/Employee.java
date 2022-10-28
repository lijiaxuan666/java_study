package first.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Employee implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private Integer dep_id;
    private Department department;//主表中应封装一个从表的对象--一对一使用

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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

    public Integer getDep_id() {
        return dep_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDep_id(Integer dep_id) {
        this.dep_id = dep_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dep_id=" + dep_id +
                '}';
    }
}
