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
        <input type="text" class="form-control" id="search_cust" placeholder="รหัสรับบริการ">
      </div>
      <button type="submit" class="btn btn-default">ค้นหา</button>
    </form>
    <br><br>

    <form>
      <div class="form-group">
        <label for="fname">ชื่อจริง</label>
        <input type="text" class="form-control" id="fname" placeholder="ชื่อจริง">
      </div>
      <div class="form-group">
        <label for="lname">นามสกุล</label>
        <input type="text" class="form-control" id="lname" placeholder="นามสกุล">
      </div>
      <div class="form-group">
        <label for="tel">เบอร์โทรศัพท์</label>
        <input type="text" class="form-control" id="tel" placeholder="เบอร์โทรศัพท์">
      </div>
      <div class="form-group">
        <label for="email">อีเมล</label>
        <input type="email" class="form-control" id="email" placeholder="อีเมล">
      </div>
      <div class="form-group">
        <label for="plate">เลขทะเบียนรถ (ถ้ามี)</label>
        <input type="text" class="form-control" id="plate" placeholder="เลขทะเบียนรถ (ถ้ามี)">
      </div>
      <div class="form-group">
        <label for="type">ประเภท</label>
        <label class="radio-inline">
          <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> นักศึกษา
        </label>
                <label class="radio-inline">
          <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> พนักงาน
        </label>
                <label class="radio-inline">
          <input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3" checked> บุคคลภายนอก
        </label>
      </div>
      <div class="form-group">
        <label for="id">รหัสบัตรประชาชน/รหัสนักศึกษา</label>
        <input type="text" class="form-control" id="id" placeholder="รหัสบัตรประชาชน/รหัสนักศึกษา">
      </div>
      <button type="submit" class="btn btn-default">บันทึก</button>
      <button type="submit" class="btn btn-danger" form="deleteuser">ลบลูกค้า</button>
    </form>

    <form class="" action="index.html" method="post" id="deleteuser">

    </form>

  </div>
  <script src="https://use.fontawesome.com/211152dd25.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>

</html>


<jsp:include page="template/footer.jsp" />