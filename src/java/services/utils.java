package services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saurabh
 */
public class utils {

    /**
     *
     * @return @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        String hostname = "localhost";
        String port = "3306";
        String dbname = "last";
        String username = "root";
        String password = "";
        String jdbc = "jdbc:mysql://localhost:3306/" + dbname;  //String.format("jdbc:mysql://%s:%s/%s", hostname, port, dbname);
        return DriverManager.getConnection(jdbc, "root", "");
    }
}