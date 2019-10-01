package main;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class RunQuery {

    public final String URL = "jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public final String USER = "admin";
    public final String PASS = "admin";
    public DatabaseConnect connector;
    public Connection conn;

    public RunQuery() {
        this.connector = new DatabaseConnect(URL, USER, PASS);
        this.conn = connector.getConnection();
    }

    public boolean insertRecord(String BookName, String DOI, String DOR) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO booktracker (BookName,DOI,DOR) VALUES (?,?,?)");
            ps.setString(1, BookName);
            ps.setString(2, DOI);
            ps.setString(3, DOR);
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteRecord(int id) {
        this.conn = connector.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM booktracker WHERE id= ?");
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            if (i >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ResultSet displayRecord() {
        this.conn = connector.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM booktracker");
            return rs;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
    
    public boolean updateRow(int id, String DOR){
        this.conn = connector.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE booktracker SET DOR= ? WHERE id= ?");
            ps.setInt(2, id);
            ps.setString(1, DOR);
            int i = ps.executeUpdate();
            if (i >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

