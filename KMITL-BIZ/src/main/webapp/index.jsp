<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template/header.jsp" />

<div class="container">
    <div class="row">
        <div class="col-md-9" id="allProduct">
            <div class="back_panel">
                <div>
                    <%-- <c:choose>
                        <c:when test="${sessionScope.status == 'RENT'}">
                            <c:forEach var="pro" items="${sessionScope.allProduct}">
                                <button class="btn btn-default btn-product" type="button" style="margin:2px 0;" disabled>${pro.getProduct_name()}</button>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="pro" items="${sessionScope.allProduct}">
                                <button class="btn btn-default btn-product" type="button" style="margin:2px 0;">${pro.getProduct_name()}</button>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose> --%>
                    <ul class="nav nav-pills" id="myTab">
                        <li class="active dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown">เลือกกลุ่มสินค้า <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <c:forEach var="grPro" items="${sessionScope.allGroupPro}">
                                    <li><a class="proGroup" href="#group${grPro.getGroup_id()}" data-toggle="tab">${grPro.getGroup_name()}</a></li>
                                </c:forEach>
                            </ul>
                        </li>
                    </ul>
                    
                    <div class="tab-content">
                        <c:forEach var="grPro" items="${sessionScope.allGroupPro}">
                            <div class="tab-pane fade in" id="group${grPro.getGroup_id()}">
                                <div>
                                    <h1 style="display: inline-block;">${grPro.getGroup_name()}</h1>
                                    <button class="btn btn-success btn-inline btn-addPro" style="float: right; margin-top: 20px" value="${grPro.getGroup_id()},${grPro.getGroup_name()}"><i class="fa fa-plus" aria-hidden="true"></i>  เพิ่มสินค้าใหม่ในกลุ่มนี้</button>
                                </div>
                                <div class="row funkyradio" id="funky${grPro.getGroup_id()}">
                                    <c:forEach var="pro" items="${grPro.getAllProduct()}">
                                        <span class="funkyradio-success col-sm-3">
                                            <input class="checkbox1" type="checkbox" name="checkbox" id="pro-${pro.getProduct_id()}" value="${pro.getProduct_name()}"/>
                                            <label for="pro-${pro.getProduct_id()}">${pro.getProduct_name()}</label>
                                        </span>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="back_panel">
                <c:choose>
                    <c:when test="${sessionScope.status == 'RENT'}">
                        <div class="form-group">
                            <label for="product">ชื่อสินค้า</label>
                            <input class="form-control" type="text" id="product" name="product" placeholder="ชื่อสินค้า" value="${sessionScope.product.getProduct_name()}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="customer">รหัสรับบริการ</label>
                            <input class="form-control" type="text" id="customer" name="customer" placeholder="รหัสรับบริการ" value="${sessionScope.customer.getCust_id_str()}" readonly>
                        </div>
                        <button class="btn btn-primary" type="button" style="width:100%;" disabled>ทำการเลือกสินค้าแล้ว </button>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group">
                            <label for="product">ชื่อสินค้า</label>
                            <input disabled="disabled" class="form-control" type="text" name="product" id="product" data-role="tagsinput">
                        </div>
                        <div class="form-group">
                            <label for="customer">รหัสรับบริการ</label>
                            <input class="form-control" type="text" name="customer" id="customer" placeholder="รหัสรับบริการ">
                        </div>
                        <button class="btn btn-primary" type="button" id="saveBtn" style="width:100%;">บันทึก </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <br><br>
    
    <c:choose>
        <c:when test="${sessionScope.status == 'RENT'}">
            <div class="row">
                <form class="form-horizontal" action="${SITE_URL}/ShowRentArea" method="POST">
                    <div class="form-group">
                        <label for="selectRent" class="col-sm-2 control-label">เลือกรูปแบบการจองพื้นที่ : </label>
                        <div class="col-sm-4">
                            <select class="form-control rented" style="width:300px" id="selectRent" name="selectRent">
                                <c:forEach var="i" begin="1" end="4">
                                    <c:set var="key" scope="session" value="R${i}"></c:set>
                                    <c:choose>
                                        <c:when test="${key == sessionScope.typeRent}">
                                            <option value="${key}" text="${sessionScope.allRentType[key]}" selected="selected">${sessionScope.allRentType[key]}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${key}" text="${sessionScope.allRentType[key]}">${sessionScope.allRentType[key]}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <button type="submit" class="btn btn-primary rented" id="changeRent">ยืนยัน</button>
                        </div>
                    </div>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            
        </c:otherwise>
    </c:choose>
    

    <div class="row">
        <c:choose>
            <c:when test="${sessionScope.statusShow != 'true'}">
                <div class="col-md-9"><img class="img-responsive" src="assets/img/Caremal-Market.jpg"></div>
            </c:when>
            <c:otherwise>
            <div class="col-md-9">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <c:forEach var="i" begin="1" end="165">
                                    <th><button class="btn btn-danger btn-xs btn-area rented" type="button">X${i}</button></th>
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
                                                <c:when test="${!sessionScope.allZone.containsKey(a)}">
                                                    <td><button class="btn btn-success btn-xs btn-area btn-unselect rented" id="${a}" name="${a}" type="button">${a}</button></td>
                                                </c:when>
                                                <c:when test="${sessionScope.allZone[a].getProduct_id() == sessionScope.customer.getProduct_id()}">
                                                    <td><button class="btn btn-warning btn-xs btn-area rented" type="button" disabled>${a}</button></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td><button class="btn btn-danger btn-xs btn-area rented" type="button" disabled>${a}</button></td>
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
            </c:otherwise>
        </c:choose>
        <div class="col-md-3" style="margin:auto 0;">
            <div class="back_panel">
                <c:choose>
                    <c:when test="${sessionScope.status == 'RENT'}">
                    <div class="form-group">
                        <button class="btn btn-primary btn-block btn-rent" id="showArea" type="button" style="width:80%; float:left">-</button>
                        <button class="btn btn-danger btn-block btn-rent" id="clearBtn" type="button" style="width:20%;"><i class="fa fa-eraser" aria-hidden="true"></i></button>
                    </div>

                    <div class="form-group">
                        <button class="btn btn-success btn-rent" type="button" id="confirmBtn" style="width:100%;" value="${sessionScope.customer.getPrice()},${sessionScope.count}" disabled>ยืนยัน/พิมพ์ใบเสร็จ</button>
                    </div>

                    <div class="form-group">
                        <a href="#" type="button" class="btn btn-default" style="width:100%;" onclick="window.open('${SITE_URL}/ShowAllArea/?type=0', '_blank', 'fullscreen=yes'); return false;">เรียกดูข้อมูล </a>
                    </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group">
                            <button class="btn btn-primary btn-block" type="button" style="width:100%;" disabled>- </button>
                        </div>

                        <div class="form-group">
                            <button class="btn btn-success" type="button" style="width:100%;" disabled>ยืนยัน/พิมพ์ใบเสร็จ</button>
                        </div>

                        <div class="form-group">
                            <a href="#" type="button" class="btn btn-default" style="width:100%;" onclick="window.open('${SITE_URL}/ShowAllArea/?type=0', '_blank', 'fullscreen=yes'); return false;">เรียกดูข้อมูล </a>
                        </div>
                    </c:otherwise>
                </c:choose>

                <div class="form-group"></div>

                <h5 class="text-center">พนักงาน: ${sessionScope.staff.getFirst_name()} ${sessionScope.staff.getLast_name()}</h5>
                <h5 class="text-center"><div id="time_display"></div></h5>
            </div>
        </div>
    </div>
<br><br>

</div>
            
                        
<script>
    var allArea = [];
    var count = 1;
    var addCost;
    
    $('.btn-addPro').click(function() {
        var btnVal = $(this).val().split(',');
        alertify.prompt('เพิ่มสินค้าใหม่ ในกลุ่ม " ' + btnVal[1] + ' "', '<h3>ใส่ชื่อสินค้า</h3> ', ''
               ,function(evt, value) { 
                    $.ajax({
                        type: "POST",
                        url: "${SITE_URL}/AddProduct",
                        data: {'group_id': btnVal[0], 'product_name': value},
                        success: function(data) {
                            if ($.trim(data) !== 'FAILED') {
                                
                                alertify.alert('เพิ่มสินค้าใหม่เสร็จสิ้น', 'ทำการเพิ่มสินค้า <strong>"' + value + '"</strong> ลงในกลุ่ม <strong>"' + btnVal[1] + '"</strong> เรียบร้อยแล้ว'
                                    , function(){ 
                                        alertify.success('Ok'); 
                                    });
                                    
                                setTimeout(function() {
                                    window.location = "${SITE_URL}/Authentication";
                                }, 2000);
                                
//                                $('#funky' + btnVal[0]).append('<span class="funkyradio-success col-sm-3">\n\
//                                <input class="checkbox1" type="checkbox" name="checkbox" id="pro-' + $.trim(data) + '" value="' + value + '"/> \n\
//                                <label for="pro-' + $.trim(data) + '">' + value + '</label></span>');
                                
                            } else {
                                alertify.error('ERROR');
                            }
                        }
                    });
               }
               ,function() { 
                   alertify.error('Cancel');
               });

    });
    
    $('input.checkbox1').on('click', function() {
        var product = $('.checkbox1:checked').map(function() {return this.value;}).get().join(',');
        $('#product').tagsinput('removeAll');
        $('#product').tagsinput('add', product);
        
        console.log(product);
    });

    $('#confirmBtn').click(function() {
        var cost = parseInt($(this).val().split(',')[0]) * allArea.length * parseInt($(this).val().split(',')[1]);
        var text = '<h3><strong>จองพื้นที่ : ' + allArea.toString() + ' </strong></h3>\n\
                    <h4>รูปแบบการจอง : ' + $("#selectRent option:selected").text() + '<h4>\n\
                    <h4>รหัสรับบริการ : ${sessionScope.customer.getCust_id_str()} </h4>\n\
                    <h4>ชื่อผู้ใช้บริการ : ${sessionScope.customer.getFullname()} </h4>\n\
                    <h4>ผู้ใช้บริการประเภท : ${sessionScope.customer.getCust_type_Str()} </h4>\n\
                    <h4>ขายสินค้า : ${sessionScope.product.getProduct_name()} </h4>\n\
                    <h4>จำนวนเงินที่ต้องชำระ : ' + cost + ' บาท </h4>\n\
                    <label for="addCost">ค่าใช้จ่ายเพิ่มเติม</label> \n\
                    <input style="display: inline-block; width: 50%;" class="form-control" type="text" id="addCost" value="0"> <strong>บาท<strong> <br><br>\n\
                    <label for="note">รายละเอียดค่าใช้จ่ายเพิ่มเติม</label>\n\
                    <textarea class="form-control" rows="5" id="note"></textarea><br><br>';
        
        alertify.confirm('ยืนยันการทำรายการ', text,
            function(){
                $('.rented').attr("disabled", true);
                $('#confirmBtn').attr("disabled", true).html('<i id="spinBtn" class="fa fa-circle-o-notch fa-spin"></i> กำลังบันทึก');
                $('.btn-rent').attr("disabled", true);
                $.ajax({
                    type: "POST",
                    url: "${SITE_URL}/RentArea",
                    data: {'allArea': allArea.toString(), 'addCost': $('#addCost').val(), 'note': $('#note').val(), 'rentType': $('#selectRent').val()},
                    success: function(data) {
                        $('#confirmBtn').html('<i id="spinBtn"></i> จองพื้นที่สำเร็จ');
                        setTimeout(function() {
                            var total = cost + parseInt($('#addCost').val());
                            var text2 = '<h3><strong>จองพื้นที่ : ' + allArea.toString() + ' </strong></h3>\n\
                                         <h3>จำนวนเงินทั้งหมดที่ต้องชำระ : ' + total + ' บาท</h3>';
                            alertify.alert('จองพื้นที่สำเร็จ', text2, function(){
                                window.location = "${SITE_URL}/Authentication"; 
                            });      
                        }, 1000);
                    }
                });
            }, 
            function(){ 
                alertify.error('Cancel');
        });
    });

    $('#saveBtn').click(function() {
        var product = $('#product').val();
        var customer = $('#customer').val();
        var textDialog = '<h3>สินค้าที่เลือก : ' + product + ' </h3>\n\
                          <h3>รหัสรับบริการ : ' + customer + ' </h3>';
        alertify.confirm('ยืนยันการเลือกสินค้า', textDialog,
            function(){
                $('#saveBtn').attr("disabled", true).html('<i id="spinBtn" class="fa fa-circle-o-notch fa-spin"></i> กำลังบันทึก');
                $('#product').attr('readonly', true);
                $('#customer').attr('readonly', true);
                $.ajax({
                    type: "POST",
                    url: "${SITE_URL}/ProductServlet",
                    data: {'product': product, 'customer': customer},
                    success: function(data) {
                        if ($.trim(data) === 'NOT FOUND') {
                            alertify.error('รหัสรับบริการไม่ถูกต้อง กรุณากรอกรหัสรับบริการใหม่อีกครั้ง');
                            $('#saveBtn').attr("disabled", false).html('<i id="spinBtn"></i> บันทึก');
                            $('#product').attr('readonly', false);
                            $('#customer').attr('readonly', false);
                        } else {
                            setTimeout(function() {
                                alertify.success('คุณทำการเลือกสินค้า ' + product);
                                setTimeout(function() {
                                    window.location = "${SITE_URL}/index.jsp";
                                }, 2000);
                            }, 2000);
                        }
                    }
                });
                
            }, function(){ 
                alertify.error('คุณทำการยกเลิก');
        });
    });

    $('.btn-unselect').click(function() {
        selectArea($(this).text());
        
        if (allArea.length === 0) {
            $('#showArea').text('-');
            $('#confirmBtn').attr('disabled', true);
        } else {
            $('#showArea').text(allArea.toString());
            $('#confirmBtn').attr('disabled', false);
        }

    });

    function selectArea(text) {
        if ($.inArray(text, allArea) > -1) {
            var index = allArea.indexOf(text);
            allArea.splice(index, 1);
            
        } else {
            if (allArea.length < 3) {
                allArea.push(text);
            }
        }
        allArea.sort();
    }
    
    $('#clearBtn').click(function(){
        allArea = [];
        $('#showArea').text('-');
        $('#confirmBtn').attr('disabled', true);
    });

    $('.btn-product').click(function() {
        $('#product').val($(this).text());
    });
    
    var myVar=setInterval(function () {myTimer()}, 1000);
    function myTimer() {
        var current_local_time = new Date();
        document.getElementById("time_display").innerHTML = current_local_time.getDate() + "/" + (current_local_time.getMonth()+1) + "/" + current_local_time.getFullYear() + " " + current_local_time.getHours() + ":" + current_local_time.getMinutes() + ":" +current_local_time.getSeconds();
    }
</script>
    
<jsp:include page="template/footer.jsp" />