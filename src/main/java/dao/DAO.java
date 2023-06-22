package dao;

import models.Employee;
import models.Office;

import java.util.List;

public interface DAO {

    Office findOfficeById(int id);

    Employee findEmployeeById(int id);

    List<Employee> findAllEmployees();

    List<Office> findAllOffices();

    void createEmployee(Employee employee);

    void createOffice(Office office);

    void updateEmployee(Employee employee);

    void updateOffice(Office office);

    void deleteEmployee(Employee employee);

    void deleteOffice(Office office);


}
