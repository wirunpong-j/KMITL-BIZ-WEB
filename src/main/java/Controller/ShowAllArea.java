/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Listener.Constant;
import Model.AreaModel;
import Model.Customer;
import Model.Formating;
import Model.Order;
import Model.Product;
import Model.Zone;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        String query = "SELECT * FROM `order` JOIN zone USING (order_id) JOIN customer USING (cust_id) JOIN order_product USING (order_id) JOIN product USING (product_id) "
                    + "WHERE DAY(rent_date) = "+ allThursday.get(type).getDayOfMonth() +" AND MONTH(rent_date) = "+ allThursday.get(type).getMonthValue() +" AND YEAR(rent_date) = "+ allThursday.get(type).getYear() +" ORDER BY zone_id, product_id;";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        // set Area
        HashMap<String,Order> allOrder = new HashMap<>();
        HashMap<String,Zone> allZone = new HashMap<>();
        HashMap<String,Customer> allCustomer = new HashMap<>();

        try {
            conn = (Connection) Constant.getConnection();
            pstmt = conn.prepareStatement(query);
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                
                if (!allOrder.containsKey(rs.getString("zone_id"))) {
                    
                    Order order = new Order();
                    order.setOrder_id(rs.getInt("order_id"));
                    order.setCust_id_str(rs.getString("cust_id"));
                    order.setRent_date(rs.getString("rent_date"));
                    
                    Zone zone = new Zone(rs.getString("zone_id"), order.getOrder_id());
                    zone.getAllProductName().add(rs.getString("product_name"));
                    
                    Customer cust = new Customer(rs.getInt("cust_id"));
                    cust.setFullname(rs.getString("fullname"));
                    cust.setTel(rs.getString("tel"));
                    cust.setCust_type(rs.getString("cust_type"));
                    cust.setStudent_id(rs.getString("student_id"));
                    cust.setCitizen_id(rs.getString("citizen_id"));
                    cust.setVehicle(rs.getString("vehicle"));
                    cust.setEmail(rs.getString("email"));
                    
                    allOrder.put(rs.getString("zone_id"), order);
                    allZone.put(rs.getString("zone_id"), zone);
                    allCustomer.put(rs.getString("zone_id"), cust);
                    
                } else {
                    Zone zone = allZone.get(rs.getString("zone_id"));
                    zone.getAllProductName().add(rs.getString("product_name"));
                    allZone.put(rs.getString("zone_id"), zone);
                }
                
            }

            rs.close();
            pstmt.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        
        // show count product on thursday.
        HashMap<Integer, Product> sellProduct = new HashMap<>();
        try {
            conn = (Connection) Constant.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM `order` JOIN order_product USING (order_id) JOIN product USING (product_id) WHERE DAY(rent_date) = "+ allThursday.get(type).getDayOfMonth() +" AND MONTH(rent_date) = "+ allThursday.get(type).getMonthValue() +" AND YEAR(rent_date) = "+ allThursday.get(type).getYear() + " ORDER BY product_id;");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product;
                if (!sellProduct.containsKey(rs.getInt("product_id"))) {
                    product = new Product(rs.getInt("product_id"), rs.getString("product_name"));
                } else {
                    product = sellProduct.get(rs.getInt("product_id"));
                    product.addCountProduct();
                }
                sellProduct.put(rs.getInt("product_id"), product);
            }
            
            rs.close();
            pstmt.close();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
        
        Map<Integer, Product> copySellProduct = new TreeMap<>(sellProduct);
        
        request.setAttribute("allZone", allZone);
        request.setAttribute("allOrder", allOrder);
        request.setAttribute("allCustomer", allCustomer);
        request.setAttribute("allRentType", allRentType);
        request.setAttribute("type", type);
        request.setAttribute("blankArea", AreaModel.getAreaLength() - allOrder.size());
        request.setAttribute("sellProduct", copySellProduct);
        
        
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
