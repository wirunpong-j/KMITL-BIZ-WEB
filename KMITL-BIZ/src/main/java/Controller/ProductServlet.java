/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Listener.Constant;
import Model.AreaModel;
import Model.Customer;
import Model.Product;
import Model.Zone;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        
        String product = request.getParameter("product");
        String customer = request.getParameter("customer");

        HttpSession session = request.getSession();
        
        Connection conn = null;
        Product pro = null;
        ArrayList<Product> allProduct = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        
        try {
            conn = (Connection) Constant.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM product WHERE product_name = ?");
            pstmt.setString(1, product);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                pro = new Product(rs.getInt("product_id"), rs.getString("product_name"));
            } else {
                pro = new Product(product);
                pro.addToDB();
            }
            
            pstmt.close();
            
            pstmt = conn.prepareStatement("SELECT * FROM product");
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Product p = new Product(rs.getInt("product_id"), rs.getString("product_name"));
                allProduct.add(p);
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }

        Customer cust = new Customer(Integer.parseInt(customer), pro.getProduct_id());
        cust.addProductID();
        cust.searchCustomerByID();
        
        
        ArrayList<String> allRentType = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        session.setAttribute("allRentType", allRentType);
        
        LocalDateTime currentTime = LocalDateTime.now();
   
        ZoneId id = ZoneId.of("Asia/Bangkok");
        ZonedDateTime zonedTime = currentTime.atZone(id);
        Month thisMonth = zonedTime.getMonth();
        Month nextMonth = thisMonth.plus(1);

        System.out.println(thisMonth);
        System.out.println(nextMonth);

        ZonedDateTime thisThursday = zonedTime;
        while (thisThursday.getDayOfWeek() != DayOfWeek.THURSDAY) {
            thisThursday = thisThursday.plusDays(1);
        }

        ZonedDateTime nextThursday = thisThursday.plusDays(1);
        while (nextThursday.getDayOfWeek() != DayOfWeek.THURSDAY) {
            nextThursday = nextThursday.plusDays(1);
        }


        System.out.println(thisThursday);
        System.out.println(nextThursday);
        
//        // set old Order
//        HashMap<Integer,Integer> allOrder = new HashMap<>();
//        try {
//            conn = (Connection) Constant.dataSource.getConnection();
//            pstmt = conn.prepareStatement("SELECT order_id, product_id FROM KMITLBIZ.CUSTOMER JOIN KMITLBIZ.`ORDER` USING (cust_id);");
//
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                allOrder.put(rs.getInt(1), rs.getInt(2));
//            }
//
//            rs.close();
//            pstmt.close();
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        } finally {
//            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
//        }
//        
//        // set Area
//        HashMap<String,Zone> allZone = new HashMap<>();
//        for (String[] area: AreaModel.allArea()) {
//            for (String a: area) {
//                try {
//                    conn = (Connection) Constant.dataSource.getConnection();
//                    pstmt = conn.prepareStatement("SELECT * FROM KMITLBIZ.ZONE WHERE zone_id = ?");
//                    pstmt.setString(1, a);
//
//                    rs = pstmt.executeQuery();
//                    if (rs.next()) {
//                        allZone.put(a, new Zone(a, rs.getInt("order_id"), allOrder.get(rs.getInt("order_id"))));
//                    } else {
//                        allZone.put(a, new Zone(a, 0, 0));
//                    }
//
//                    rs.close();
//                    pstmt.close();
//
//                } catch (SQLException ex) {
//                    System.out.println(ex.getMessage());
//                } finally {
//                    if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
//                }
//            }
//        }
        
        session.setAttribute("product", pro);
        session.setAttribute("customer", cust);
        session.setAttribute("status", "RENT");
        session.setAttribute("allProduct", allProduct);
//        session.setAttribute("allZone", allZone);
        
        response.sendRedirect("/KMITL-BIZ/index.jsp");
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
