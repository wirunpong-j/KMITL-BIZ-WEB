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
    <div class="jumbotron" id="admin_cust_history">
      <h1>ดูข้อมูลการจอง</h1>
    </div>

    <form>
      <div class="form-group">
        <label for="search_cust">รหัสรับบริการ</label>
        <input type="text" class="form-control" id="search_cust" placeholder="รหัสรับบริการ">
      </div>
      <button type="submit" class="btn btn-default">ค้นหา</button>
    </form>
    <br><br><br>


    <h2>รหัสรับบริการ 000211 นาย สมหมาย คลายตัว</h2>
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
      <tr>
        <td>000231</td>
        <td>12 มกราคม 2560 12:31:02</td>
        <td>15 มกราคม 2560</td>
        <td>A32</td>
        <td>ไก่ย่าง</td>
        <td>300</td>
        <td>20</td>
        <td>หลอดไฟเพิ่มเติม 1 หลอด</td>
      </tr>
    </table>

    <button type="submit" class="btn btn-default">บันทึกเป็น Excel</button>
    <button type="submit" class="btn btn-default">บันทึกเป็น Pdf</button>

  </div>
  <script src="https://use.fontawesome.com/211152dd25.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>

</html>

<jsp:include page="template/footer.jsp" />