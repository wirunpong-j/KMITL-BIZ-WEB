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
public class Product {
    private int product_id;
    private String product_name;
    private int group_id;

    public Product() {
        
    }
    
    public Product(int product_id) {
        this.product_id = product_id;
    }

    public Product(String product_name) {
        this.product_name = product_name;
    }

    public Product(int product_id, String product_name) {
        this.product_id = product_id;
        this.product_name = product_name;
    }

    public Product(int product_id, String product_name, int group_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.group_id = group_id;
    }
    
    public void addToDB() {
        Connection conn = null;
        try {
            conn = (Connection) Constant.getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO product(product_name) VALUES(?)");
            pstmt.setString(1, product_name);
            pstmt.executeUpdate();
            pstmt.close();
            
            pstmt = conn.prepareStatement("SELECT * FROM product WHERE product_name = ?");
            pstmt.setString(1, product_name);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.product_id = rs.getInt("product_id");
            }
            pstmt.close();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
    }
    
    public boolean addProduct() {
        boolean status = true;
        Connection conn = null;
        
        try {
            conn = (Connection) Constant.getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO product(product_name, group_id) VALUES(?,?)");
            pstmt.setString(1, this.product_name);
            pstmt.setInt(2, this.group_id);
            pstmt.executeUpdate();
            pstmt.close();
            
            pstmt = conn.prepareStatement("SELECT * FROM product WHERE product_name = ? AND group_id = ?");
            pstmt.setString(1, this.product_name);
            pstmt.setInt(2, this.group_id);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.product_id = rs.getInt("product_id");
            }
            pstmt.close();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            status = false;
            
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        
        return status;
    }
    
    public boolean changeGroup() {
        boolean status = true;
        Connection conn = null;
        
        try {
            conn = (Connection) Constant.getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE product SET group_id = ? WHERE product_id = ?");
            pstmt.setInt(1, this.group_id);
            pstmt.setInt(2, this.product_id);
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            status = false;
            
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        
        return status;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    
    
    
}
