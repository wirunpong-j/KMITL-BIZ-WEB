/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
         try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) getServletContext().getAttribute("connection");
            HttpSession session = request.getSession();
            Statement statement = conn.createStatement();
            Statement statement1 = conn.createStatement();
            Statement statement2 = conn.createStatement();
            FileOutputStream fileOut;
            fileOut = new FileOutputStream("file.xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("ข้อมูลลูกค้า");
            Row row1 = worksheet.createRow((short)0);
            row1.createCell(0).setCellValue("รหัสลูกค้า");
            row1.createCell(1).setCellValue("ชื่อ-นามสกุล");
            row1.createCell(2).setCellValue("ประเภท");
            row1.createCell(3).setCellValue("เบอร์โทรศัพท์");
            row1.createCell(4).setCellValue("Email");
            row1.createCell(5).setCellValue("ทะเบียนรถยนตร์");
            Row row2 ;
            ResultSet rs = statement.executeQuery("SELECT * FROM KMITLBIZ.CUSTOMER");
            while(rs.next()){
                int a = rs.getRow();
                row2 = worksheet.createRow((short)a);
                row2.createCell(0).setCellValue(rs.getString("cust_id"));
                row2.createCell(1).setCellValue(rs.getString("fullname"));
                row2.createCell(2).setCellValue(rs.getString("cust_type"));
                row2.createCell(3).setCellValue(rs.getString("tel"));
                row2.createCell(4).setCellValue(rs.getString("email"));
                row2.createCell(5).setCellValue(rs.getString("vehicle_num"));
            }
            HSSFSheet worksheet1 = workbook.createSheet("ข้อมูลการชำระเงิน");
            Row row3 = worksheet1.createRow((short)0);
            row3.createCell(0).setCellValue("รหัสลูกค้า");
            row3.createCell(1).setCellValue("วันที่ชำระเงิน");
            row3.createCell(2).setCellValue("จำนวนเงินที่ชำระ");
            row3.createCell(3).setCellValue("ประเภทการชำระ");
            row3.createCell(4).setCellValue("จำนวนล็อค");
            Row row4 ;
            ResultSet rs1 = statement1.executeQuery("SELECT ord.cust_id as cust_id, ord.order_date as order_date, ord.price as price, ord.payment as payment, COUNT(ord.order_id) as zone_count"
                    + "FROM KMITLBIZ.ORDER as ord"
                    + "INNER JOIN KMITLBIZ.ZONE as zo ON ord.order_id = zo.ORDER_order_id"
                    + "WHERE order_date = @order_date"
                    + "GROUP BY cust_id");
            while(rs1.next()){
                int b = rs1.getRow();
                row4 = worksheet1.createRow((short)b);
                row4.createCell(0).setCellValue(rs.getString("cust_id"));
                row4.createCell(1).setCellValue(rs.getString("order_date"));
                row4.createCell(2).setCellValue(rs.getString("price"));
                row4.createCell(3).setCellValue(rs.getString("payment"));
                row4.createCell(4).setCellValue(rs.getString("zone_count"));
            }
            HSSFSheet worksheet2 = workbook.createSheet("ข้อมูลการจองล็อค");
            Row row5 = worksheet2.createRow((short)0);
            row5.createCell(0).setCellValue("รหัสลูกค้า");
            row5.createCell(1).setCellValue("วันเปิดขาย");
            row5.createCell(2).setCellValue("รหัสล็อค");
            row5.createCell(3).setCellValue("สินค้า");
            Row row6 ;
            ResultSet rs2 = statement2.executeQuery("SELECT cus.cust_id as cust_id, cust.start_date as start_date, zo.zone_id as zone_id, pro.product_name as product_name"
                    + "FROM KMITLBIZ.CUSTOMER as cus"
                    + "INNER JOIN KMITLBIZ.ORDER as ord ON cus.cust_id = ord.CUSTOMER_cust_id"
                    + "INNER JOIN KMITLBIZ.ZONE as zon ON ord.order_id = zon.ORDER_order_id"
                    + "INNER JOIN KMITLBIZ.PRODUCT as pro ON cus.PRODUCT_product_id = pro.product_id");
            while(rs2.next()){
                int c = rs2.getRow();
                row6 = worksheet2.createRow((short)c);
                row6.createCell(0).setCellValue(rs.getString("cust_id"));
                row6.createCell(1).setCellValue(rs.getString("start_date"));
                row6.createCell(2).setCellValue(rs.getString("zone"));
                row6.createCell(3).setCellValue(rs.getString("product_name"));
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();
            rs1.close();
            rs2.close();
            statement.close();
            statement1.close();
            statement2.close();
            conn.close();
            System.out.println("Export Success");
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
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
