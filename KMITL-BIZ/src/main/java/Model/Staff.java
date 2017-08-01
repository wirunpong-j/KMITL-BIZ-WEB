/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Listener.Constant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
    public Staff(){}
    
    public Staff(String staff_id){
        this.staff_id = staff_id;
    }
    
    public Staff(String staff_id, String password, String first_name, String last_name, String role){
        this.staff_id = staff_id;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.role = role;
    }

    public Staff(String staff_id, String password) {
        this.staff_id = staff_id;
        this.password = password;
    }
    
    public void searchStaff() {
        Connection conn = null;
        try {
            conn = (Connection) Constant.getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM staff WHERE staff_id = ?");
            pstmt.setString(1, staff_id);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.password = rs.getString("password");
                this.first_name = rs.getString("fname");
                this.last_name = rs.getString("lname");
                this.role = rs.getString("role");
            }
            
            pstmt.close();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
    }
    
    public boolean isStaff() {
        Connection conn = null;
        try {
            conn = (Connection) Constant.getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM staff WHERE staff_id = ? AND password = ?");
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
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        return false;
    }
    
    public boolean isAdmin() {
                Connection conn = null;
        try {
            conn = (Connection) Constant.getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM staff WHERE staff_id = ? AND password = ?");
            pstmt.setString(1, this.staff_id);
            pstmt.setString(2, this.password);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.first_name = rs.getString("fname");
                this.last_name = rs.getString("lname");
                this.role = rs.getString("role");
                
                if(this.role.equals("AD")){
                    return true;
                } else {
                    return false;
                }
                
            } else {
                return false;
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        return false;
    }
    
    public void addStaff() {
        Connection conn = null;
        try {
            conn = (Connection) Constant.getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO staff(staff_id, password, fname, lname, role) "
                    + "VALUES(?,?,?,?,?)");
            pstmt.setString(1, this.staff_id);
            pstmt.setString(2, this.password);
            pstmt.setString(3, this.first_name);
            pstmt.setString(4, this.last_name);
            pstmt.setString(5, this.role);
            
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
    }
    
    public void updateStaff(){
        Connection conn = null;
        try {
            conn = (Connection) Constant.getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE staff "
                    + "SET staff_id = ?, password = ?, fname = ?, lname = ?, role = ? "
                    + "WHERE staff_id = ?");
            pstmt.setString(1, this.staff_id);
            pstmt.setString(2, this.password);
            pstmt.setString(3, this.first_name);
            pstmt.setString(4, this.last_name);
            pstmt.setString(5, this.role);
            pstmt.setString(6, this.staff_id);
            
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
    }
    
    public void deleteStaff(){
        Connection conn = null;
        try {
            conn = (Connection) Constant.getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM staff WHERE staff_id = ?");
            pstmt.setString(1, this.staff_id);
            
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
