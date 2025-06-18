package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class aivenConnection {
    private static final String URL = "jdbc:mysql://avnadmin:AVNS_Mx60RHDvrs0lTAODxLE@mysql-3c1fff18-phantruc314-a2a8.e.aivencloud.com:16005/defaultdb?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_Mx60RHDvrs0lTAODxLE";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Không tìm thấy JDBC Driver", e);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kết nối CSDL: " + e.getMessage(), e);
        }
    }
}
