package fr.ece.project.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public final class Database {
    private Database() {}

    private static final String URL = "jdbc:mysql://localhost:3306/minierp?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

