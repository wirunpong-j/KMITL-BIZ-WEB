/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Listener.Constant;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author fluke
 */
@WebServlet(name = "ExportData", urlPatterns = {"/ExportData/"})
public class ExportData extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String fileName = "";
        
        switch (request.getParameter("action")) {
            // Customer Infomation
            case "1":
                {
                    HashMap<Integer, String> header = new HashMap<>();
                    header.put(0, "รหัสลูกค้า");
                    header.put(1, "ชื่อ-นามสกุล");
                    header.put(2, "ประเภท");
                    header.put(3, "เบอร์โทรศัพท์");
                    header.put(4, "Email");
                    header.put(5, "ทะเบียนรถ");
                    
                    HashMap<Integer, String> result = new HashMap<>();
                    result.put(0, "cust_id");
                    result.put(1, "fullname");
                    result.put(2, "cust_type");
                    result.put(3, "tel");
                    result.put(4, "email");
                    result.put(5, "vehicle");
                    
                    String query = "SELECT * FROM customer";
                    fileName = "สรุปข้อมูลลูกค้า";
                    
                    createExcel(fileName, 6, header, query, result);

                    break;
                }
            // Transaction
            case "2":
                {
                    HashMap<Integer, String> header = new HashMap<>();
                    header.put(0, "รหัสลูกค้า");
                    header.put(1, "วันที่ชำระเงิน");
                    header.put(2, "จำนวนเงินที่ชำระ");
                    header.put(3, "ประเภทการชำระ");
                    header.put(4, "จำนวนล็อค");
                    
                    HashMap<Integer, String> result = new HashMap<>();
                    result.put(0, "cust_id");
                    result.put(1, "order_date");
                    result.put(2, "s_price");
                    result.put(3, "order_type");
                    result.put(4, "c_zone");
                    
                    String query = "SELECT cust_id, order_date, SUM(price) as s_price, order_type, COUNT(*) as c_zone "
                            + "FROM kmitlbiz.customer "
                            + "JOIN kmitlbiz.order "
                            + "USING (cust_id) "
                            + "GROUP BY cust_id";
                    fileName = "สรุปข้อมูลการชำระเงิน";
                    
                    createExcel(fileName, 5, header, query, result);

                    break;
                }
            // Area Rent
            default:
                {
                    HashMap<Integer, String> header = new HashMap<>();
                    header.put(0, "รหัสลูกค้า");
                    header.put(1, "วันที่เปิดขาย");
                    header.put(2, "รหัสพื้นที่");
                    header.put(3, "สินค้า");
                    
                    HashMap<Integer, String> result = new HashMap<>();
                    result.put(0, "cust_id");
                    result.put(1, "rent_date");
                    result.put(2, "zone_id");
                    result.put(3, "product_name");
                    
                    String query = "SELECT cust_id, rent_date, zone_id, product_name "
                            + "FROM kmitlbiz.order "
                            + "JOIN product "
                            + "USING (product_id) "
                            + "JOIN zone "
                            + "USING (order_id)";
                    fileName = "สรุปข้อมูลการจองพื้นที่ขาย";
                    
                    createExcel(fileName, 4, header, query, result);
                    
                    break;
                }
        }
        
        try {
            String downloadPath = URLEncoder.encode(fileName,"UTF-8");
            response.setHeader("Content-disposition","attachment; filename=" + downloadPath + ".xls");
            
            OutputStream out = response.getOutputStream();
            FileInputStream in = new FileInputStream("excel/" + fileName + ".xls");

            byte[] buffer = new byte[4096];
            while(in.read(buffer, 0, 4096) != -1)
                out.write(buffer, 0, 4096);
            in.close();
            out.flush();
            out.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Export Success");
        return;

    }
    
    private void createExcel(String fileName, int numCell, HashMap<Integer, String> header, String query, HashMap<Integer, String> result) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = (Connection) Constant.getConnection();
            
            FileOutputStream fileOut = new FileOutputStream("excel/" + fileName + ".xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet(fileName);
            HSSFRow rowhead = worksheet.createRow((short)0);
            
            for (int i = 0; i < numCell; i++) {
                rowhead.createCell(i).setCellValue(header.get(i));
            }

            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            int i = 1;
            while(rs.next()){
                HSSFRow row = worksheet.createRow((short) i);
                
                for (int k = 0; k < numCell; k++) {
                    row.createCell(k).setCellValue(rs.getString(result.get(k)));
                }
                
                i++;
            }
            
            workbook.write(fileOut);
            fileOut.close();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
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
