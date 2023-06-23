package dao;

import models.Employee;
import models.Office;
import models.Tasks;

import java.util.List;

public interface DAO {

    Office findOfficeById(int id);

    Employee findEmployeeById(int id);

    Tasks findTasksBytId(int id);

    List<Employee> findAllEmployees();

    List<Office> findAllOffices();

    List<Tasks> findAllTasks();

    void createEmployee(Employee employee);

    void createOffice(Office office);

    void createTask(Tasks tasks);

    void updateEmployee(Employee employee);

    void updateOffice(Office office);

    void updateTask(Tasks tasks);

    void deleteEmployee(Employee employee);

    void deleteOffice(Office office);

    void deleteTask(Tasks tasks);
}
