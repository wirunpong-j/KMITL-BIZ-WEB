/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fluke
 */
@WebServlet(name = "UpdateStaff", urlPatterns = {"/UpdateStaff/"})
public class UpdateStaff extends HttpServlet {

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
        
        if (request.getParameter("action").equals("update")) {
            String staff_id = request.getParameter("user");
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String role = request.getParameter("role");
            
            Staff staff = new Staff(staff_id);
            staff.setFirst_name(fname);
            staff.setLast_name(lname);
            staff.setRole(role);
            
            try (PrintWriter out = response.getWriter()) {
                if (staff.updateStaff()) {
                    out.println("SUCCESS");
                } else {
                    out.println("FALIED");
                }
            }
            
            
        } else {
            String staff_id = request.getParameter("user");
            String newPassword = request.getParameter("newPass");
            
            Staff staff = new Staff(staff_id);
            staff.searchStaff();
            
            try (PrintWriter out = response.getWriter()) {
                if (staff.changePassword(newPassword)) {
                    out.println("SUCCESS");
                } else {
                    out.println("FALIED");
                }
            }
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
