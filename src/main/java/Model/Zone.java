/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Listener.Constant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author BellKunG
 */
public class Zone {
    private String zone_id;
    private int order_id;
    private ArrayList<Integer> allProductID;
    private ArrayList<String> allProductName;
    
    private boolean hasProduct;

    public Zone(String zone_id, int order_id) {
        this.zone_id = zone_id;
        this.order_id = order_id;
        this.allProductID = new ArrayList<>();
        this.allProductName = new ArrayList<>();
    }
    
    
    public void insertZoneToDB() {
        PreparedStatement pstmt;
        Connection conn = null;
        try {
            conn = (Connection) Constant.getConnection();
            pstmt = conn.prepareStatement("INSERT INTO zone VALUES(?,?)");
            pstmt.setString(1, this.zone_id);
            pstmt.setInt(2, this.order_id);
            pstmt.executeUpdate();
            
            pstmt.close();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
    }

    public String getZone_id() {
        return zone_id;
    }

    public void setZone_id(String zone_id) {
        this.zone_id = zone_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public boolean isHasProduct() {
        return hasProduct;
    }

    public void setHasProduct(boolean hasProduct) {
        this.hasProduct = hasProduct;
    }

    public ArrayList<Integer> getAllProductID() {
        return allProductID;
    }

    public void setAllProductID(ArrayList<Integer> allProductID) {
        this.allProductID = allProductID;
    }

    public ArrayList<String> getAllProductName() {
        return allProductName;
    }

    public void setAllProductName(ArrayList<String> allProductName) {
        this.allProductName = allProductName;
    }
    
    
    
    
}
