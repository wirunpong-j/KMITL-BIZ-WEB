/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customer;
import Model.Order;
import Model.Product;
import Model.Staff;
import Model.Zone;
import java.io.IOException;
import java.sql.Connection;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
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
@WebServlet(name = "RentArea", urlPatterns = {"/RentArea"})
public class RentArea extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        
        Product product = (Product) session.getAttribute("product");
        Customer customer = (Customer) session.getAttribute("customer");
        Staff staff = (Staff) session.getAttribute("staff");
        
        String[] allArea = request.getParameter("allArea").split(",");
        int addCost = Integer.parseInt(request.getParameter("addCost"));
        String note = request.getParameter("note");
        String rentType = request.getParameter("rentType");
        
        HashMap<String, Object> allRentDate = (HashMap<String, Object>) session.getAttribute("allRentDate");
        
        if (rentType.equals("R1") || rentType.equals("R2")) {
            ZonedDateTime thursday = (ZonedDateTime) allRentDate.get(rentType);
            Order order = new Order(thursday, customer.getPrice(), customer.getCust_id(), staff.getStaff_id());
                order.setExtra_price(addCost);
                order.setNote(note);
                if (rentType.equals("R1")) {
                    order.setOrder_type("NOR");
                } else {
                    order.setOrder_type("PRE");
                }
                order.setProduct_id(product.getProduct_id());
                order.addOrder();
                
            for (String area: allArea) {
                Zone zone = new Zone(area, order.getOrder_id());
                zone.insertZoneToDB();
            }
            
        } else {
            ArrayList<ZonedDateTime> thursdayOnMonth = (ArrayList<ZonedDateTime>) allRentDate.get(rentType);
            for (ZonedDateTime thursday: thursdayOnMonth) {
                Order order = new Order(thursday, customer.getPrice(), customer.getCust_id(), staff.getStaff_id());
                order.setExtra_price(addCost);
                order.setNote(note);
                order.setOrder_type("PRE");
                order.setProduct_id(product.getProduct_id());
                order.addOrder();
                
                for (String area: allArea) {
                    Zone zone = new Zone(area, order.getOrder_id());
                    zone.insertZoneToDB();
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
