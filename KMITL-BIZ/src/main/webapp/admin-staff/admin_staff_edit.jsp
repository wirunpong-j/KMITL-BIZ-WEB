<%@page import="Model.Staff"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp" />

    <div class="container">
        <div class="jumbotron" id="admin_staff_edit">
            <h1>ดู/แก้ไข ข้อมูลพนักงาน</h1>
        </div>

        <form action='${SITE_URL}/SearchStaff' method="POST">
            <div class="form-group">
                <label for="search_username">ชื่อผู้ใช้ของพนักงาน</label>
                <input type="text" class="form-control information" id="search-staff" name="search" placeholder="ชื่อผู้ใช้" value="${requestScope.staff.getStaff_id()}">
            </div>
            <button type="submit" class="btn btn-info" name="click" id="clickSearch"><i class="fa fa-search" aria-hidden="true"></i> ค้นหา</button>
        </form>
        <br><br>

        <c:choose>
            <c:when test="${requestScope.status == 'true'}">
                <form action="#" method="POST" id="updateStaff">
                    <div class="form-group">
                        <label for="username">ชื่อผู้ใช้ *</label>
                        <input type="text" class="form-control" id="username" name="staff_id" value="${requestScope.staff.getStaff_id()}" placeholder="ชื่อผู้ใช้" readonly="readonly">
                    </div>
                    <div class="form-group">
                        <label for="fname">ชื่อ *</label>
                        <input type="text" class="form-control information" id="fname" name="fname" value="${requestScope.staff.getFirst_name()}" placeholder="ชื่อ">
                    </div>
                    <div class="form-group">
                        <label for="lname">นามสกุล *</label>
                        <input type="text" class="form-control information" id="lname" name="lname" value="${requestScope.staff.getLast_name()}" placeholder="นามสกุล">
                    </div>
                    
                    <c:choose>
                        <c:when test="${requestScope.staff.getRole() == 'ST'}">
                            <c:set var="check1" scope="request" value="checked"></c:set>
                        </c:when>
                        <c:otherwise>
                            <c:set var="check2" scope="request" value="checked"></c:set>
                        </c:otherwise>
                    </c:choose>
                    
                    <div class="form-group">
                        <label for="type">ประเภท * </label>
                        <label class="radio-inline">
                            <input class="information" type="radio" name="role" value="ST" ${check1}> Staff
                        </label>
                        <label class="radio-inline">
                            <input class="information" type="radio" name="role" value="AD" ${check2}> Admin
                        </label>
                    </div>
                    <button type="submit" class="btn btn-info" id="confirmBtn"><i class="fa fa-floppy-o" aria-hidden="true"></i> แก้ไขข้อมูล</button>
                    <button type="submit" class="btn btn-danger" id="deleteBtn" form="deleteStaff"><i class="fa fa-trash" aria-hidden="true"></i> ลบพนักงาน</button>
                </form>
                <form action="#" method="POST" id="deleteStaff"></form>
                <br><br>        
            <!---------------------------เปลี่ยนรหัสผ่าน--------------------------->
                <form action="#" method="POST" id="changePassword">
                    <div class="form-group">
                        <label for="newPass">สร้างรหัสผ่านใหม่ *</label>
                        <input type="password" class="form-control information" id="newPass" name="newPass" placeholder="สร้างรหัสผ่านใหม่">
                    </div>
                    <div class="form-group">
                        <label for="reNewPass">ยืนยันรหัสผ่านใหม่ *</label>
                        <input type="password" class="form-control information" id="reNewPass" name="reNewPass" placeholder="ยืนยันรหัสผ่านใหม่">
                    </div>
                    <button type="submit" class="btn btn-info" id="changePassBtn"><i class="fa fa-key" aria-hidden="true"></i> เปลี่ยนรหัสผ่าน</button>
                </form>
                    
            </c:when>
            <c:when test="${requestScope.status == 'false'}">
                ไม่มีชื่อผู้ใช้ของพนักงานนี้
            </c:when>
            <c:otherwise>
                
            </c:otherwise>
        </c:choose>
                <br><br>

    </div>
<script>
    $('#updateStaff').submit(function(event) {
        event.preventDefault();
        
        if ($('#fname').val() !== '' && $('#lname').val() !== '') {
            alertify.prompt('ยืนยันการทำรายการ', '<h3>กรุณาใส่รหัสผ่านเพื่อยืนยันการแก้ไข</h3>', '', function(evt, value) { 
                if (value === '${sessionScope.staff.getPassword()}') {
                    $('.information').attr("readonly", true);
                    $('#changePassBtn').attr("disabled", true);
                    $('#clickSearch').attr("disabled", true);
                    $('#confirmBtn').attr("disabled", true).html('<i id="spinBtn" class="fa fa-circle-o-notch fa-spin"></i> กำลังบันทึก');
                    $.ajax({
                        type: "POST",
                        url: "${SITE_URL}/UpdateStaff/?action=update&user=${requestScope.staff.getStaff_id()}",
                        datatype: 'json',
                        data: $('#updateStaff').serialize(),
                        success: function(data) {
                            if ($.trim(data) === 'SUCCESS') {
                                $('#confirmBtn').removeClass('btn-info').addClass('btn-success').html('<i class="fa fa-check" aria-hidden="true"></i> Success');
                                alertify.success('แก้ไขข้อมูลของพนักงานสำเร็จ');
                                setTimeout(function() {
                                    window.location = "${SITE_URL}/admin-staff/admin_staff_edit.jsp";
                                }, 2000);

                            } else {
                                alertify.error('ไม่สามารถแก้ไขข้อมูลได้');
                                $('.information').attr("readonly", false);
                                $('#confirmBtn').attr("disabled", false).html('<i class="fa fa-floppy-o" aria-hidden="true"></i> แก้ไขข้อมูล');
                                $('#changePassBtn').attr("disabled", false);
                                $('#clickSearch').attr("disabled", false);
                            }
                        }
                    });
                    
                } else {
                    alertify.error('รหัสผ่านยืนยันไม่ถูกต้อง');
                }
            }, function() { 
                alertify.error('Cancel'); 
            }).set('type', 'password');
        } else {
            alertify.error('กรุณาใส่ข้อมูลให้ครบถ้วน');
        }
        
    });
    
    $('#changePassword').submit(function(event) {
        event.preventDefault();
        if ($('#newPass').val() === $('#reNewPass').val() && $('#newPass').val() !== '' && $('#reNewPass').val() !== '') {
            alertify.prompt('ยืนยันการทำรายการ', '<h3>กรุณาใส่รหัสผ่านเพื่อยืนยันการแก้ไข</h3>', '', function(evt, value) { 
                if (value === '${sessionScope.staff.getPassword()}') {
                    $('.information').attr("readonly", true);
                    $('#changePassBtn').attr("disabled", true).html('<i id="spinBtn" class="fa fa-circle-o-notch fa-spin"></i> กำลังบันทึก');
                    $('#confirmBtn').attr("disabled", true);
                    $('#clickSearch').attr("disabled", true);
                    $.ajax({
                        type: "POST",
                        url: "${SITE_URL}/UpdateStaff/?action=changePass&user=${requestScope.staff.getStaff_id()}",
                        datatype: 'json',
                        data: $('#changePassword').serialize(),
                        success: function(data) {
                            if ($.trim(data) === 'SUCCESS') {
                                $('#changePassBtn').removeClass('btn-info').addClass('btn-success').html('<i class="fa fa-check" aria-hidden="true"></i> Success');
                                alertify.success('เปลี่ยนรหัสผ่านสำเร็จ');
                                setTimeout(function() {
                                    window.location = "${SITE_URL}/admin-staff/admin_staff_edit.jsp";
                                }, 2000);

                            } else {
                                alertify.error('ไม่สามารถเปลี่ยนรหัสผ่านได้');
                                $('.information').attr("readonly", false);
                                $('#confirmBtn').attr("disabled", false);
                                $('#changePassBtn').attr("disabled", false).html('<i class="fa fa-key" aria-hidden="true"></i> เปลี่ยนรหัสผ่าน');
                                $('#clickSearch').attr("disabled", false);
                            }
                        }
                    });
                    
                } else {
                    alertify.error('รหัสผ่านยืนยันไม่ถูกต้อง');
                }
            }, function() { 
                alertify.error('Cancel'); 
            }).set('type', 'password');
            
        } else {
            alertify.error('กรุณากรอกรหัสผ่านใหม่ทั้ง 2 ช่องให้ตรงกัน');
        }
    });
    
    $('#deleteStaff').submit(function(event){
        event.preventDefault();
        alertify.prompt('ยืนยันการทำรายการ', '<h3 style="color:red;">คุณต้องการจะลบพนักงานใช่หรือไม่ ?</h3><h4>กรุณาใส่รหัสผ่านเพื่อยืนยันการลบ</h4>', '', function(evt, value) { 
            if (value === '${sessionScope.staff.getPassword()}') {
                $('.information').attr("readonly", true);
                $('#deleteBtn').attr("disabled", true).html('<i id="spinBtn" class="fa fa-circle-o-notch fa-spin"></i> กำลังลบ');
                $('#changePassBtn').attr("disabled", true);
                $('#confirmBtn').attr("disabled", true);
                $('#clickSearch').attr("disabled", true);
                
                $.ajax({
                    type: "POST",
                    url: "${SITE_URL}/DeleteStaff/?user=${requestScope.staff.getStaff_id()}",
                    datatype: 'json',
                    data: $('#deleteStaff').serialize(),
                    success: function(data) {
                        if ($.trim(data) === 'SUCCESS') {
                            $('#deleteBtn').html('<i class="fa fa-check" aria-hidden="true"></i> Success');
                            alertify.success('ลบสำเร็จ');
                            setTimeout(function() {
                                window.location = "${SITE_URL}/admin-staff/admin_staff_edit.jsp";
                            }, 2000);

                        } else {
                            alertify.error('ไม่สามารถลบได้');
                            $('.information').attr("readonly", false);
                            $('#confirmBtn').attr("disabled", false);
                            $('#changePassBtn').attr("disabled", false);
                            $('#clickSearch').attr("disabled", false);
                            $('#deleteBtn').attr("disabled", false).html('<i class="fa fa-trash" aria-hidden="true"></i> ลบพนักงาน');
                        }
                    }
                });

            } else {
                alertify.error('รหัสผ่านยืนยันไม่ถูกต้อง');
            }
        }, function() { 
            alertify.error('Cancel'); 
        }).set('type', 'password');
    });
</script>

<jsp:include page="/template/footer.jsp" />