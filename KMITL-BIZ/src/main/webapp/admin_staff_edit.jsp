<%@page import="Model.Staff"%>
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
    <div class="jumbotron" id="admin_staff_edit">
      <h1>ดู/แก้ไข ข้อมูลพนักงาน</h1>
    </div>

    <form class="" method="post">
      <div class="form-group">
        <label for="search_username">ชื่อผู้ใช้</label>
        <input type="text" class="form-control" id="search-staff" name="search" placeholder="ชื่อผู้ใช้">
      </div>
      <button type="submit" class="btn btn-default" name="click">ค้นหา</button>
    </form>
    <br><br>

    <% 
        if(request.getParameter("click") != null){
            String search = request.getParameter("search");
            Staff staf = new Staff(search);
            
            staf.searchStaff();
            
            String case1 = "";
            String case2 = "";
            switch (staf.getRole()){
                case "ST" : case1 = "checked"; break;
                case "AD" : case2 = "checked"; break;
            }
    %>
    <form action="UpdateStaff">
      <div class="form-group">
        <label for="fname">ชื่อจริง</label>
        <input type="text" class="form-control" id="fname" name="fname" value="<%= staf.getFirst_name()%>" placeholder="ชื่อจริง">
      </div>
      <div class="form-group">
        <label for="lname">นามสกุล</label>
        <input type="text" class="form-control" id="lname" name="lname" value="<%= staf.getLast_name()%>" placeholder="นามสกุล">
      </div>
      <div class="form-group">
        <label for="username">ชื่อผู้ใช้</label>
        <input type="text" class="form-control" id="username" name="staff_id" value="<%= staf.getStaff_id()%>" placeholder="ชื่อผู้ใช้">
      </div>
      <div class="form-group">
        <label for="password">รหัสผ่าน</label>
        <input type="password" class="form-control" id="password" name="password" value="<%= staf.getPassword()%>" placeholder="รหัสผ่าน">
      </div>
      <div class="form-group">
        <label for="password">ยืนยันรหัสผ่าน</label>
        <input type="password" class="form-control" id="confirm-password" value="<%= staf.getPassword()%>" placeholder="ยืนยันรหัสผ่าน">
      </div>
      <div class="form-group">
        <label for="type">ประเภท</label>
        <label class="radio-inline">
            <input type="radio" name="role" id="inlineRadio1" value="ST" <%= case1%>> Staff
        </label>
                <label class="radio-inline">
                    <input type="radio" name="role" id="inlineRadio2" value="AD" <%= case2%>> Admin
        </label>
      </div>
      <button type="submit" class="btn btn-default">บันทึก</button>
    </form>
    <%
    }
    %>

    <form class="" action="DeleteStaff" method="post" id="deleteuser">
      <div class="form-group">
        <label for="plate">ใส่ชื่อผู้ใช้ที่ต้องการลบ</label>
        <input type="text" class="form-control" id="staff" name="staff_id" placeholder="ชื่อผู้ใช้">
      </div>
      <button type="submit" class="btn btn-danger" form="deleteuser">ลบพนักงาน</button>
    </form>

  </div>
    <script type="text/javascript">
        window.onload = function () {
            document.getElementById("password").onchange = validatePassword;
            document.getElementById("confirm-password").onchange = validatePassword;
        }
        function validatePassword(){
            var pass2=document.getElementById("confirm-password").value;
            var pass1=document.getElementById("password").value;
            if(pass1!=pass2)
                document.getElementById("confirm-password").setCustomValidity("รหัสผ่านไม่ตรงกัน");
            else
                document.getElementById("confirm-password").setCustomValidity('');	 
        //empty string means no validation error
        }
    </script>
  <script src="https://use.fontawesome.com/211152dd25.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>

</html>
<jsp:include page="template/footer.jsp" />