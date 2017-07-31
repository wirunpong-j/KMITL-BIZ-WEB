<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Customer"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Listener.Constant"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp" />

  <div class="container">
    <div class="jumbotron" id="admin_cust_history">
      <h1>ดูข้อมูลการจอง</h1>
    </div>

    <form>
      <div class="form-group">
        <label for="search_cust">รหัสรับบริการ</label>
        <input type="text" class="form-control" id="search_cust" name="search" placeholder="รหัสรับบริการ">
      </div>
      <button type="submit" class="btn btn-default" name="click">ค้นหา</button>
    </form>
    <br><br><br>
    
      <%
          if(request.getParameter("click") != null){
            Connection conn = null;
            try {
                conn = (Connection) Constant.getConnection();
                String search = request.getParameter("search");
                int cust_id = Integer.parseInt(search);
                ArrayList<Integer> order_id = new ArrayList<Integer>();
                ArrayList<String> order_date = new ArrayList<String>();
                ArrayList<String> rent_date = new ArrayList<String>();
                ArrayList<String> product = new ArrayList<String>();
                ArrayList<Integer> price = new ArrayList<Integer>();
                ArrayList<Integer> extra_price = new ArrayList<Integer>();
                ArrayList<String> note = new ArrayList<String>();
                HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
                
                Customer cust = new Customer(cust_id);
                cust.searchCustomerByID();
                String fullname = cust.getFullname();
                
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM customer "
                        + "JOIN kmitlbiz.order "
                        + "USING (cust_id) "
                        + "JOIN product "
                        + "USING (product_id) "
                        + "WHERE cust_id = ?");
                pstmt.setInt(1, cust_id);
                String cust_id_form = String.format("%06d", cust_id);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()){
                    order_id.add(rs.getInt("order_id"));
                    order_date.add(rs.getString("order_date"));
                    rent_date.add(rs.getString("rent_date"));
                    product.add(rs.getString("product_name"));
                    price.add(rs.getInt("price"));
                    extra_price.add(rs.getInt("extra_price"));
                    note.add(rs.getString("note"));
                }
                
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < order_id.size(); i++){
                    if (i > 0){
                        sb.append(" OR ");
                    }
                    sb.append("order_id = ");
                    sb.append(Integer.toString(order_id.get(i)));
                }
                String st = sb.toString();
                String statement = "SELECT * FROM zone WHERE " + st;
                PreparedStatement pstmt1 = conn.prepareStatement(statement);
                ResultSet rs1 = pstmt1.executeQuery();
                while (rs1.next()){
                    System.out.println("Yes");
                    int key = rs1.getInt("order_id");
                    if (map.get(key) == null){
                        map.put(key, new ArrayList<String>());
                    }
                    map.get(key).add(rs1.getString("zone_id"));
                }
                System.out.println(map.get(1));

                pstmt1.close();
      %>

    <h2>รหัสรับบริการ <%= cust_id_form%> คุณ <%= fullname%></h2>
    <table class="table table-striped">
      <tr>
        <th>รหัสการจอง</th>
        <th>วันที่ออกบิล</th>
        <th>วันที่จอง</th>
        <th>พื้นที่ที่จอง</th>
        <th>สินค้าที่ขาย</th>
        <th>ราคาจอง</th>
        <th>ค่าใช้จ่ายเพิ่มเติม</th>
        <th>หมายเหตุ</th>
      </tr>
      <%
          for(int k = 0; k < order_id.size(); k++){
              int key = order_id.get(k);
              String order_id_form = String.format("%06d", key);
              ArrayList<String> zone  = map.get(key);
              StringBuilder sb1 = new StringBuilder();
              for (int j = 0 ; j < zone.size(); j++){
                  if (j > 0){
                    sb1.append(", ");
                  }
                  sb1.append(zone.get(j));
              }
              String zone_form = sb1.toString();
      %>
      <tr>
        <td><%= order_id_form%></td>
        <td><%= order_date.get(k)%></td>
        <td><%= rent_date.get(k)%></td>
        <td><%= zone_form%></td>
        <td><%= product.get(k)%></td>
        <td><%= price.get(k)%></td>
        <td><%= extra_price.get(k)%></td>
        <td><%= note.get(k)%></td>
      </tr>
      <%
            }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } finally {
                if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
            }
            }
      %>
    </table>

    <button type="submit" class="btn btn-default">บันทึกเป็น Excel</button>
    <button type="submit" class="btn btn-default">บันทึกเป็น Pdf</button>

  </div>

<jsp:include page="/template/footer.jsp" />