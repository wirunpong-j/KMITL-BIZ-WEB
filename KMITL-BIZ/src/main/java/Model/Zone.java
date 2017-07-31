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

/**
 *
 * @author BellKunG
 */
public class Zone {
    private String zone_id;
    private int order_id;
    private int product_id;

    public Zone(String zone_id, int order_id, int pro_id) {
        this.zone_id = zone_id;
        this.order_id = order_id;
        this.product_id = pro_id;
    }
    
    public Zone(String zone_id, int order_id) {
        this.zone_id = zone_id;
        this.order_id = order_id;
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

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

}
