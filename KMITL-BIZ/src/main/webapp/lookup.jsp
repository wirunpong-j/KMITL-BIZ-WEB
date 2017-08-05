<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template/header.jsp" />
<div></div>
<div>
    <div class="container">
        <div class="row">            
            <form class="form-horizontal" action="${SITE_URL}/ShowAllArea/" method="POST">
                <div class="form-group">
                    <label for="type" class="col-sm-2 control-label">เลือกรูปแบบการจองพื้นที่ : </label>
                    <div class="col-sm-4">
                        <select class="form-control" style="width:300px" id="type" name="type">
                            <c:forEach var="i" begin="0" end="7">
                                <c:choose>
                                    <c:when test="${requestScope.type == i}">
                                        <option value="${i}" text="${requestScope.allRentType[i]}" selected="selected">${requestScope.allRentType[i]}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${i}" text="${requestScope.allRentType[i]}">${requestScope.allRentType[i]}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-2">
                        <button type="submit" class="btn btn-primary c-hide" id="changeRent">ยืนยัน</button>
                    </div>
                </div>
            </form>
        </div>
                
        <div class="row" style="padding:15px;">
            <div class="col-md-9">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <c:forEach var="i" begin="1" end="165">
                                    <th><button class="btn btn-danger btn-xs btn-area c-hide" type="button">X${i}</button></th>
                                </c:forEach>
                            </tr>
                        </thead>
                        <tbody>
<!---------------------------------------------------Map Area--------------------------------------------------------------------------------->
                            <c:forEach var="area" items="${sessionScope.allArea}">
                                <tr>
                                <c:forEach var="a" items="${area}">
                                    <c:choose>
                                        <c:when test="${a == 'X'}">
                                            <td class="blank-space"></td>
                                        </c:when>
                                        <c:when test="${a == 'B'}">
                                            <td class="black-area"></td>
                                        </c:when>    
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${!requestScope.allZone.containsKey(a)}">
                                                    <td><button class="btn btn-success btn-xs btn-area not-rent c-hide" id="${a}" type="button">${a}</button></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td><button type="button" class="btn btn-danger btn-xs btn-area rented c-hide" id="${a}" name="${a}">${a}</button></td>
                                                    <input type="hidden" id="info-${a}" 
                                                        value="${a},${requestScope.allZone[a][1].getProduct_name()},${requestScope.allZone[a][2].getCust_id_str()},${requestScope.allZone[a][2].getFullname()},${requestScope.allZone[a][2].getCust_type_Str()},${requestScope.allZone[a][2].getStudent_id()},${requestScope.allZone[a][2].getCitizen_id()},${requestScope.allZone[a][2].getTel()},${requestScope.allZone[a][2].getEmail()},${requestScope.allZone[a][2].getVehicle()},${requestScope.allZone[a][0].getOrder_id()}">
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                </tr>
                            </c:forEach>

<!---------------------------------------------------END--------------------------------------------------------------------------------->
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-3" style="margin:auto 0;">
                <form action="#" method="POST" id="deleteOrderForm">
                    <div class="form-group">
                        <button class="btn btn-info" type="button" style="width:100%;" id="area">-</button>
                        <h5 style="color:rgb(189,189,189);">สินค้าที่วางขาย </h5>
                        <h4 id="product">-</h4>
                        <h5 style="color:rgb(189,189,189);">รหัสรับบริการ </h5>
                        <h4 id="custid">-</h4>
                        <h5 style="color:rgb(189,189,189);">ชื่อผู้ใช้บริการ </h5>
                        <h4 id="fullname">-</h4>
                        <h5 style="color:rgb(189,189,189);">ประเภท </h5>
                        <h4 id="custtype">-</h4>
                        <h5 style="color:rgb(189,189,189);">รหัสนักศึกษา </h5>
                        <h4 id="studentid">-</h4>
                        <h5 style="color:rgb(189,189,189);">เลขบัตรประชาชน </h5>
                        <h4 id="cardid">-</h4>
                        <h5 style="color:rgb(189,189,189);">เบอร์โทรศัพท์ </h5>
                        <h4 id="tel">-</h4>
                        <h5 style="color:rgb(189,189,189);">อีเมล </h5>
                        <h4 id="email">-</h4>
                        <h5 style="color:rgb(189,189,189);">ทะเบียนรถ </h5>
                        <h4 id="vehicle">-</h4>
                    </div>
                    <input type="hidden" id="orderID" name="orderID" value="">
                    <button type="submit" class="btn btn-danger" style="width:100%;" id="deleteOrderBtn" disabled="disabled"><i class="fa fa-ban" aria-hidden="true"></i> ยกเลิกการจองพื้นที่</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $('#deleteOrderForm').submit(function(event) {
        event.preventDefault();
        $('.c-hide').attr('disabled', true);
        $('#deleteOrderBtn').attr('disabled', true).html('<i id="spinBtn" class="fa fa-circle-o-notch fa-spin"></i> กรุณารอสักครู่');
        $.ajax({
            type: "POST",
            url: "${SITE_URL}/SearchZoneInOrder",
            datatype: 'json',
            data: $('#deleteOrderForm').serialize(),
            success: function(data) {
                var textRes = $.trim(data);
                alertify.confirm('ยืนยันการทำรายการ', '<h3>คุณต้องการจะยกเลิกการจองพื้นที่ ใช่หรือไม่ ?</h3>\n\
                                                     <h4 style="color:red">หมายเหตุ : พื้นที่ที่ลูกค้าท่านนี้ได้จอง'+$('#type option:selected').text()+'</h4>\
                                                     <h4 style="color:red">ได้แก่พื้นที่ '+ textRes +' จะถูกยกเลิกการจองด้วย</h4>', function(){
                    $.ajax({
                        type: "POST",
                        url: "${SITE_URL}/CancleOrder",
                        datatype: 'json',
                        data: $('#deleteOrderForm').serialize(),
                        success: function(result) {
                            if ($.trim(result) === 'SUCCESS') {
                                $('#deleteOrderBtn').html('<i class="fa fa-check" aria-hidden="true"></i> Success');
                                alertify.alert('ทำรายการสำเร็จ', '<h3 style="color:red">พื้นที่ '+ textRes + ' ถูกยกเลิกการจองแล้ว</h3>', function(){ 
                                    setTimeout(function() {
                                        window.location = "${SITE_URL}/ShowAllArea/?type="+$('#type option:selected').val();
                                    }, 1000);
                                });
                                
                            } else {
                                $('.c-hide').attr('disabled', false);
                                $('#deleteOrderBtn').attr('disabled', false).html('<i class="fa fa-ban" aria-hidden="true"></i> ยกเลิกการจองพื้นที่');
                                alertify.error('ไม่สามารถยกเลิกการจองพื้นที่นี้ได้');
                            }
                        }
                    });
                    
                }, function(){
                    $('.c-hide').attr('disabled', false);
                    $('#deleteOrderBtn').attr('disabled', false).html('<i class="fa fa-ban" aria-hidden="true"></i> ยกเลิกการจองพื้นที่');
                    alertify.error('Cancel');
                });
            }
        });
    });
    
    $('.rented').click(function() {
        var area = this.id;
        var info = $('#info-' + area).val().split(',');
        $('#area').text(info[0]);
        $('#product').text(info[1]);
        $('#custid').text(info[2]);
        $('#fullname').text(info[3]);
        $('#custtype').text(info[4]);
        $('#studentid').text(info[5]);
        $('#cardid').text(info[6]);
        $('#tel').text(info[7]);
        $('#email').text(info[8]);
        $('#vehicle').text(info[9]);
        
        $('#deleteOrderBtn').attr('disabled', false);
        $('#orderID').val(info[10]);
    });
    
    $('.not-rent').click(function() {
        var area = this.id;
        $('#area').text(area);
        $('#product').text('ไม่มีข้อมูล');
        $('#custid').text('ไม่มีข้อมูล');
        $('#fullname').text('ไม่มีข้อมูล');
        $('#custtype').text('ไม่มีข้อมูล');
        $('#studentid').text('ไม่มีข้อมูล');
        $('#cardid').text('ไม่มีข้อมูล');
        $('#tel').text('ไม่มีข้อมูล');
        $('#email').text('ไม่มีข้อมูล');
        $('#vehicle').text('ไม่มีข้อมูล');
        $('#deleteOrderBtn').attr('disabled', true);
        
    });
</script>

<jsp:include page="template/footer.jsp" />
