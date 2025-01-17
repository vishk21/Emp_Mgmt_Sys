package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root"; 
    private static final String PASSWORD = "User@1234"; 
    private static final Logger logger = LogManager.getLogger(DBConnection.class);

    public static Connection getConnection() throws SQLException {
        try {
            logger.info("Attempting to establish database connection...");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Database connection established.");
            return connection;
        } catch (SQLException e) {
            logger.error("Error establishing database connection: ", e);
            throw e; 
        }
    }
}
