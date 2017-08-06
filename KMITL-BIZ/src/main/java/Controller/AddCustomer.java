/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Listener.Constant;
import Model.Customer;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fluke
 */
@WebServlet(name = "AddCustomer", urlPatterns = {"/AddCustomer"})
public class AddCustomer extends HttpServlet {

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
        
        String fullname = request.getParameter("fullname");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        String plate = request.getParameter("plate");
        String vehicle = (plate.equals("")) ? plate : "";
        String cust_type = request.getParameter("cust_type");
        
        
        
        Customer cust = new Customer();
        cust.setFullname(fullname);
        cust.setTel(tel);
        cust.setEmail(email);
        cust.setVehicle(vehicle);
        cust.setCust_type(cust_type);
        
        if (cust_type.equals("STUDENT")) {
            String student_id = request.getParameter("studentid");
            cust.setStudent_id(student_id);
            cust.setCitizen_id("");
        } else {
            String citizen_id = request.getParameter("citizenid");
            cust.setStudent_id("");
            cust.setCitizen_id(citizen_id);
        }
        
        try (PrintWriter out = response.getWriter()) {
            if (cust.addCustomer()) {
                out.println(cust.getCust_id_str());
            } else {
                out.println("FAILED");
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
