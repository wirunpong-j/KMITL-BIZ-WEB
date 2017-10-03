/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Listener.Constant;
import Model.Customer;
import Model.Formating;
import Model.Product;
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
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {

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
        
        String[] product = request.getParameter("product").split(",");
        String customer = request.getParameter("customer");

        HttpSession session = request.getSession();
        
        Connection conn = null;
        PreparedStatement pstmt;
        ResultSet rs;
        
        try (PrintWriter out = response.getWriter()) {
            try {
                conn = (Connection) Constant.getConnection();
                pstmt = conn.prepareStatement("SELECT * FROM customer WHERE cust_id = ?");
                pstmt.setString(1, customer);
                rs = pstmt.executeQuery();
                
                if (!rs.next()) {
                    conn.close();
                    out.println("NOT FOUND");
                    return;
                }
                
            } catch (Exception ex) {
                ex.printStackTrace();
                
            } finally {
                if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
            }
        }
        
//        try {
//            conn = (Connection) Constant.getConnection();
//            pstmt = conn.prepareStatement("SELECT * FROM product WHERE product_name = ?");
//            pstmt.setString(1, product);
//            
//            rs = pstmt.executeQuery();
//            
//            if (rs.next()) {
//                pro = new Product(rs.getInt("product_id"), rs.getString("product_name"));
//            } else {
//                pro = new Product(product);
//                pro.addToDB();
//            }
//            
//            pstmt.close();
//            
//            pstmt = conn.prepareStatement("SELECT * FROM product");
//            rs = pstmt.executeQuery();
//            
//            while (rs.next()) {
//                Product p = new Product(rs.getInt("product_id"), rs.getString("product_name"));
//                allProduct.add(p);
//            }
//            
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        } finally {
//            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
//        }

        Customer cust = new Customer(Integer.parseInt(customer));
        cust.searchCustomerByID();
        for (String pro: product) {
            cust.setAllProduct(Integer.parseInt(pro.split(":")[0]));
        }
        
        HashMap<String, String> allRentType = new HashMap<>();
        HashMap<String, Object> allRentDate = new HashMap<>();
        LocalDateTime currentTime = LocalDateTime.now();
   
        ZoneId id = ZoneId.of("Asia/Bangkok");
        ZonedDateTime zonedTime = currentTime.atZone(id);
        Month thisMonth = zonedTime.getMonth();
        Month nextMonth = thisMonth.plus(1);
       
        ZonedDateTime dateIncrementor = zonedTime;
              
        ArrayList<ZonedDateTime> thisMonthDays = new ArrayList<>();
        while(dateIncrementor.getMonth() == thisMonth) {
            if(dateIncrementor.getDayOfWeek() == DayOfWeek.THURSDAY) {
                thisMonthDays.add(dateIncrementor);
            }
            dateIncrementor = dateIncrementor.plusDays(1);
        }

        ArrayList<ZonedDateTime> nextMonthDays = new ArrayList<>();
        while(dateIncrementor.getMonth() == nextMonth) {
            if(dateIncrementor.getDayOfWeek() == DayOfWeek.THURSDAY) {
                nextMonthDays.add(dateIncrementor);
            }
            dateIncrementor = dateIncrementor.plusDays(1);
        }

        ZonedDateTime thisThursday = zonedTime;
        while (thisThursday.getDayOfWeek() != DayOfWeek.THURSDAY) {
            thisThursday = thisThursday.plusDays(1);
        }

        ZonedDateTime nextThursday = thisThursday.plusDays(1);
        while (nextThursday.getDayOfWeek() != DayOfWeek.THURSDAY) {
            nextThursday = nextThursday.plusDays(1);
        }
        
        allRentType.put("R1", "จองวันพฤหัสบดีที่ " + thisThursday.getDayOfMonth() + " " + Formating.monthFormat(thisThursday.getMonth().getValue()) + " " + thisThursday.getYear());
        allRentType.put("R2", "จองล่วงหน้าวันพฤหัสบดีที่ " + nextThursday.getDayOfMonth() + " " + Formating.monthFormat(nextThursday.getMonth().getValue()) + " " + nextThursday.getYear());
        allRentType.put("R3", "จองทั้งเดือน " + Formating.monthFormat(thisMonth.getValue()));
        allRentType.put("R4", "จองล่วงหน้าทั้งเดือน " + Formating.monthFormat(nextMonth.getValue()));
        
        allRentDate.put("R1", thisThursday);
        allRentDate.put("R2", nextThursday);
        allRentDate.put("R3", thisMonthDays);
        allRentDate.put("R4", nextMonthDays);
        
        session.setAttribute("allRentType", allRentType);
        session.setAttribute("allRentDate", allRentDate);
        session.setAttribute("customer", cust);
        session.setAttribute("status", "RENT");
        
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
