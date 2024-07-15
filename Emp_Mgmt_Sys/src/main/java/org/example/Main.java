package org.example;

import org.example.dao.EmployeeDAO;
import org.example.model.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter position: ");
                    String position = scanner.nextLine();
                    System.out.print("Enter salary: ");
                    double salary = scanner.nextDouble();
                    Employee newEmployee = new Employee();
                    newEmployee.setName(name);
                    newEmployee.setPosition(position);
                    newEmployee.setSalary(salary);
                    employeeDAO.addEmployee(newEmployee);
                    break;

                case 2:
                    List<Employee> employees = employeeDAO.getAllEmployees();
                    for (Employee employee : employees) {
                        System.out.println(employee);
                    }
                    break;

                case 3:
                    System.out.print("Enter employee ID to update: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new position: ");
                    String newPosition = scanner.nextLine();
                    System.out.print("Enter new salary: ");
                    double newSalary = scanner.nextDouble();
                    Employee updatedEmployee = new Employee();
                    updatedEmployee.setId(idToUpdate);
                    updatedEmployee.setName(newName);
                    updatedEmployee.setPosition(newPosition);
                    updatedEmployee.setSalary(newSalary);
                    employeeDAO.updateEmployee(updatedEmployee);
                    break;

                case 4:
                    System.out.print("Enter employee ID to delete: ");
                    int idToDelete = scanner.nextInt();
                    employeeDAO.deleteEmployee(idToDelete);
                    break;

                case 5:
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
