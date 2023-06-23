package services;

import dao.DAO;
import dao.DAOImpl;
import models.Employee;
import models.Office;
import models.Tasks;

import java.util.List;

public class AppService {

    public AppService() {
    }

    private final DAO dao = new DAOImpl();

    public Office findOfficeById(int id) {
        return dao.findOfficeById(id);
    }

    public Employee findEmployeeById(int id) {
        return dao.findEmployeeById(id);
    }

    public Tasks findTaskById(int id) {
        return dao.findTasksBytId(id);
    }

    public List<Employee> findAllEmployees() {
        return dao.findAllEmployees();
    }

    public List<Office> findAllOffices() {
        return dao.findAllOffices();
    }

    public List<Tasks> findAllTasks() {
        return dao.findAllTasks();
    }

    public void createEmployee(Employee employee) {
        dao.createEmployee(employee);
    }

    public void createOffice(Office office) {
        dao.createOffice(office);
    }

    public void createTask(Tasks tasks) {
        dao.createTask(tasks);
    }

    public void updateEmployee(Employee employee) {
        dao.updateEmployee(employee);
    }

    public void updateOffice(Office office) {
        dao.updateOffice(office);
    }

    public void updateTask(Tasks tasks) {
        dao.updateTask(tasks);
    }


    public void deleteEmployee(Employee employee) {
        dao.deleteEmployee(employee);
    }


    public void deleteOffice(Office office) {
        dao.deleteOffice(office);
    }

    public void deleteTask(Tasks tasks) {
        dao.deleteTask(tasks);
    }

}
