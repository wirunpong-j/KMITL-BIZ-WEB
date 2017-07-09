/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BellKunG
 */
public class Staff {
    private String staff_id;
    private String password;
    private String first_name;
    private String last_name;
    private String role;
    
    private Connection conn;

    public Staff(Connection conn, String staff_id, String password) {
        this.conn = conn;
        this.staff_id = staff_id;
        this.password = password;
    }
    
    public boolean isStaff() {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM KMITLBIZ.STAFF WHERE staff_id = ? AND password = ?");
            pstmt.setString(1, this.staff_id);
            pstmt.setString(2, this.password);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.first_name = rs.getString("fname");
                this.last_name = rs.getString("lname");
                this.role = rs.getString("role");
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
}
