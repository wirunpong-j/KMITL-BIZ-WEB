/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Listener.Constant;
import Model.AllFormat;
import Model.Customer;
import Model.Order;
import Model.Product;
import Model.Zone;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author BellKunG
 */
@WebServlet(name = "SearchOrder", urlPatterns = {"/SearchOrder"})
public class SearchOrder extends HttpServlet {

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
        
        String cust_id = !request.getParameter("custid").equals("") ? request.getParameter("custid") : "0";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        HashMap<String, Order> allOrder = new HashMap<>();
        ArrayList<String> keyOrder = new ArrayList<>();
        
        try {
            conn = (Connection) Constant.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM customer JOIN `order` USING (cust_id) JOIN order_product USING (order_id) JOIN product USING (product_id) JOIN zone USING (order_id) WHERE cust_id = ? ORDER BY order_id, zone_id, product_id;");
            pstmt.setString(1, cust_id);
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order;
                if (!allOrder.containsKey(rs.getString("order_id"))) {                   
                    order = new Order();
                    order.setOrder_id(rs.getInt("order_id"));
                    order.setOrder_date(rs.getString("order_date"));
                    order.setRent_date(rs.getString("rent_date"));
                    order.setPrice(rs.getInt("price"));
                    order.setExtra_price(rs.getInt("extra_price"));
                    order.setNote(rs.getString("note"));
                    
                    keyOrder.add(rs.getString("order_id"));
                    
                } else {
                    order = allOrder.get(rs.getString("order_id"));
                    
                }
                order.getAllProductName().add(rs.getString("product_name"));
                order.getAllZone().add(rs.getString("zone_id"));
                allOrder.put(rs.getString("order_id"), order);
            }
            
            rs.close();
            pstmt.close();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        
        Customer customer = new Customer(Integer.parseInt(cust_id));
        boolean status;
        if (customer.searchCustomerByID()) {
            status = true;
        } else {
            status = false;
        }
        
        request.setAttribute("allOrder", allOrder);
        request.setAttribute("keyOrder", keyOrder);
        request.setAttribute("status", status);
        request.setAttribute("customer", customer);
        
        RequestDispatcher page = request.getRequestDispatcher("/admin-customer/admin_cust_history.jsp");
        page.forward(request, response);
        
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
