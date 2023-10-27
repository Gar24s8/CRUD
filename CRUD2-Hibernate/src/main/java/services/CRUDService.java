package services;

import dao.DAO;
import dao.DAOImpl;
import models.Employee;
import models.Office;
import models.Task;

import java.util.List;

public class CRUDService {

    public CRUDService() {
    }

    private final DAO dao = new DAOImpl();

    public Office findOfficeById(int id) {
        return dao.findOfficeById(id);
    }

    public Employee findEmployeeById(int id) {
        return dao.findEmployeeById(id);
    }

    public Task findTaskById(int id) {
        return dao.findTasksBytId(id);
    }

    public List<Employee> findAllEmployees() {
        return dao.findAllEmployees();
    }

    public List<Office> findAllOffices() {
        return dao.findAllOffices();
    }

    public List<Task> findAllTasks() {
        return dao.findAllTasks();
    }

    public boolean createEmployee(Employee employee) {
        return dao.createEmployee(employee);
    }

    public void createOffice(Office office) {
        dao.createOffice(office);
    }

    public void createTask(Task task) {
        dao.createTask(task);
    }

    public boolean updateEmployee(Employee employee) {
        return dao.updateEmployee(employee);
    }

    public void updateOffice(Office office) {
        dao.updateOffice(office);
    }

    public void updateTask(Task task) {
        dao.updateTask(task);
    }

    public void deleteEmployee(Employee employee) {
        dao.deleteEmployee(employee);
    }

    public void deleteOffice(Office office) {
        dao.deleteOffice(office);
    }

    public void deleteTask(Task task) {
        dao.deleteTask(task);
    }

    public boolean deleteEmployeeById(int id) {
       return dao.deleteEmployeeById(id);
    }
}
