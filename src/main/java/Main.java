import models.Employee;
import models.Office;
import models.Tasks;
import services.AppService;
import utils.HibernateSessionFactoryUtil;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        AppService service = new AppService();

        // added first employee, office and task
        Office office1 = new Office("Dalnaya", "Dalnaya 39");
        Employee employee1 = new Employee("Elena", "boss", 500000);
        Tasks task1 = new Tasks("rate code", "23.06.2023");
        office1.addEmployee(employee1);
        employee1.addTask(task1);
        service.createOffice(office1);

        // added second employee, office and task
        Office office2 = new Office("Lukjanenko", "Lukjanenko 101");
        Employee employee2 = new Employee("Igor", "engineer", 100000);
        Tasks task2 = new Tasks("finish app", "23.06.2023");
        office2.addEmployee(employee2);
        employee2.addTask(task2);
        service.createOffice(office2);

        //added third task for both
        Tasks task3 = new Tasks("chill", "26.03.2023");
        task3.addEmployee(employee2);
        task3.addEmployee(employee1);
        service.createTask(task3);

        //find all tasks and associated employees
        service.findAllTasks();


        HibernateSessionFactoryUtil.getSessionFactory().close();

    }
}
