/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Listener.Constant;
import Model.AllFormat;
import Model.Customer;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@WebServlet(name = "SearchCustomer", urlPatterns = {"/SearchCustomer"})
public class SearchCustomer extends HttpServlet {

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
        
        String number = (!request.getParameter("search").equals("")) ? request.getParameter("search") : "0";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = (Connection) Constant.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM customer WHERE cust_id = ? OR tel = ? OR citizen_id = ? OR student_id = ?;");
            pstmt.setInt(1, Integer.parseInt(number));
            pstmt.setString(2, number);
            pstmt.setString(3, number);
            pstmt.setString(4, number);
            
            rs = pstmt.executeQuery();
            if (rs.next()) {
                
                Customer cust = new Customer();
                cust.setCust_id(rs.getInt("cust_id"));
                cust.setFullname(rs.getString("fullname"));
                cust.setTel(rs.getString("tel"));
                cust.setCust_type(rs.getString("cust_type"));
                cust.setStudent_id(rs.getString("student_id"));
                cust.setCitizen_id(rs.getString("citizen_id"));
                cust.setVehicle(rs.getString("vehicle"));
                cust.setEmail(rs.getString("email"));
                
                request.setAttribute("cust", cust);
                request.setAttribute("status", "true");
                
            } else {
                request.setAttribute("status", "false");
            }
            
            request.setAttribute("custText", AllFormat.toPadZero(Integer.parseInt(number)));
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        
        RequestDispatcher page = request.getRequestDispatcher("/admin-customer/admin_cust_edit.jsp");
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
