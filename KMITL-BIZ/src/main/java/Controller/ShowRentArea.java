/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Listener.Constant;
import Model.Customer;
import Model.Product;
import Model.Zone;
import java.io.IOException;
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
@WebServlet(name = "ShowRentArea", urlPatterns = {"/ShowRentArea"})
public class ShowRentArea extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
//        HttpSession session = request.getSession();
//        HashMap<String, Object> allRentDate = (HashMap<String, Object>) session.getAttribute("allRentDate");
//        String selectRent = request.getParameter("selectRent");
//        
//        ZonedDateTime thursday = null;
//        ArrayList<ZonedDateTime> thursdayOnMonth = null;
//        String query = "";
//        // set price area
//        Customer cust = (Customer) session.getAttribute("customer");
//        int count = 0;
//        
//        if (selectRent.equals("R1") || selectRent.equals("R2")) {
//            thursday = (ZonedDateTime) allRentDate.get(selectRent);
//            query = "SELECT * FROM `order` JOIN zone USING (order_id) JOIN order_product USING (order_id) JOIN product USING (product_id) "
//                    + "WHERE DAY(rent_date) = "+ thursday.getDayOfMonth() +" AND MONTH(rent_date) = "+ thursday.getMonthValue() +" AND YEAR(rent_date) = "+ thursday.getYear() +" ORDER BY zone_id;";
//            
//            switch (cust.getCust_type()) {
//                case "STUDENT": cust.setPrice(100); break;
//                case "STAFF": cust.setPrice(160); break;
//                case "OUTSIDER": cust.setPrice(200); break;
//            }
//            
//            count = 1;
//
//            
//        } else {
//            thursdayOnMonth = (ArrayList<ZonedDateTime>) allRentDate.get(selectRent);
//            query = "SELECT * FROM `order` JOIN zone USING (order_id) JOIN order_product USING (order_id) JOIN product USING (product_id) "
//                    + "WHERE MONTH(rent_date) = "+ thursdayOnMonth.get(0).getMonthValue() +" ORDER BY zone_id;";
//            
//            switch (cust.getCust_type()) {
//                case "STUDENT": cust.setPrice(90); break;
//                case "STAFF": cust.setPrice(150); break;
//                case "OUTSIDER": cust.setPrice(190); break;
//            }
//            
//            count = thursdayOnMonth.size();
//            
//        }
        
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
        
//        // set Area
//        HashMap<String,Zone> allZone = new HashMap<>();
//        try {
//            conn = (Connection) Constant.getConnection();
//            pstmt = conn.prepareStatement(query);
//            
//            rs = pstmt.executeQuery();
//            
//            Zone zone;
//            while (rs.next()) {
//                if (!allZone.containsKey(rs.getString("zone_id"))) {
//                    zone = new Zone(rs.getString("zone_id"), rs.getInt("order_id"));
//                    zone.setHasProduct(false);
//                    zone.getAllProductID().add(rs.getInt("product_id"));
//                } else {
//                    zone = allZone.get(rs.getString("zone_id"));
//                    zone.getAllProductID().add(rs.getInt("product_id"));
//                }               
//                allZone.put(rs.getString("zone_id"), zone);
//            }
//
//            rs.close();
//            pstmt.close();
//
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        } finally {
//            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
//        }
        
//        for (String zoneID: allZone.keySet()) {
//            for (Product product: cust.getAllProduct()) {
//                for (int id: allZone.get(zoneID).getAllProductID()) {
//                    if (product.getProduct_id() == id) {
//                        allZone.get(zoneID).setHasProduct(true);
//                        break;
//                    }
//                }
//            }
//        }
        
//        session.setAttribute("count", count);
//        session.setAttribute("allZone", allZone);
//        session.setAttribute("typeRent", selectRent);
//        session.setAttribute("statusShow", "true");
//        session.setAttribute("customer", cust);
//        response.sendRedirect("/KMITL-BIZ/index.jsp");
        return;
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
