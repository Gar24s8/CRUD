package ru.home.dao;

import ru.home.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private static String dbURL = "jdbc:postgresql://localhost:5432/postgres;INIT=RUNSCRIPT FROM 'classpath:init.sql'";
    private static String dbUserName = "postgres";
    private static String dbPassWord = "postgres";
    private static Connection connection = null;

    private static final String INSERT_EMPLOYEE = "INSERT INTO servletTask" + " (name, position, salary) VALUES "
            + " (?, ?, ?);";
    private static final String SELECT_EMPLOYEE_BY_ID = "SELECT id, name, position, salary FROM servletTask WHERE id = ?";
    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM servletTask";
    private static final String DELETE_EMPLOYEE = "DELETE FROM servletTask WHERE id = ?";
    private static final String UPDATE_EMPLOYEE = "UPDATE servletTask SET name = ?, position = ?, salary = ? WHERE id = ?";

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(dbURL, dbUserName, dbPassWord);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //TODO: insert employee
    public void insertEmployee(Employee employee) throws SQLException {
        System.out.println(INSERT_EMPLOYEE);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPosition());
            preparedStatement.setString(3, employee.getSalary());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }
    }

    //TODO: select employee by id
    public Employee selectEmployee(int id) {
        Employee employee = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String position = rs.getString("position");
                String salary = rs.getString("salary");
                employee = new Employee(id, name, position, salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    //TODO: select all employees
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String position = rs.getString("position");
                String salary = rs.getString("salary");
                employees.add(new Employee(id, name, position, salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    //TODO: update employee
    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean isRowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE)) {
            System.out.println("Updated Employee " + preparedStatement);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPosition());
            preparedStatement.setString(3, employee.getSalary());
            preparedStatement.setInt(4, employee.getId());

            isRowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return isRowUpdated;
    }


    //TODO: delete employee
    public boolean deleteEmployee(int id) throws SQLException {
        boolean isRowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE)) {
            preparedStatement.setInt(1, id);
            isRowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return isRowDeleted;
    }
}