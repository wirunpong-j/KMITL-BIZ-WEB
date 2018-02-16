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
public class FetchData {
    public static ArrayList<Product_Group> fetchGroupProduct() {
        ArrayList<Product_Group> allGroupPro = new ArrayList<>();
        Connection conn = null;
        try {
            conn = (Connection) Constant.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM product_group WHERE is_hidden = 0 ORDER BY group_id");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product_Group pGroup = new Product_Group(rs.getInt("group_id"), rs.getString("group_name"));
                pGroup.listProduct();

                allGroupPro.add(pGroup);
            }

            pstmt.close();
            rs.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        
        return allGroupPro;
    }
}
