<%@page import="Model.Customer"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="Listener.Constant"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template/header.jsp" />

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>KMITL Biz Way</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  <link rel="stylesheet" href="css/style.css">
</head>

<body>
  <div class="container">
    <div class="jumbotron" id="admin_cust_edit">
      <h1>ดู/แก้ไข ข้อมูลลูกค้า</h1>
    </div>

    <form>
      <div class="form-group">
        <label for="search_cust">รหัสรับบริการ</label>
        <input type="text" class="form-control" id="search_cust" name="search" placeholder="รหัสรับบริการ">
      </div>
      <button type="submit" class="btn btn-default" name="click">ค้นหา</button>
    </form>
    <br><br>
    
    <%
        if(request.getParameter("click") != null){
            String fname = null;
            String lname = null;
            String id = null;
            String n1 = request.getParameter("search");
            int cust_id = Integer.parseInt(n1);
            Customer cust = new Customer(cust_id);

            cust.searchCustomer();
            String fullname = cust.getFullname();
            String[] nr = fullname.split(" ");
            fname = nr[0];
            lname = nr[1];
            

            if (cust.getStudent_id() == null){
                id = cust.getCitizen_id();
            }
            else{
                id = cust.getStudent_id();
            }
    %>

    <form action="UpdateCustomer">
      <div class="form-group">
        <label for="fname">ชื่อจริง</label>
        <input type="text" class="form-control" id="fname" name="fname" value="<%= fname%>" placeholder="ชื่อจริง">
      </div>
      <div class="form-group">
        <label for="lname">นามสกุล</label>
        <input type="text" class="form-control" id="lname" name="lname" value="<%= lname%>" placeholder="นามสกุล">
      </div>
      <div class="form-group">
        <label for="tel">เบอร์โทรศัพท์</label>
        <input type="text" class="form-control" id="tel" name="tel" value="<%= cust.getTel()%>" placeholder="เบอร์โทรศัพท์">
      </div>
      <div class="form-group">
        <label for="email">อีเมล</label>
        <input type="email" class="form-control" id="email" name="email" value="<%= cust.getEmail()%>" placeholder="อีเมล">
      </div>
      <div class="form-group">
        <label for="plate">เลขทะเบียนรถ (ถ้ามี)</label>
        <input type="text" class="form-control" id="plate" name="plate" value="<%= cust.getVehicle()%>" placeholder="เลขทะเบียนรถ (ถ้ามี)">
      </div>
      <div class="form-group">
        <label for="type">ประเภท</label>
        <% String type1 = "";
        String type2 = "";
        String type3 = "";
            switch (cust.getCust_type()) {
                case "STUDENT": type1 = "checked"; break;
                case "STAFF": type2 = "checked"; break;
                case "OUTSIDER": type3 = "checked"; break;
        } %>
        <label class="radio-inline">
          <input type="radio" name="cust_type" id="inlineRadio1" value="STUDENT" <%= type1%>> นักศึกษา
        </label>
                <label class="radio-inline">
          <input type="radio" name="cust_type" id="inlineRadio2" value="STAFF" <%= type2%>> พนักงาน
        </label>
                <label class="radio-inline">
          <input type="radio" name="cust_type" id="inlineRadio3" value="OUTSIDER" <%= type3%>> บุคคลภายนอก
        </label>
      </div>
      <div class="form-group">
        <label for="id">รหัสบัตรประชาชน/รหัสนักศึกษา</label>
        <input type="text" class="form-control" id="id" name="id" value="<%= id%>" placeholder="รหัสบัตรประชาชน/รหัสนักศึกษา">
      </div>
      <button type="submit" class="btn btn-default">บันทึก</button>
    </form>
      
      <%}%>

    <form class="" action="DeleteCustomer" method="post" id="deleteuser">
      <div class="form-group">
        <label for="plate">ใส่เบอร์โทรศัพท์ลูกค้าเพื่อทำการลบ</label>
        <input type="text" class="form-control" id="tele" name="tel" placeholder="เบอร์โทรศัพท์">
      </div>
      <button type="submit" class="btn btn-danger" form="deleteuser">ลบลูกค้า</button>
    </form>
  </div>
      
  <script src="https://use.fontawesome.com/211152dd25.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>

</html>


<jsp:include page="template/footer.jsp" />