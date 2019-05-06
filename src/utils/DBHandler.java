package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static application.Welcome.*;

public class DBHandler {
    private static DBHandler dbHandler;
    public static DBHandler getInstance() {
        if(dbHandler==null)
            dbHandler=new DBHandler();
        return dbHandler;
    }
    public static String createUsersTable=
            "   CREATE TABLE `users` (" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT," +
            "  `userName` varchar(55) NOT NULL unique," +
            "  `password` varchar(55) NOT NULL," +
            "  `securityQuestion`  TEXT NOT NULL," +
            "  `securityanswer`  TEXT NOT NULL," +
            "  `firstName` varchar(55) NOT NULL," +
            "  `lastName` varchar(55) NOT NULL," +
            "  `address` varchar(55) NOT NULL," +
            "  `state`varchar(55) NOT NULL," +
            "  `email` varchar(55) NOT NULL," +
            "  `zip` varchar(55) NOT NULL," +
            "  `ssn` varchar(55) NOT NULL," +
            "  PRIMARY KEY (`id`)" +
            ")";
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(connectionString, dbUserName,dbPassword);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeSelect(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            return st.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
