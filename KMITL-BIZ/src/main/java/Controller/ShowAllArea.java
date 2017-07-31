/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Listener.Constant;
import Model.Customer;
import Model.Formating;
import Model.Order;
import Model.Product;
import Model.Zone;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "ShowAllArea", urlPatterns = {"/ShowAllArea/"})
public class ShowAllArea extends HttpServlet {

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
        
        int type = Integer.parseInt(request.getParameter("type"));
        
        LocalDateTime currentTime = LocalDateTime.now();
   
        ZoneId id = ZoneId.of("Asia/Bangkok");
        ZonedDateTime zonedTime = currentTime.atZone(id);
        ZonedDateTime dateIncrementor = zonedTime;
        
        
        ArrayList<ZonedDateTime> allThursday = new ArrayList<>();
        int count = 1;
        while (count <= 8) {
            if (dateIncrementor.getDayOfWeek() == DayOfWeek.THURSDAY) {
                allThursday.add(dateIncrementor);
                dateIncrementor = dateIncrementor.plusDays(7);
                count++;
            } else {
                dateIncrementor = dateIncrementor.plusDays(1);
            } 
        }
        
        
        HashMap<Integer,String> allRentType = new HashMap<>();
        count = 0;
        for (ZonedDateTime dt: allThursday) {
            allRentType.put(count, "วันพฤหัสบดีที่ " + dt.getDayOfMonth() + " " + Formating.monthFormat(dt.getMonth().getValue()) + " " + dt.getYear());;
            count++;
        }
        
        
        String query = "SELECT * FROM `order` JOIN zone USING (order_id) JOIN customer USING (cust_id) JOIN product USING (product_id) "
                    + "WHERE DAY(rent_date) = "+ allThursday.get(type).getDayOfMonth() +" AND MONTH(rent_date) = "+ allThursday.get(type).getMonthValue() +" AND YEAR(rent_date) = "+ allThursday.get(type).getYear() +" ORDER BY zone_id;";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        // set Area
        HashMap<String,ArrayList<Object>> allZone = new HashMap<>();
        try {
            conn = (Connection) Constant.getConnection();
            pstmt = conn.prepareStatement(query);
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ArrayList<Object> info = new ArrayList<>();
                
                Order order = new Order();
                order.setCust_id_str(rs.getString("cust_id"));
                order.setProduct_id(rs.getInt("product_id"));
                order.setRent_date(rs.getString("rent_date"));
                
                Product pro = new Product(rs.getInt("product_id"), rs.getString("product_name"));
                
                Customer cust = new Customer(rs.getInt("cust_id"), rs.getInt("product_id"));
                cust.setFullname(rs.getString("fullname"));
                cust.setTel(rs.getString("tel"));
                cust.setCust_type(rs.getString("cust_type"));
                cust.setStudent_id(rs.getString("student_id"));
                cust.setCitizen_id(rs.getString("citizen_id"));
                cust.setVehicle(rs.getString("vehicle"));
                cust.setEmail(rs.getString("email"));
                
                info.add(order);
                info.add(pro);
                info.add(cust);
                
                allZone.put(rs.getString("zone_id"), info);
            }

            rs.close();
            pstmt.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        
        request.setAttribute("allZone", allZone);
        request.setAttribute("allRentType", allRentType);
        request.setAttribute("type", type);
        
        RequestDispatcher page = request.getRequestDispatcher("/lookup.jsp");
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
