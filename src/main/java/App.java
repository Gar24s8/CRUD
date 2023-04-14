import java.util.List;

public class App {

    public static void main(String[] args) {

        List<Rates> rates = CRUD.getRatesData("SELECT * FROM rates");
        System.out.println("Вывод списка должностей с зарплатами: ");
        System.out.println(rates);
        System.out.println();

        List<Employee> employees = CRUD.getEmployeeData("SELECT * from employee, office WHERE employee.office_id = office.id");
        System.out.println("Вывод списка сотрудников: ");
        System.out.println(employees);
        System.out.println();

        Employee employee = new Employee();
        employee.setName("Dmitry Dmitriev");
        employee.setPosition("engineer 3");
        employee.setSalary(1500);
        employee.setOfficeId(1);

        System.out.println("Добавлен новый сотрудник: ");
        System.out.println(CRUD.insertEmployee(employee));

        System.out.println();
        System.out.println("Изменена должность и зарплата сотрудника: ");
        System.out.println(CRUD.updateEmployee(1, "engineer 2", 1200));

        System.out.println();
        System.out.println("Удаление сотрудника с id = 2: ");
        System.out.println(CRUD.deleteEmployees(2));

    }
}
