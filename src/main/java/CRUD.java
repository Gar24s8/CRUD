import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD {

    private static void getAllData(List<Employee> employees, PreparedStatement preparedStatement) throws SQLException {

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String position = resultSet.getString("position");
            int salary = resultSet.getInt("salary");
            int officeId = resultSet.getInt("office_id");
            String officeName = resultSet.getString("office_name");
            String address = resultSet.getString("address");

            employees.add(new Employee(id, name, position, salary, officeId, officeName, address));
        }
    }


    public static List<Employee> getEmployeeData(Connection connection) {
        List<Employee> employees = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from employee, office WHERE employee.office_id = office.id")) {
            getAllData(employees, preparedStatement);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employees;
    }


    public static List<Rates> getRatesData(Connection connection) {
        List<Rates> rates = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM rates")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String position = resultSet.getString("position");
                int salary = resultSet.getInt("salary");

                rates.add(new Rates(id, position, salary));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rates;
    }


    public static List<Employee> insertEmployee(Connection connection, Employee employee, PreparedStatement allEmployee) {
        List<Employee> employees = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee (name, position, salary, office_id) VALUES (?, ?, ?, ?);")) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPosition());
            preparedStatement.setInt(3, employee.getSalary());
            preparedStatement.setInt(4, employee.getOfficeId());
            preparedStatement.executeUpdate();

            getAllData(employees, allEmployee);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employees;
    }


    public static List<Employee> updateEmployee(Connection connection, int id, String position, int salary, PreparedStatement allEmployees) {
        List<Employee> updateEmployees = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET position = ?, salary = ? WHERE id = ?")) {

            preparedStatement.setString(1, position);
            preparedStatement.setInt(2, salary);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

            getAllData(updateEmployees, allEmployees);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return updateEmployees;
    }


    public static List<Employee> deleteEmployees(Connection connection, int id, PreparedStatement allEmployees) {
        List<Employee> deleteEmployees = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            getAllData(deleteEmployees, allEmployees);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return deleteEmployees;
    }
}