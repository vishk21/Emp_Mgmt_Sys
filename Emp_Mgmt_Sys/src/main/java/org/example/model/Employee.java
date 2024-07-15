package org.example.model;

public class Employee {
        private int id;
        private String name;
        private String position;
        private double salary;

        // Getters and Setters with validation
        public int getId() {
            return id;
        }

        public void setId(int id) {
            if (id <= 0) {
                throw new IllegalArgumentException("ID must be positive.");
            }
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty.");
            }
            this.name = name;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            if (salary < 0) {
                throw new IllegalArgumentException("Salary cannot be negative.");
            }
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee{id=" + id + ", name='" + name + "', position='" + position + "', salary=" + salary + '}';
        }
    }

