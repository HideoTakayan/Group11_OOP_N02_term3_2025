package com.example.servingwebcontent.database;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class aivenConnection {
    private static final String URL = "jdbc:mysql://avnadmin:AVNS_Mx60RHDvrs0lTAODxLE@mysql-3c1fff18-phantruc314-a2a8.e.aivencloud.com:16005/defaultdb?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_Mx60RHDvrs0lTAODxLE";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in database connection: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}
