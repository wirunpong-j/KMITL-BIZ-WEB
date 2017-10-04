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
public class OrderProduct {
    private int order_id;
    private int product_id;

    public OrderProduct(int order_id, int product_id) {
        this.order_id = order_id;
        this.product_id = product_id;
    }
    
    public boolean addOrderProduct() {
        boolean status = true;
        PreparedStatement pstmt;
        Connection conn = null;
        
        try {
            conn = (Connection) Constant.getConnection();
            pstmt = conn.prepareStatement("INSERT INTO order_product VALUES(?,?);");
            pstmt.setInt(1, this.order_id);
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
    
    
}
