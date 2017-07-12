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
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author BellKunG
 */
public class Order {
    private int order_id;
    private String order_id_str;
    private String order_date;
    private double price;
    private int cust_id;
    private String staff_id;
    private String zone_id;
    
    private Connection conn;
    
    public Order(Connection conn, int order_id) {
        this.conn = conn;
        this.order_id = order_id;
    }

    public Order(int order_id, String order_date, double price, int cust_id, String staff_id, String zone_id) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.price = price;
        this.cust_id = cust_id;
        this.staff_id = staff_id;
        this.zone_id = zone_id;
    }

    public Order(Connection conn, double price, int cust_id, String staff_id, String zone_id) {
        this.conn = conn;
        this.price = price;
        this.cust_id = cust_id;
        this.staff_id = staff_id;
        this.zone_id = zone_id;
    }
    
    public void addOrder() {
        Calendar calendar = Calendar.getInstance();
        PreparedStatement pstmt;
        try {
            
            pstmt = conn.prepareStatement("INSERT INTO KMITLBIZ.ORDER(order_date, price, cust_id, staff_id) VALUES(?,?,?,?)");
            pstmt.setTimestamp(1, new Timestamp(calendar.getTime().getTime()));
            pstmt.setDouble(2, this.price);
            pstmt.setInt(3, this.cust_id);
            pstmt.setString(4, this.staff_id);
            pstmt.executeUpdate();
            
            pstmt.close();
            
            pstmt = conn.prepareStatement("SELECT * FROM KMITLBIZ.ORDER WHERE cust_id = ?");
            pstmt.setInt(1, this.cust_id);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                this.order_id = rs.getInt("order_id");
                this.order_date = rs.getString("order_date");
                this.price = rs.getDouble("price");
                this.cust_id = rs.getInt("order_id");
                this.staff_id = rs.getString("staff_id");
            }
            
            rs.close();
            pstmt.close();
            
            pstmt = conn.prepareStatement("INSERT INTO KMITLBIZ.ZONE VALUES(?,?)");
            pstmt.setString(1, this.zone_id);
            pstmt.setInt(2, this.order_id);
            pstmt.executeUpdate();
            
            pstmt.close();
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public String getOrder_id_str() {
        this.order_id_str = AllFormat.toPadZero(this.order_id);
        return order_id_str;
    }
    
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getZone_id() {
        return zone_id;
    }

    public void setZone_id(String zone_id) {
        this.zone_id = zone_id;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    
    
    
    
}
