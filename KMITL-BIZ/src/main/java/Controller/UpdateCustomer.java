/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customer;
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
@WebServlet(name = "UpdateCustomer", urlPatterns = {"/UpdateCustomer/"})
public class UpdateCustomer extends HttpServlet {

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
        String cust_type = request.getParameter("cust_type");
        String id = request.getParameter("id");
        int cust_id = Integer.parseInt(request.getParameter("custid"));
        
        String student_id = (cust_type.equals("STUDENT")) ? id : "";
        String citizen_id = (!cust_type.equals("STUDENT")) ? id : "";
        String vehicle = (plate.equals("")) ? plate : "";
        
        Customer cust = new Customer(cust_id);
        cust.setFullname(fullname);
        cust.setTel(tel);
        cust.setEmail(email);
        cust.setVehicle(vehicle);
        cust.setCust_type(cust_type);
        cust.setCitizen_id(citizen_id);
        cust.setStudent_id(student_id);
        
        try (PrintWriter out = response.getWriter()) {
            if (cust.updateCustomer()) {
                out.println("SUCCESS");
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
