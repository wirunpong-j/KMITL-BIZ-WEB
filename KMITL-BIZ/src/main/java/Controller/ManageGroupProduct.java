/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.FetchData;
import Model.Product;
import Model.Product_Group;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ManageGroupProduct", urlPatterns = {"/ManageGroupProduct/"})
public class ManageGroupProduct extends HttpServlet {

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
        
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        
        // Add Group
        if (action.equals("addGroup")) {
            String group_name = request.getParameter("gName");
            Product_Group productGroup = new Product_Group(group_name);
            
            try (PrintWriter out = response.getWriter()) {
                if (productGroup.addGroupProduct()) {
                    out.println("ADDED");
                } else {
                    out.println("ADD ERROR");
                }
            }
        } // Add Product on Group
        else if (action.equals("addProduct")) {
            String product_name = request.getParameter("productName");
            int group_id = Integer.parseInt(request.getParameter("groupID"));
            
            Product product = new Product(product_name);
            product.setGroup_id(group_id);
            
            try (PrintWriter out = response.getWriter()) {
                if (product.addProduct()) {
                    out.println("ADDED");
                } else {
                    out.println("ADD ERROR");
                }
            }
            
        } // Move Product to another group
        else if (action.equals("moveProduct")) {
            String[] allProduct = request.getParameter("product").split(",");
            int group_id = Integer.parseInt(request.getParameter("groupID"));
            int pass = 0;
            
            for (String proID: allProduct) {
                Product product = new Product(Integer.parseInt(proID));
                product.setGroup_id(group_id);
                if (product.changeGroup()) {
                    pass++;
                }
            }
            
            try (PrintWriter out = response.getWriter()) {
                if (allProduct.length == pass) {
                    out.println("MOVED");
                } else {
                    out.println("MOVE ERROR");
                }
            }
        }
        
        // Remove Product
        else if (action.equals("removeProduct")) {
            String[] allProduct = request.getParameter("product").split(",");
            int pass = 0;
            
            for (String proID: allProduct) {
                Product product = new Product(Integer.parseInt(proID));
                if (product.removeProduct()) {
                    pass++;
                }
            }
            
            try (PrintWriter out = response.getWriter()) {
                if (allProduct.length == pass) {
                    out.println("REMOVED");
                } else {
                    out.println("REMOVE ERROR");
                }
            }
        }
        
        session.setAttribute("allGroupPro", FetchData.fetchGroupProduct());
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
