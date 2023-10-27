package dao;

import models.Employee;
import models.Office;
import models.Task;

import java.util.List;

public interface DAO {

    Office findOfficeById(int id);

    Employee findEmployeeById(int id);

    Task findTasksBytId(int id);

    List<Employee> findAllEmployees();

    List<Office> findAllOffices();

    List<Task> findAllTasks();

    boolean createEmployee(Employee employee);

    void createOffice(Office office);

    void createTask(Task task);

    boolean updateEmployee(Employee employee);

    void updateOffice(Office office);

    void updateTask(Task task);

    void deleteEmployee(Employee employee);

    void deleteOffice(Office office);

    void deleteTask(Task task);

    boolean deleteEmployeeById(int id);
}
