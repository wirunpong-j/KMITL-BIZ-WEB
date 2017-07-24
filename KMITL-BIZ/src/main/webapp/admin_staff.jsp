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
  <script src="https://use.fontawesome.com/211152dd25.js"></script>
</head>

<body>
  <div class="container">
    <div class="jumbotron">
      <h1>จัดการพนักงาน</h1>
    </div>
    <div class="row">
      <a href="admin_staff_add.jsp">
        <div class="col-md-4">
          <div class="admin_panel" id="admin_staff_add">
            <i class="fa fa-user-plus fa-5x" aria-hidden="true"></i>
            <h3>เพิ่มพนักงานใหม่</h3>
          </div>
        </div>
      </a>
      <a href="admin_staff_edit.jsp">
        <div class="col-md-4">
          <div class="admin_panel" id="admin_staff_edit">
            <i class="fa fa-pencil fa-5x" aria-hidden="true"></i>
            <h3>ดู/แก้ไข ข้อมูลพนักงาน</h3>
          </div>
        </div>
      </a>
      <a href="admin_staff_list.jsp">  
        <div class="col-md-4">
          <div class="admin_panel" id="admin_staff_history">
            <i class="fa fa-file-text fa-5x" aria-hidden="true"></i>
            <h3>รายการพนักงาน</h3>
          </div>
        </div>
      </a>
    </div>
  </div>

  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>

</html>


<jsp:include page="template/footer.jsp" />