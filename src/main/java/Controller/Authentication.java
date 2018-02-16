/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AllFormat;
import Model.AreaModel;
import Model.FetchData;
import Model.MyDateTime;
import Model.Staff;
import java.io.IOException;
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
            
            session.setAttribute("allGroupPro", FetchData.fetchGroupProduct());
            session.setAttribute("staff", staff);
            session.setAttribute("allArea", AreaModel.allArea());
            session.setAttribute("allFormat", new AllFormat());
            
            session.setAttribute("allRentType", MyDateTime.getInstance().getAllRentType());
            session.setAttribute("allRentDate", MyDateTime.getInstance().getAllRentDate());
            
            response.sendRedirect("/main.jsp");
            
        } else {
            response.sendRedirect("/");
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
