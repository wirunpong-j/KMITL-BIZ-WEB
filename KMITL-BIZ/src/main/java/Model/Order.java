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
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 *
 * @author BellKunG
 */
public class Order {
    private int order_id;
    private String order_id_str;
    private String order_date;
    private int price;
    private int cust_id;
    private String cust_id_str;
    private String staff_id;
    private String zone_id;
    private int product_id;
    
    private int extra_price;
    private String note;
    private String rent_date;
    private String order_type;
    private ZonedDateTime rent_date_obj;

    public Order() {
    }

    public Order(ZonedDateTime rentDate, int price, int cust_id, String staff_id) {
        this.rent_date_obj = rentDate;
        this.price = price;
        this.cust_id = cust_id;
        this.staff_id = staff_id;
    }
    
    public void addOrder() {
        
        Calendar calendar = Calendar.getInstance();
        PreparedStatement pstmt;
        Connection conn = null;
        ResultSet rs = null;
        
        try {
            conn = (Connection) Constant.getConnection();
            
            pstmt = conn.prepareStatement("INSERT INTO `order`(order_date, price, extra_price, note, rent_date, order_type, cust_id, staff_id) "
                    + "VALUES(?,?,?,?,?,?,?,?)");
            pstmt.setTimestamp(1, new Timestamp(calendar.getTime().getTime()));
            pstmt.setInt(2, this.price);
            pstmt.setInt(3, this.extra_price);
            pstmt.setString(4, this.note);
            pstmt.setString(5, this.rent_date_obj.format(DateTimeFormatter.ISO_LOCAL_DATE));
            pstmt.setString(6, this.order_type);
            pstmt.setInt(7, this.cust_id);
            pstmt.setString(8, this.staff_id);
            pstmt.executeUpdate();
            
            pstmt.close();
            
            pstmt = conn.prepareStatement("SELECT * FROM `order` WHERE cust_id = ? AND rent_date = ? AND order_type = ?;");
            pstmt.setInt(1, this.cust_id);
            pstmt.setString(2, this.rent_date_obj.format(DateTimeFormatter.ISO_LOCAL_DATE));
            pstmt.setString(3, this.order_type);
            
            rs = pstmt.executeQuery();
            if (rs.next()) {
                this.order_id = rs.getInt("order_id");
                this.order_date = rs.getString("order_date");
                this.rent_date = rs.getString("rent_date");
            }
            
            rs.close();
            pstmt.close();
            
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        
    }
    
    public boolean deleteOrder() {
        PreparedStatement pstmt;
        Connection conn = null;
        boolean status = true;
        
        try {
            conn = (Connection) Constant.getConnection();
            pstmt = conn.prepareStatement("DELETE FROM `order` WHERE order_id = ?");
            pstmt.setInt(1, this.order_id);
            
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRent_date() {
        return rent_date;
    }

    public void setRent_date(String rent_date) {
        this.rent_date = rent_date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getExtra_price() {
        return extra_price;
    }

    public void setExtra_price(int extra_price) {
        this.extra_price = extra_price;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getCust_id_str() {
        return cust_id_str;
    }

    public void setCust_id_str(String cust_id_str) {
        this.cust_id_str = cust_id_str;
    }

}
