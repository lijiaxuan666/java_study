package first.dao;

import first.domain.Department;

import java.util.List;

public interface IDepartmentDao {
    //一对多查询
    List<Department> findAll();
}
