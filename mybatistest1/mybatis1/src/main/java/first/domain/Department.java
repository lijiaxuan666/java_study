package first.domain;

import java.io.Serializable;
import java.util.List;

public class Department implements Serializable {
    private Integer id;
    private String dep_name;
    private String dep_locationion;

    //一对多关系映射：主表实体应该包含从表实体的集合引用----一对多使用
    private List<Employee> list;

    public List<Employee> getList() {
        return list;
    }

    public void setList(List<Employee> list) {
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public String getDep_name() {
        return dep_name;
    }

    public String getDep_locationion() {
        return dep_locationion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }

    public void setDep_locationion(String dep_locationion) {
        this.dep_locationion = dep_locationion;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", dep_name='" + dep_name + '\'' +
                ", dep_locationion='" + dep_locationion + '\'' +
                '}';
    }
}
