package services;

import dao.DAO;
import dao.DAOImpl;
import models.Employee;
import models.Office;

import java.util.List;

public class Service {

    public Service() {
    }

    private DAO dao = new DAOImpl();

        public Office findOfficeById(int id) {
            return dao.findOfficeById(id);
        }


        public Employee findEmployeeById(int id) {
            return dao.findEmployeeById(id);
        }


        public List<Employee> findAllEmployees() {
            return dao.findAllEmployees();
        }


        public List<Office> findAllOffices() {
            return dao.findAllOffices();
        }


        public void saveEmployee(Employee employee) {
            dao.saveEmployee(employee);
        }


        public void saveOffice(Office office) {
            dao.saveOffice(office);
        }


        public void updateEmployee(Employee employee) {
            dao.updateEmployee(employee);
        }


        public void updateOffice(Office office) {
            dao.updateOffice(office);
        }


        public void deleteEmployee(Employee employee) {
            dao.deleteEmployee(employee);
        }


        public void deleteOffice(Office office) {
            dao.deleteOffice(office);
        }

}
