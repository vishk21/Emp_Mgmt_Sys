package org.example.dao;

import org.example.model.Employee;
import org.example.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeDAO {
    private static final Logger logger = LogManager.getLogger(EmployeeDAO.class);

    public void addEmployee(Employee employee) {
        String query = "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getPosition());
            statement.setDouble(3, employee.getSalary());
            statement.executeUpdate();
            logger.info("Employee added: " + employee);
        } catch (SQLException e) {
            logger.error("Error adding employee: ", e);
            throw new RuntimeException("Error adding employee.", e);
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setPosition(resultSet.getString("position"));
                employee.setSalary(resultSet.getDouble("salary"));
                employees.add(employee);
            }
            logger.info("Retrieved all employees.");
        } catch (SQLException e) {
            logger.error("Error retrieving employees: ", e);
            throw new RuntimeException("Error retrieving employees.", e);
        }
        return employees;
    }

    public void updateEmployee(Employee employee) {
        String query = "UPDATE employees SET name = ?, position = ?, salary = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getPosition());
            statement.setDouble(3, employee.getSalary());
            statement.setInt(4, employee.getId());
            statement.executeUpdate();
            logger.info("Employee updated: " + employee);
        } catch (SQLException e) {
            logger.error("Error updating employee: ", e);
            throw new RuntimeException("Error updating employee.", e);
        }
    }

    public void deleteEmployee(int id) {
        String query = "DELETE FROM employees WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            logger.info("Employee deleted with ID: " + id);
        } catch (SQLException e) {
            logger.error("Error deleting employee: ", e);
            throw new RuntimeException("Error deleting employee.", e);
        }
    }

    public Employee getEmployeeById(int id) {
        String query = "SELECT * FROM employees WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setPosition(resultSet.getString("position"));
                employee.setSalary(resultSet.getDouble("salary"));
                logger.info("Employee retrieved: " + employee);
                return employee;
            } else {
                logger.warn("No employee found with ID: " + id);
                return null;
            }
        } catch (SQLException e) {
            logger.error("Error retrieving employee by ID: ", e);
            throw new RuntimeException("Error retrieving employee by ID.", e);
        }
    }
}
