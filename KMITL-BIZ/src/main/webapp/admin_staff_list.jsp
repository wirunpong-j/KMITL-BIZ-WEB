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
    <div class="jumbotron" id="admin_staff_history">
      <h1>รายการพนักงาน</h1>
    </div>

    <table class="table table-striped">
      <tr>
        <th>ชื่อผู้ใช้</th>
        <th>รหัสผ่าน</th>
        <th>ชื่อจริง</th>
        <th>นามสกุล</th>
        <th>ประเภท</th>
      </tr>
      <tr>
        <td>admin001</td>
        <td>123455</td>
        <td>สมใจ</td>
        <td>ไปน่าน</td>
        <td>Staff</td>
      </tr>
      <tr>
        <td>notadmin</td>
        <td>111111</td>
        <td>สมดวง</td>
        <td>พวงใหญ่</td>
        <td>Admin</td>
      </tr>
    </table>
  </div>

  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  <script src="https://use.fontawesome.com/211152dd25.js"></script>
</body>

</html>

<jsp:include page="template/footer.jsp" />