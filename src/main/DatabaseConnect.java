package main;

import java.sql.*;
import com.mysql.cj.jdbc.Driver;

public class DatabaseConnect {
    private String URL = "";
    private String USER = "";
    private String PASS = "";
    
    public DatabaseConnect(String URL, String USER, String PASS) {
        this.URL = URL;
        this.USER = USER;
        this.PASS = PASS;
    } 
    
    public Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(this.URL, this.USER, this.PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}
