<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp" />

    <div class="container">
        <div class="jumbotron" id="admin_staff_add">
            <h1>เพิ่มพนักงานใหม่</h1>
        </div>

        <form action="#" method="POST" id="addStaff">
            <div class="form-group">
                <label for="fname">ชื่อ *</label>
                <input type="text" class="form-control information" id="fname" name="fname" placeholder="ชื่อ">
            </div>
            <div class="form-group">
                <label for="lname">นามสกุล *</label>
                <input type="text" class="form-control information" id="lname" name="lname" placeholder="นามสกุล">
            </div>
            <div class="form-group">
                <label for="username">ชื่อผู้ใช้ *</label>
                <input type="text" class="form-control information" id="username" name="username" placeholder="ชื่อผู้ใช้">
            </div>
            <div class="form-group">
                <label for="password">รหัสผ่าน *</label>
                <input type="password" class="form-control information" id="password" name="password" placeholder="รหัสผ่าน">
            </div>
            <div class="form-group">
                <label for="password">ยืนยันรหัสผ่าน *</label>
                <input type="password" class="form-control information" id="confirm-password" placeholder="ยืนยันรหัสผ่าน">
            </div>
            <div class="form-group">
                <label for="type">ประเภท * </label>
                <label class="radio-inline">
                    <input class="information" type="radio" name="role" value="ST" checked="checked"> Staff
                </label>
                <label class="radio-inline">
                    <input class="information" type="radio" name="role" value="AD"> Admin
                </label>
            </div>
            <button type="submit" class="btn btn-info" id="confirmBtn"><i class="fa fa-floppy-o" aria-hidden="true"></i> บันทึก</button>
        </form>
    </div>
    
<script>
    $('#addStaff').submit(function(event) {
        event.preventDefault();
        
        if ($('#password').val() === $('#confirm-password').val() && $('#password').val() !== '' && $('#confirm-password').val() !== '') {
            alertify.confirm('ยืนยันการทำรายการ', '<h4>คุณต้องการจะเพิ่มพนักงานใช่หรือไหม ?</h4>', function(){
                $('.information').attr("readonly", true);
                $('#confirmBtn').attr("disabled", true).html('<i id="spinBtn" class="fa fa-circle-o-notch fa-spin"></i> กำลังบันทึก');
                $.ajax({
                    type: "POST",
                    url: "${SITE_URL}/AddStaff",
                    datatype: 'json',
                    data: $('#addStaff').serialize(),
                    success: function(data) {
                        if ($.trim(data) === 'SUCCESS') {
                            $('#confirmBtn').removeClass('btn-info').addClass('btn-success').html('<i class="fa fa-check" aria-hidden="true"></i> Success');
                            alertify.success('เพิ่มพนักงานสำเร็จ');
                            setTimeout(function() {
                                window.location = "${SITE_URL}/admin-staff/admin_staff_add.jsp";
                            }, 1000);
                            
                        } else if ($.trim(data) === 'FAILED') {
                            alertify.error('ไม่สามารถเพิ่มพนักงานได้');
                            $('.information').attr("readonly", false);
                            $('#confirmBtn').attr("disabled", false).html('<i class="fa fa-floppy-o" aria-hidden="true"></i> บันทึก');
                        } else {
                            alertify.error('ชื่อผู้ใช้ (Username) ได้ถูกใช้ไปแล้ว');
                            $('.information').attr("readonly", false);
                            $('#confirmBtn').attr("disabled", false).html('<i class="fa fa-floppy-o" aria-hidden="true"></i> บันทึก');
                        }
                    }
                });
            }, function(){ 
                alertify.error('Cancel');
            });
        
        } else {
            alertify.error('กรุณากรอกรหัสผ่านทั้ง 2 ช่องให้ตรงกัน');
        }
        
        
    });
</script>

<jsp:include page="/template/footer.jsp" />