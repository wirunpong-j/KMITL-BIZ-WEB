/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Listener.Constant;
import Model.AreaModel;
import Model.Zone;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        
        String selectRent = request.getParameter("selectRent");
        
        HttpSession session = request.getSession();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        // set old Order
        HashMap<Integer,Integer> allOrder = new HashMap<>();
        try {
            conn = (Connection) Constant.getConnection();
            pstmt = conn.prepareStatement("SELECT order_id, product_id FROM customer JOIN `order` USING (cust_id);");

            rs = pstmt.executeQuery();
            while (rs.next()) {
                allOrder.put(rs.getInt(1), rs.getInt(2));
            }

            rs.close();
            pstmt.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        
        // set Area
        HashMap<String,Zone> allZone = new HashMap<>();
        for (String[] area: AreaModel.allArea()) {
            for (String a: area) {
                try {
                    conn = (Connection) Constant.getConnection();
                    pstmt = conn.prepareStatement("SELECT * FROM zone WHERE zone_id = ?");
                    pstmt.setString(1, a);

                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        allZone.put(a, new Zone(a, rs.getInt("order_id"), allOrder.get(rs.getInt("order_id"))));
                    } else {
                        allZone.put(a, new Zone(a, 0, 0));
                    }

                    rs.close();
                    pstmt.close();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
                }
            }
        }
        session.setAttribute("allZone", allZone);
        session.setAttribute("typeRent", selectRent);
        session.setAttribute("statusShow", "true");
        response.sendRedirect("/KMITL-BIZ/index.jsp");
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
