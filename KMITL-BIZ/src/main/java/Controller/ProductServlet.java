/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Listener.Constant;
import Model.Customer;
import Model.MyDateTime;
import Model.Product;
import Model.Zone;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author BellKunG
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String[] listProduct = request.getParameter("product").split(",");
        String customer = request.getParameter("customer");

        HttpSession session = request.getSession();
        
        Connection conn = null;
        PreparedStatement pstmt;
        ResultSet rs;
        
        try (PrintWriter out = response.getWriter()) {
            try {
                conn = (Connection) Constant.getConnection();
                pstmt = conn.prepareStatement("SELECT * FROM customer WHERE cust_id = ?");
                pstmt.setString(1, customer);
                rs = pstmt.executeQuery();
                
                if (!rs.next()) {
                    conn.close();
                    out.println("NOT FOUND");
                    return;
                }
                rs.close();
                
            } catch (Exception ex) {
                ex.printStackTrace();
                
            } finally {
                if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
            }
        }

        Customer cust = new Customer(Integer.parseInt(customer));
        cust.searchCustomerByID();
        for (String product: listProduct) {
            cust.setAllProduct(Integer.parseInt(product.split(":")[0]));
        }
        
        // show rent area
        HashMap<String, Object> allRentDate = (HashMap<String, Object>) session.getAttribute("allRentDate");
        String selectRent = request.getParameter("selectRent");
        
        HashMap<String, Object> priceAreaMap = getPriceArea(allRentDate, selectRent, cust);
        cust = (Customer) priceAreaMap.get("customer");
        conn = null;
        pstmt = null;
        rs = null;
        
        // set Area
        HashMap<String,Zone> allZone = new HashMap<>();
        try {
            conn = (Connection) Constant.getConnection();
            pstmt = conn.prepareStatement((String) priceAreaMap.get("query"));
            
            rs = pstmt.executeQuery();
            
            Zone zone;
            while (rs.next()) {
                if (!allZone.containsKey(rs.getString("zone_id"))) {
                    zone = new Zone(rs.getString("zone_id"), rs.getInt("order_id"));
                    zone.setHasProduct(false);
                    zone.getAllProductID().add(rs.getInt("product_id"));
                } else {
                    zone = allZone.get(rs.getString("zone_id"));
                    zone.getAllProductID().add(rs.getInt("product_id"));
                }               
                allZone.put(rs.getString("zone_id"), zone);
            }

            rs.close();
            pstmt.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        
        for (String zoneID: allZone.keySet()) {
            for (Product product: cust.getAllProduct()) {
                for (int id: allZone.get(zoneID).getAllProductID()) {
                    if (product.getProduct_id() == id) {
                        allZone.get(zoneID).setHasProduct(true);
                        break;
                    }
                }
            }
        }

        session.setAttribute("customer", cust);
        session.setAttribute("status", "RENT");
        session.setAttribute("count", (Integer) priceAreaMap.get("count"));
        session.setAttribute("allZone", allZone);
        session.setAttribute("selectRentText", request.getParameter("selectRentText"));
        return;
    }
    
    private HashMap<String, Object> getPriceArea(HashMap<String, Object> allRentDate, String selectRent, Customer customer) {
        HashMap<String, Object> priceAreaMap = new HashMap<>();
        
        ZonedDateTime thursday = null;
        ArrayList<ZonedDateTime> thursdayOnMonth = null;
        String query = "";
        int count = 0;
        
        if (selectRent.equals("R1") || selectRent.equals("R2")) {
            thursday = (ZonedDateTime) allRentDate.get(selectRent);
            query = "SELECT * FROM `order` JOIN zone USING (order_id) JOIN order_product USING (order_id) JOIN product USING (product_id) "
                    + "WHERE DAY(rent_date) = "+ thursday.getDayOfMonth() +" AND MONTH(rent_date) = "+ thursday.getMonthValue() +" AND YEAR(rent_date) = "+ thursday.getYear() +" ORDER BY zone_id;";
            
            switch (customer.getCust_type()) {
                case "STUDENT": customer.setPrice(100); break;
                case "STAFF": customer.setPrice(160); break;
                case "OUTSIDER": customer.setPrice(200); break;
            }
            
            count = 1;

            
        } else {
            thursdayOnMonth = (ArrayList<ZonedDateTime>) allRentDate.get(selectRent);
            query = "SELECT * FROM `order` JOIN zone USING (order_id) JOIN order_product USING (order_id) JOIN product USING (product_id) "
                    + "WHERE MONTH(rent_date) = "+ thursdayOnMonth.get(0).getMonthValue() +" ORDER BY zone_id;";
            
            switch (customer.getCust_type()) {
                case "STUDENT": customer.setPrice(90); break;
                case "STAFF": customer.setPrice(150); break;
                case "OUTSIDER": customer.setPrice(190); break;
            }
            
            count = thursdayOnMonth.size();
            
        }
        
        priceAreaMap.put("query", query);
        priceAreaMap.put("count", count);
        priceAreaMap.put("customer", customer);
        
        return priceAreaMap;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
