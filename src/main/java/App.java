import java.sql.Connection;
import java.util.List;

public class App {

    public static void main(String[] args) {
        try (Connection connection = DBConnect.getConnection()) {

            List<Rates> rates = CRUD.getRatesData(connection);
            System.out.println("Вывод списка должностей с зарплатами: ");
            System.out.println(rates);
            System.out.println();

            List<Employee> employees = CRUD.getEmployeeData(connection);
            System.out.println("Вывод списка сотрудников с офисами: ");
            System.out.println(employees);
            System.out.println();

            Employee employee = new Employee("Dmitry Dmitriev", "engineer 2", 1200, 1);
            CRUD.insertEmployee(connection, employee);
            System.out.println("Добавлен новый сотрудник: ");
            System.out.println(CRUD.getEmployeeData(connection));

            Employee employee1 = new Employee(1, "engineer 2", 1200);
            CRUD.updateEmployee(connection, employee1);
            System.out.println();
            System.out.println("Изменена должность и зарплата сотрудника с id = 1: ");
            System.out.println(CRUD.getEmployeeData(connection));

            CRUD.deleteEmployees(connection, 2);
            System.out.println();
            System.out.println("Удаление сотрудника с id = 2: ");
            System.out.println(CRUD.getEmployeeData(connection));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}