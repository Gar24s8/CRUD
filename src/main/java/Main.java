import models.Employee;
import models.Office;
import services.AppService;
import utils.HibernateSessionFactoryUtil;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        AppService service = new AppService();
        Office office = new Office("OP 1", "Krasnaya 1");
        Office office2 =new Office("OP 2", "Severnaya 3");
        Employee ivan = new Employee("Ivan", "engineer 1", 1000);
        Employee dima = new Employee("Dima", "boss", 2000);

        office.addEmployee(ivan);
        service.createOffice(office);
        service.createOffice(office2);
        dima.setOffice(office2);
        service.createEmployee(dima);
        ivan.setSalary(1500);
        ivan.setPosition("engineer 2");
        service.updateEmployee(ivan);

        HibernateSessionFactoryUtil.getSessionFactory().close();

    }
}
