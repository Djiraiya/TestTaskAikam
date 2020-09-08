package org.example.TestTaskAikam.DB;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static final String user = "postgres";
    private static final String password = "";
    private static final String url = "jdbc:postgresql://localhost:5432/AikamTest";

    public static java.sql.Connection getConnect() {

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
