/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AreaModel;
import Model.Product;
import Model.Staff;
import Model.Zone;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection conn = (Connection) getServletContext().getAttribute("connection");
        HttpSession session = request.getSession();
        
        Staff staff = new Staff(conn, username, password);
        if (staff.isStaff()) {
            
            ArrayList<Product> allProduct = new ArrayList<>();
            try {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM KMITLBIZ.PRODUCT");
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    Product pro = new Product(conn, rs.getInt("product_id"), rs.getString("product_name"));
                    allProduct.add(pro);
                }
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
            HashMap<String,Integer> allZone = new HashMap<String,Integer>();
            for (String[] area: AreaModel.allArea()) {
                for (String a: area) {
                    try {
                        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM KMITLBIZ.ZONE WHERE zone_id = ?");
                        pstmt.setString(1, a);
                        
                        ResultSet rs = pstmt.executeQuery();
                        if (rs.next()) {
                            allZone.put(a, rs.getInt("order_id"));
                        } else {
                            allZone.put(a, 0);
                        }
                        
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                    
                }
            }

            session.setAttribute("staff", staff);
            session.setAttribute("allProduct", allProduct);
            session.setAttribute("allArea", AreaModel.allArea());
            session.setAttribute("allZone", allZone);
            
//            session.setAttribute("area1", AreaModel.area1);
//            session.setAttribute("area2", AreaModel.area2);
//            session.setAttribute("area3", AreaModel.area3);
//            session.setAttribute("area4", AreaModel.area4);
//            session.setAttribute("area5", AreaModel.area5);
//            session.setAttribute("area6", AreaModel.area6);
//            session.setAttribute("area7", AreaModel.area7);
//            session.setAttribute("area8", AreaModel.area8);
//            session.setAttribute("area9", AreaModel.area9);
//            session.setAttribute("area10", AreaModel.area10);
//            session.setAttribute("area11", AreaModel.area11);
            
            
            
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
