package first.dao;

import first.domain.Employee;
import first.domain.EmployeeUser;

import java.util.List;

public interface IEmployeeDao {

    List<Employee> findAll();

    List<EmployeeUser> findSome();
}
