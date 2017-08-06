package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionBuilder {

    public static final String url = "jdbc:mysql://localhost:3306/bmurlgrabberdb";
    public static final String user = "root";
    public static final String password = "";
    public static final String databaseName = "bmurlgrabberdb";
    public static final String descriptionTableName = "bmurlgrabberdb";
    public static final String contentTableName = "bmurlgrabberdb";



    public static Connection createConnectionToDB () {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;

    }

}
