<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp" />

  <div class="container">
      
    <div class="jumbotron" id="admin_cust_add">
        <h1><i class="fa fa-user-plus" aria-hidden="true"></i> เพิ่มลูกค้าใหม่</h1>
    </div>

    <form action="#" method="POST" id="addCustomer">
        <div class="form-group">
            <label for="fname">ชื่อ - นามสกุล *</label>
            <input type="text" class="form-control information" id="fullname" name="fullname" placeholder="ชื่อ - นามสกุล">
        </div>
        <div class="form-group">
            <label for="tel">เบอร์โทรศัพท์ *</label>
            <input type="text" class="form-control information" id="tel" name="tel" placeholder="เบอร์โทรศัพท์">
        </div>
        <div class="form-group">
            <label for="email">อีเมล (ถ้ามี)</label>
            <input type="email" class="form-control information" id="email" name="email" placeholder="อีเมล (ถ้ามี)">
        </div>
        <div class="form-group">
            <label for="plate">เลขทะเบียนรถ (ถ้ามี)</label>
            <input type="text" class="form-control information" id="plate" name="plate" placeholder="เลขทะเบียนรถ (ถ้ามี)">
        </div>
        <div class="form-group">
            <label for="type">ประเภท * </label>
            <label class="radio-inline">
                <input class="information" type="radio" name="cust_type" value="STUDENT"> นักศึกษา
            </label>
            <label class="radio-inline">
                <input class="information" type="radio" name="cust_type" value="STAFF"> พนักงาน
            </label>
            <label class="radio-inline">
                <input class="information" type="radio" name="cust_type" value="OUTSIDER" checked> บุคคลภายนอก
            </label>
        </div>
        <div class="form-group">
            <label class="student_id" for="studentid">รหัสนักศึกษา *</label>
            <input type="text" class="form-control information student_id" id="studentid" name="studentid" placeholder="รหัสนักศึกษา (8 หลัก)" maxlength="8">
            
            <label class="citizen_id" for="citizenid">เลขบัตรประชาชน *</label>
            <input type="text" class="form-control information citizen_id" id="citizenid" name="citizenid" placeholder="เลขบัตรประชาชน (13 หลัก)" maxlength="13">
        </div>
        
        <button type="submit" class="btn btn-info" id="confirmBtn"><i class="fa fa-floppy-o" aria-hidden="true"></i> บันทึก</button>
        
    </form>

  </div>

<script>
    $(document).ready(function() {
        $('.student_id').hide();
        $('.citizen_id').show();
    });
    
    $('#addCustomer input').on('change', function() {
        if ($('input[name=cust_type]:checked', '#addCustomer').val() === 'STUDENT') {
            $('.student_id').show();
            $('.citizen_id').hide();
        } else {
            $('.student_id').hide();
            $('.citizen_id').show();
        }
         
    });
    
    $('#addCustomer').submit(function(event) {
        event.preventDefault();
        
        alertify.confirm('ยืนยันการทำรายการ', '<h4>คุณต้องการจะเพิ่มลูกค้าท่านนี้ใช่หรือไม่</h4>', function(){
            $('.information').attr("readonly", true);
            $('#confirmBtn').attr("disabled", true).html('<i id="spinBtn" class="fa fa-circle-o-notch fa-spin"></i> กำลังบันทึก');
            $.ajax({
                type: "POST",
                url: "${SITE_URL}/AddCustomer",
                datatype: 'json',
                data: $('#addCustomer').serialize(),
                success: function(data) {
                    if ($.trim(data) !== 'FAILED') {
                        $('#confirmBtn').removeClass('btn-info').addClass('btn-success').html('<i id="spinBtn"></i> Success');
                        alertify.alert('ทำรายการสำเร็จ', '<h3>รหัสรับบริการที่ได้รับ คือ ' + $.trim(data) + ' </h3>', function(){ 
                            setTimeout(function() {
                                window.location = "${SITE_URL}/admin-customer/admin_cust_add.jsp";
                            }, 1000);
                        });
                    } else {
                        alertify.error('ไม่สามารถเพิ่มลูกค้าได้');
                    }
                }
            });
        }, function(){ 
            alertify.error('Cancel');
        });
    });
</script>

<jsp:include page="/template/footer.jsp" />