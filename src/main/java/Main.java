import models.Employee;
import models.Office;
import services.Service;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        Service service = new Service();
        Office office = new Office("OP 1", "Krasnaya 1");
        service.saveOffice(office);
        Employee ivan = new Employee("Ivan","engineer 1", 1000);
        office.addEmployee(ivan);
        Employee dmitry = new Employee("Dmitry", "boss", 2000);
        dmitry.setOffice(office);
        office.addEmployee(dmitry);
        service.updateOffice(office);
        office.setOfficeName("OP 666");
        service.updateOffice(office);
    }
}
