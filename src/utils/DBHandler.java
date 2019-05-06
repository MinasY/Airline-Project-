package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static application.Welcome.*;

/**
 * Database manager for creating connection and executing queries
 * **/
public class DBHandler {
    private static DBHandler dbHandler;
    public static DBHandler getInstance() {
        if(dbHandler==null)
            dbHandler=new DBHandler();
        return dbHandler;
    }
    /**
     * create database connecton
     * @return database connection object
     * **/
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
/**
 * Executes the given SQL statement, which may be an <code>INSERT</code>,
 * <code>UPDATE</code>, or <code>DELETE</code> statement or an
 * SQL statement that returns nothing, such as an SQL DDL statement.
 * **/
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
/**
 * Executes the given SQL statement, which returns a single
 * <code>ResultSet</code> object.
 *<p>
 * @param query an SQL statement to be sent to the database, typically a
 *        static SQL <code>SELECT</code> statement
 * @return a <code>ResultSet</code> object that contains the data produced
 *         by the given query; never <code>null</code>
 **/
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
