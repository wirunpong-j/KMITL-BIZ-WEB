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
      <h1>สรุปข้อมูลการใช้งาน</h1>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="admin_panel" id="admin_data_export">
          <i class="fa fa-user-plus fa-5x" aria-hidden="true"></i>
          <h3>ส่งออกข้อมูลเป็น Excel</h3>
        </div>
      </div>
    </div>
  </div>

  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>

</html>


<jsp:include page="template/footer.jsp" />