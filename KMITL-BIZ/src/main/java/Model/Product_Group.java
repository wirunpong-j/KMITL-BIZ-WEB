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
import java.util.ArrayList;

/**
 *
 * @author BellKunG
 */
public class Product_Group {
    private int group_id;
    private String group_name;
    private ArrayList<Product> allProduct = new ArrayList<>();

    public Product_Group(int group_id, String group_name) {
        this.group_id = group_id;
        this.group_name = group_name;
    }
    
    public void listProduct() {
        Connection conn = null;
        try {
            conn = (Connection) Constant.getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM product WHERE group_id = ? ORDER BY product_id");
            pstmt.setInt(1, this.group_id);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product pro = new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getInt("group_id"));
                allProduct.add(pro);
            }
            
            rs.close();
            pstmt.close();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public ArrayList<Product> getAllProduct() {
        return allProduct;
    }

    
    
    
    
    
    
    
    
    
}