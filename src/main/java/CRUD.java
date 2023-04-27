import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD {

    private static String INSERT_EMPLOYEE = "INSERT INTO employee (name, position, salary, office_id) VALUES (?, ?, ?, ?);";
    private static String UPDATE_EMPLOYEE = "UPDATE employee SET position = ?, salary = ? WHERE id = ?";
    private static String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id = ?";

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


    public static List<Employee> getEmployeeData(String query) {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            getAllData(employees, preparedStatement);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employees;
    }


    public static List<Rates> getRatesData(String query){
        List<Rates> rates = new ArrayList<>();

        try (Connection connection = DBConnect.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
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


    public static List<Employee> insertEmployee(Employee employee) {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPosition());
            preparedStatement.setInt(3, employee.getSalary());
            preparedStatement.setInt(4, employee.getOfficeId());
            preparedStatement.executeUpdate();

            PreparedStatement allEmployee = connection.prepareStatement("SELECT * FROM employee, office WHERE employee.office_id = office.id");
            getAllData(employees, allEmployee);
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

            PreparedStatement allEmployees = connection.prepareStatement("SELECT * FROM employee, office WHERE employee.office_id = office.id AND employee.id =1");
            getAllData(updateEmployees, allEmployees);
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

            PreparedStatement allemployees = connection.prepareStatement("SELECT * FROM employee, office WHERE employee.office_id = office.id");
            getAllData(deleteEmployees, allemployees);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return deleteEmployees;
    }
}