/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Listener.Constant;
import Model.AreaModel;
import Model.Product;
import Model.Staff;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "Authentication", urlPatterns = {"/Authentication"})
public class Authentication extends HttpServlet {

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
        
        String username = "", password = "";
        Staff staff = null;
        HttpSession session = request.getSession();
        
        if (session.getAttribute("staff") == null) {
            username = request.getParameter("username");
            password = request.getParameter("password");
            staff = new Staff(username, password);
        } else {
            staff = (Staff) session.getAttribute("staff");
        }
        
        session.invalidate();
        session = request.getSession();
        
        if (staff.isStaff()) {
            
            ArrayList<Product> allProduct = new ArrayList<>();
            Connection conn = null;
            try {
                conn = (Connection) Constant.getConnection();
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM product");
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    Product pro = new Product(rs.getInt("product_id"), rs.getString("product_name"));
                    allProduct.add(pro);
                }
                
                pstmt.close();
                rs.close();
                
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } finally {
                if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
            }
            
            session.setAttribute("staff", staff);
            session.setAttribute("allProduct", allProduct);
            session.setAttribute("allArea", AreaModel.allArea());
            
            response.sendRedirect("/KMITL-BIZ/index.jsp");
            
        } else {
            response.sendRedirect("/KMITL-BIZ/Login.jsp");
        }
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
