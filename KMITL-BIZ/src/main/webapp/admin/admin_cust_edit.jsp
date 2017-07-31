<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp" />

  <div class="container">
    <div class="jumbotron" id="admin_cust_edit">
        <h1><i class="fa fa-pencil" aria-hidden="true"></i> ดู/แก้ไข ข้อมูลลูกค้า</h1>
    </div>

    <form action="${SITE_URL}/SearchCustomer" method="POST">
        <div class="form-group">
            <label for="search_cust">รหัสรับบริการ</label>
            <input type="text" class="form-control information" id="search_cust" name="search" placeholder="รหัสรับบริการ" value="${requestScope.cust.getCust_id_str()}">
        </div>
        <button type="submit" class="btn btn-info">ค้นหา</button>
    </form>
    <br><br>

    <c:choose>
        <c:when test="${requestScope.status == 'true'}">
            <form action="#" method="POST" id="updateCustomer">
                <div class="form-group">
                    <label for="fname">ชื่อ - นามสกุล *</label>
                    <input type="text" class="form-control information" id="fullname" name="fullname" value="${requestScope.cust.getFullname()}" placeholder="ชื่อ - นามสกุล">
                </div>
                <div class="form-group">
                    <label for="tel">เบอร์โทรศัพท์ *</label>
                    <input type="text" class="form-control information" id="tel" name="tel" value="${requestScope.cust.getTel()}" placeholder="เบอร์โทรศัพท์">
                </div>
                <div class="form-group">
                    <label for="email">อีเมล *</label>
                    <input type="email" class="form-control information" id="email" name="email" value="${requestScope.cust.getEmail()}" placeholder="อีเมล">
                </div>
                <div class="form-group">
                    <label for="plate">เลขทะเบียนรถ (ถ้ามี)</label>
                    <input type="text" class="form-control information" id="plate" name="plate" value="${requestScope.cust.getVehicle()}" placeholder="เลขทะเบียนรถ (ถ้ามี)">
                </div>
                <div class="form-group">
                    <c:choose>
                        <c:when test="${requestScope.cust.getCust_type() == 'STUDENT'}">
                            <c:set var="custType1" scope="request" value="checked"></c:set>
                        </c:when>
                        <c:when test="${requestScope.cust.getCust_type() == 'STAFF'}">
                            <c:set var="custType2" scope="request" value="checked"></c:set>
                        </c:when>
                        <c:otherwise>
                            <c:set var="custType3" scope="request" value="checked"></c:set>
                        </c:otherwise>
                    </c:choose>
                    <label for="type">ประเภท * </label>
                    <label class="radio-inline">
                        <input type="radio" class="information" name="cust_type" value="STUDENT" ${custType1}> นักศึกษา
                    </label>
                    <label class="radio-inline">
                        <input type="radio" class="information" name="cust_type" value="STAFF" ${custType2}> พนักงาน
                      </label>
                    <label class="radio-inline">
                        <input type="radio" class="information" name="cust_type" value="OUTSIDER" ${custType3}> บุคคลภายนอก
                    </label>
                </div>
                <div class="form-group">
                    <label for="id">รหัสบัตรประชาชน/รหัสนักศึกษา *</label>
                    <c:choose>
                        <c:when test="${requestScope.cust.getCust_type() == 'STUDENT'}">
                            <input type="text" class="form-control information" id="id" name="id" value="${requestScope.cust.getStudent_id()}" placeholder="รหัสบัตรประชาชน/รหัสนักศึกษา">
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control information" id="id" name="id" value="${requestScope.cust.getCitizen_id()}" placeholder="รหัสบัตรประชาชน/รหัสนักศึกษา">
                        </c:otherwise>
                    </c:choose>
                    
                </div>
                <button type="submit" class="btn btn-info" id="confirmBtn">บันทึก</button>
            </form>
        </c:when>
        <c:when test="${requestScope.status == 'false'}">
            <p>ไม่มีรหัสรับบริการนี้</p>
        </c:when>
        <c:otherwise>
            
        </c:otherwise>
    </c:choose>
            <br>
    

<!--    <form action="${SITE_URL}/DeleteCustomer" method="POST" id="deleteuser">
        <div class="form-group">
            <label for="plate">ใส่เบอร์โทรศัพท์ลูกค้าเพื่อทำการลบ</label>
            <input type="text" class="form-control" id="tele" name="tel" placeholder="เบอร์โทรศัพท์">
        </div>
        <button type="submit" class="btn btn-danger" form="deleteuser">ลบลูกค้า</button>
    </form>-->
  </div>

<script>
    $('#updateCustomer').submit(function(event) {
        event.preventDefault();
        
        alertify.confirm('ยืนยันการทำรายการ', '<h4>คุณต้องการจะบันทึกการแก้ไขครั้งนี้ใช่หรือไม่ ?</h4>', function(){
            $('.information').attr("readonly", true);
            $('#confirmBtn').attr("disabled", true).html('<i id="spinBtn" class="fa fa-circle-o-notch fa-spin"></i> กำลังบันทึก');
            $.ajax({
                type: "POST",
                url: "${SITE_URL}/UpdateCustomer/?custid=${requestScope.cust.getCust_id()}",
                datatype: 'json',
                data: $('#updateCustomer').serialize(),
                success: function(data) {
                    if ($.trim(data) === 'SUCCESS') {
                        $('#confirmBtn').removeClass('btn-info').addClass('btn-success').html('<i id="spinBtn"></i> Success');
                        alertify.success('บันทึกข้อมูลสำเร็จ');
                        setTimeout(function() {
                            window.location = "${SITE_URL}/admin/admin_cust_edit.jsp";
                        }, 2000);
                    } else {
                        alertify.error('ไม่สามารถแก้ไขได้');
                        $('.information').attr("readonly", false);
                        $('#confirmBtn').attr("disabled", false).removeClass('btn-success').addClass('btn-info').html('<i id="spinBtn"></i> บันทึก');
                    }
                }
            });
        }, function(){ 
            alertify.error('Cancel');
        });
    });
</script>

<jsp:include page="/template/footer.jsp" />