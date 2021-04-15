package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // Connect to MySQL
    public static Connection getMySQLConnection() throws SQLException{
        String hostName = "localhost";

        String dbName = "store";
        String userName = "VB";
        String password = "c1c7e8d3";

        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException {


        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useSSL=false";

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        System.out.println(connectionURL);
        return conn;
    }

    // реализуйте настройку соеденения с БД
}
