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

/**
 *
 * @author BellKunG
 */
public class Customer {
    private int cust_id;
    private String cust_id_str;
    private String fullname;
    private String tel;
    private String cust_type;
    private String student_id;
    private String citizen_id;
    private String vehicle;
    private int product_id;
    
    private Connection conn;

    public Customer(Connection conn, String fullname, String tel, String cust_type, String student_id, String citizen_id, String vehicle) {
        this.conn = conn;
        this.fullname = fullname;
        this.tel = tel;
        this.cust_type = cust_type;
        this.student_id = student_id;
        this.citizen_id = citizen_id;
        this.vehicle = vehicle;
    }

    public Customer(Connection conn, int cust_id, int product_id) {
        this.conn = conn;
        this.cust_id = cust_id;
        this.product_id = product_id;
    }
    
    public void addCustomer() {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO KMITLBIZ.CUSTOMER(fullname, tel, cust_type, student_id, citizen_id, vehicle) "
                    + "VALUES(?,?,?,?,?,?)");
            pstmt.setString(1, this.fullname);
            pstmt.setString(2, this.tel);
            pstmt.setString(3, this.cust_type);
            pstmt.setString(4, this.student_id);
            pstmt.setString(5, this.citizen_id);
            pstmt.setString(6, this.vehicle);
            
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void addProductID() {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE KMITLBIZ.CUSTOMER SET product_id = ? WHERE cust_id = ?");
            pstmt.setInt(1, this.product_id);
            pstmt.setInt(2, this.cust_id);
            
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void searchCustomer() {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM KMITLBIZ.CUSTOMER WHERE fullname = ? AND cust_type = ?");
            pstmt.setString(1, this.fullname);
            pstmt.setString(2, this.cust_type);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.cust_id = rs.getInt("cust_id");
                this.fullname = rs.getString("fullname");
                this.tel = rs.getString("tel");
                this.cust_type = rs.getString("cust_type");
                this.student_id = rs.getString("student_id");
                this.citizen_id = rs.getString("citizen_id");
                this.vehicle = rs.getString("vehicle");
                this.product_id = rs.getInt("product_id");
            }
            
            pstmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void searchCustomerByID() {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM KMITLBIZ.CUSTOMER WHERE cust_id = ?");
            pstmt.setInt(1, this.cust_id);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.cust_id = rs.getInt("cust_id");
                this.fullname = rs.getString("fullname");
                this.tel = rs.getString("tel");
                this.cust_type = rs.getString("cust_type");
                this.student_id = rs.getString("student_id");
                this.citizen_id = rs.getString("citizen_id");
                this.vehicle = rs.getString("vehicle");
                this.product_id = rs.getInt("product_id");
            }
            
            pstmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public String getCust_type_Str() {
        String type = "";
        switch (this.cust_type) {
            case "STUDENT": type = "นักศึกษา"; break;
            case "STAFF": type = "บุคลากรของสถาบัน"; break;
            case "OUTSIDER": type = "บุคคลภายนอก"; break;
        }
        return type;
    }

    public String getCust_id_str() {
        this.cust_id_str = AllFormat.toPadZero(this.cust_id);
        return cust_id_str;
    }

    public void setCust_id_str(String cust_id_str) {
        this.cust_id_str = cust_id_str;
    }
    
    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCust_type() {
        return cust_type;
    }

    public void setCust_type(String cust_type) {
        this.cust_type = cust_type;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getCitizen_id() {
        return citizen_id;
    }

    public void setCitizen_id(String citizen_id) {
        this.citizen_id = citizen_id;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
    
    
    
}
