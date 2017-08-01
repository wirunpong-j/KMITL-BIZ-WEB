<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp" />

  <div class="container">
    <div class="jumbotron" id="admin_staff_add">
      <h1>เพิ่มพนักงานใหม่</h1>
    </div>

    <form action="${SITE_URL}/AddStaff" method="POST">
      <div class="form-group">
        <label for="fname">ชื่อจริง</label>
        <input type="text" class="form-control" id="fname" name="fname" placeholder="ชื่อจริง">
      </div>
      <div class="form-group">
        <label for="lname">นามสกุล</label>
        <input type="text" class="form-control" id="lname" name="lname" placeholder="นามสกุล">
      </div>
      <div class="form-group">
        <label for="username">ชื่อผู้ใช้</label>
        <input type="text" class="form-control" id="username" name="username" placeholder="ชื่อผู้ใช้">
      </div>
      <div class="form-group">
        <label for="password">รหัสผ่าน</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="รหัสผ่าน">
      </div>
      <div class="form-group">
        <label for="password">ยืนยันรหัสผ่าน</label>
        <input type="password" class="form-control" id="confirm-password" placeholder="ยืนยันรหัสผ่าน">
      </div>
      <div class="form-group">
        <label for="type">ประเภท</label>
        <label class="radio-inline">
          <input type="radio" name="role" id="inlineRadio1" value="ST"> Staff
        </label>
                <label class="radio-inline">
          <input type="radio" name="role" id="inlineRadio2" value="AD"> Admin
        </label>
      </div>
      <button type="submit" class="btn btn-default">เพิ่ม</button>
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

<jsp:include page="/template/footer.jsp" />