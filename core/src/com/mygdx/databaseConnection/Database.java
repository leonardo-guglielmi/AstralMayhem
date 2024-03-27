package com.mygdx.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Database {

    private static final String url = "jdbc:mysql://localhost:3306/provajdbc";

    private static final String user = "admin";

    private static final String password = "admin";

    public Database() {

    }

    public static Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("Database connection settled");
        return connection;
    }

    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }
}
