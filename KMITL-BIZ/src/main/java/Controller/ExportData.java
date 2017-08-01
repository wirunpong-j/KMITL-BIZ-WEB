/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Listener.Constant;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author fluke
 */
@WebServlet(name = "ExportData", urlPatterns = {"/ExportData"})
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
        response.setContentType("text/html;charset=UTF-8");
        
        Connection conn = null;
        try {
            conn = (Connection) Constant.getConnection();
            FileOutputStream fileOut = new FileOutputStream("ข้อมูลลูกค้า.xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("ข้อมูลลูกค้า");
            HSSFRow rowhead = worksheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("รหัสลูกค้า");
            rowhead.createCell(1).setCellValue("ชื่อ-นามสกุล");
            rowhead.createCell(2).setCellValue("ประเภท");
            rowhead.createCell(3).setCellValue("เบอร์โทรศัพท์");
            rowhead.createCell(4).setCellValue("Email");
            rowhead.createCell(5).setCellValue("ทะเบียนรถยนตร์");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM customer");
            ResultSet rs = pstmt.executeQuery();
            int i = 1;
            while(rs.next()){
                HSSFRow row = worksheet.createRow((short)i);
                row = worksheet.createRow((short)i);
                row.createCell(0).setCellValue(rs.getString("cust_id"));
                row.createCell(1).setCellValue(rs.getString("fullname"));
                row.createCell(2).setCellValue(rs.getString("cust_type"));
                row.createCell(3).setCellValue(rs.getString("tel"));
                row.createCell(4).setCellValue(rs.getString("email"));
                row.createCell(5).setCellValue(rs.getString("vehicle"));
                i++;
            }
            workbook.write(fileOut);
            fileOut.close();
            
            FileOutputStream fileOut1 = new FileOutputStream("ข้อมูลการชำระเงิน.xls");
            HSSFWorkbook workbook1 = new HSSFWorkbook();
            HSSFSheet worksheet1 = workbook1.createSheet("ข้อมูลการชำระเงิน");
            HSSFRow rowhead1 = worksheet1.createRow((short)0);
            rowhead1.createCell(0).setCellValue("รหัสลูกค้า");
            rowhead1.createCell(1).setCellValue("วันที่ชำระเงิน");
            rowhead1.createCell(2).setCellValue("จำนวนเงินที่ชำระ");
            rowhead1.createCell(3).setCellValue("ประเภทการชำระ");
            rowhead1.createCell(4).setCellValue("จำนวนล็อค");
            PreparedStatement pstmt1 = conn.prepareStatement("SELECT cust_id, order_date, SUM(price) as s_price, order_type, COUNT(*) as c_zone "
                    + "FROM kmitlbiz.customer "
                    + "JOIN kmitlbiz.order "
                    + "USING (cust_id) "
                    + "GROUP BY cust_id");
            ResultSet rs1 = pstmt1.executeQuery();
            int j = 1;
            while(rs1.next()){
                HSSFRow row1 = worksheet1.createRow((short)j);
                row1 = worksheet1.createRow((short)j);
                row1.createCell(0).setCellValue(rs1.getString("cust_id"));
                row1.createCell(1).setCellValue(rs1.getString("order_date"));
                row1.createCell(2).setCellValue(rs1.getString("s_price"));
                row1.createCell(3).setCellValue(rs1.getString("order_type"));
                row1.createCell(4).setCellValue(rs1.getString("c_zone"));
                j++;
            }
            workbook1.write(fileOut1);
            fileOut1.close();
            
            FileOutputStream fileOut2 = new FileOutputStream("ข้อมูลการจองล็อค.xls");
            HSSFWorkbook workbook2 = new HSSFWorkbook();
            HSSFSheet worksheet2 = workbook2.createSheet("ข้อมูลการจองล็อค");
            HSSFRow rowhead2 = worksheet2.createRow((short)0);
            rowhead2.createCell(0).setCellValue("รหัสลูกค้า");
            rowhead2.createCell(1).setCellValue("วันเปิดขาย");
            rowhead2.createCell(2).setCellValue("รหัสล็อค");
            rowhead2.createCell(3).setCellValue("สินค้า");
            PreparedStatement pstmt2 = conn.prepareStatement("SELECT cust_id, rent_date, zone_id, product_name "
                    + "FROM kmitlbiz.order "
                    + "JOIN product "
                    + "USING (product_id) "
                    + "JOIN zone "
                    + "USING (order_id)");
            ResultSet rs2 = pstmt2.executeQuery();
            int k = 1;
            while(rs2.next()){
                HSSFRow row2 = worksheet2.createRow((short)k);
                row2 = worksheet2.createRow((short)k);
                row2.createCell(0).setCellValue(rs2.getString("cust_id"));
                row2.createCell(1).setCellValue(rs2.getString("rent_date"));
                row2.createCell(2).setCellValue(rs2.getString("zone_id"));
                row2.createCell(3).setCellValue(rs2.getString("product_name"));
                k++;
            }
            workbook2.write(fileOut2);
            fileOut2.close();
            
            System.out.println("Export Success");
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
