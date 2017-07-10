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
public class Product {
    private int product_id;
    private String product_name;
    private Connection conn;

    public Product() {
        
    }

    public Product(Connection conn, String product_name) {
        this.conn = conn;
        this.product_name = product_name;
    }

    public Product(Connection conn, int product_id, String product_name) {
        this.conn = conn;
        this.product_id = product_id;
        this.product_name = product_name;
    }
    
    public void addToDB() {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO KMITLBIZ.PRODUCT(product_name) VALUES(?)");
            pstmt.setString(1, product_name);
            pstmt.executeUpdate();
            pstmt.close();
            
            pstmt = conn.prepareStatement("SELECT * FROM KMITLBIZ.PRODUCT WHERE product_name = ?");
            pstmt.setString(1, product_name);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.product_id = rs.getInt("product_id");
            }
            pstmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
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
    
    
}
