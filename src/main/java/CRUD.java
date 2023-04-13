import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUD {

    private static String INSERT_EMPLOYEE = "INSERT INTO employee (name, position, salary, office_id) VALUES (?, ?, ?, ?);";
    private static String UPDATE_EMPLOYEE = "UPDATE employee SET position = ?, salary = ? WHERE id = ?";
    private static String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id =?";

    public static List<Employee> getEmployeeData(String query) {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                int salary = resultSet.getInt("salary");
                int officeId = resultSet.getInt("office_id");

                employees.add(new Employee(id, name, position, salary, officeId));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employees;
    }

    public static List<Office> getOfficeData(String query) {
        List<Office> offices = new ArrayList<>();

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String officeName = resultSet.getString("office_name");
                String address = resultSet.getString("address");

                offices.add(new Office(id, officeName, address));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return offices;
    }

    public static List<Employee> insertEmployee(Employee employee) {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPosition());
            preparedStatement.setInt(3, employee.getSalary());
            preparedStatement.setInt(4, employee.getOfficeId());
            preparedStatement.executeUpdate();

            PreparedStatement allEmployee = connection.prepareStatement("SELECT * FROM employee WHERE id = 5");
            ResultSet resultSet = allEmployee.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                int salary = resultSet.getInt("salary");
                int officeId = resultSet.getInt("office_id");

                employees.add(new Employee(id, name, position, salary, officeId));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employees;
    }

    public static List<Employee> updateEmployee(int id, String position, int salary) {
        List<Employee> updateEmployees = new ArrayList<>();

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE)) {

            preparedStatement.setString(1, position);
            preparedStatement.setInt(2, salary);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

            PreparedStatement allEmployees = connection.prepareStatement("SELECT * FROM employee WHERE id =1");
            ResultSet resultSet = allEmployees.executeQuery();

            while (resultSet.next()) {
                int idd = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String position1 = resultSet.getString("position");
                int salary1 = resultSet.getInt("salary");
                int officeId = resultSet.getInt("office_id");

                updateEmployees.add(new Employee(idd, name, position1, salary1, officeId));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return updateEmployees;
    }

    public static List<Employee> deleteEmployees(int id) {
        List<Employee> deleteEmployees = new ArrayList<>();

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            PreparedStatement allemployees = connection.prepareStatement("SELECT * FROM employee");
            ResultSet resultSet = allemployees.executeQuery();

            while (resultSet.next()) {
                int id2 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                int salary = resultSet.getInt("salary");
                int officeId = resultSet.getInt("office_id");

                deleteEmployees.add(new Employee(id2, name, position, salary, officeId));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return deleteEmployees;
    }
}
