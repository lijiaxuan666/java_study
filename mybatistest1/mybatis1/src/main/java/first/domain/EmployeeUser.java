package first.domain;

public class EmployeeUser extends Employee{
    private String name;
    private String dep_locationion;

    @Override
    public String getName() {
        return name;
    }

    public String getDep_locationion() {
        return dep_locationion;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setDep_locationion(String dep_locationion) {
        this.dep_locationion = dep_locationion;
    }

    @Override
    public String toString() {
        return "EmployeeUser{" +
                "name='" + name + '\'' +
                ", dep_locationion='" + dep_locationion + '\'' +
                '}';
    }
}
