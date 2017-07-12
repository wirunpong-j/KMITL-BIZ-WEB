<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="SITE_URL" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KMITL BIZ</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/3.3.7/flatly/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic">
    <link rel="stylesheet" href="assets/css/styles.css">

    <!-- CSS -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/alertify.min.css"/>
    <!-- Default theme -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/themes/default.min.css"/>
    <!-- Semantic UI theme -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/themes/semantic.min.css"/>
    <!-- Bootstrap theme -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/themes/bootstrap.min.css"/>
    
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    
    <!-- JavaScript -->
    <script src="//cdn.jsdelivr.net/alertifyjs/1.10.0/alertify.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
</head>

<body>
    <div>
        <div class="container" style="margin:20px auto;">
            <div class="row" style="background-color:#eeeeee; padding:15px;">
                <div class="col-md-9" id="allProduct">
                    <c:choose>
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
                    </c:choose>    
                </div>
                
                <div class="col-md-3">
                    <c:choose>
                        <c:when test="${sessionScope.status == 'RENT'}">
                            <div class="form-group">
                                <input class="form-control" type="text" name="product" placeholder="ชื่อสินค้า" value="${sessionScope.product.getProduct_name()}" readonly>
                            </div>
                            <div class="form-group">
                                <input class="form-control" type="text" name="customer" placeholder="รหัสรับบริการ" value="${sessionScope.customer.getCust_id_str()}" readonly>
                            </div>
                            <button class="btn btn-primary" type="button" style="width:100%;" disabled>ทำการเลือกสินค้าแล้ว </button>
                        </c:when>
                        <c:otherwise>
                            <div class="form-group">
                                <input class="form-control" type="text" name="product" id="product" placeholder="ชื่อสินค้า">
                            </div>
                            <div class="form-group">
                                <input class="form-control" type="text" name="customer" id="customer" placeholder="รหัสรับบริการ">
                            </div>
                            <button class="btn btn-primary" type="button" id="saveBtn" style="width:100%;">บันทึก </button>
                        </c:otherwise>
                    </c:choose>
                </div>
                        
                <c:choose>
                    <c:when test="${sessionScope.status == 'RENT'}">
                      <h4>คุณ ${sessionScope.customer.getFullname()} ได้ทำการขายสินค้า ${sessionScope.product.getProduct_name()} </h4>
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>
                
            </div>
        </div>
    </div>
    <div>
        <div class="container">
            <div class="row" style="background-color:#eeeeee; padding:15px;">
                    <c:choose>
                        <c:when test="${sessionScope.status != 'RENT'}">
                            <div class="col-md-9"><img class="img-responsive" src="assets/img/Caremal-Market.jpg"></div>
                        </c:when>
                        <c:otherwise>
                        <div class="col-md-9">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <c:forEach var="i" begin="1" end="165">
                                                <th><button class="btn btn-danger btn-xs btn-area" type="button">X${i}</button></th>
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
                                                            <c:when test="${sessionScope.allZone[a].getOrder_id() == 0}">
                                                                <td><button class="btn btn-success btn-xs btn-area btn-unselect" id="${a}" name="${a}" type="button">${a}</button></td>
                                                            </c:when>
                                                            <c:when test="${sessionScope.allZone[a].getProduct_id() == sessionScope.customer.getProduct_id()}">
                                                                <td><button class="btn btn-warning btn-xs btn-area" type="button" disabled>${a}</button></td>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <td><button class="btn btn-danger btn-xs btn-area" type="button" disabled>${a}</button></td>
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
                    <c:choose>
                        <c:when test="${sessionScope.status == 'RENT'}">
                          <div class="form-group">
                            <button class="btn btn-primary btn-block" id="showArea" type="button" style="width:100%;">- </button>
                        </div>

                        <div class="form-group">
                            <button class="btn btn-success" type="button" id="confirmBtn" style="width:100%;">ยืนยัน/พิมพ์ใบเสร็จ</button>
                        </div>

                        <div class="form-group">
                            <button class="btn btn-default" type="button" style="width:100%;">เรียกดูข้อมูล </button>
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
                                <button class="btn btn-default" type="button" style="width:100%;" disabled>เรียกดูข้อมูล </button>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    
                    <div class="form-group"></div>
                    
                    <h5 class="text-center">พนักงาน: ${sessionScope.staff.getFirst_name()} ${sessionScope.staff.getLast_name()}</h5>
                    <h5 class="text-center">วันพฤหัสที่ 23 สิงหาคม 2560 เวลา 18:45</h5>
                    <form action="${SITE_URL}/LogoutServlet" method="POST">
                        <button class="btn btn-danger" type="submit" style="width:100%;">ออกจากระบบ </button>
                    </form>
                </div>
            </div>
        </div>
        <br><br>
    </div>
                        
    <script>
        var allArea = '';
        var count = 1;
        
        $('#confirmBtn').click(function() {
            alertify.confirm("Do you want to save?", function () {
                $.ajax({
                    type: "POST",
                    url: "${SITE_URL}/RentArea",
                    data: {'allArea': allArea},
                    success: function(data) {
                        $('#confirmBtn').attr("disabled", true).html('<i id="spinBtn" class="fa fa-circle-o-notch fa-spin"></i> กำลังบันทึก');
                        
                        setTimeout(function() {
                            alertify.success('คุณได้ทำการเลือกสินค้า ${sessionScope.product.getProduct_name()} และจองพื้นที่ตำแหน่ง ' + allArea);
                            setTimeout(function() {
                                window.location = "${SITE_URL}/information.jsp";
                            }, 2000);
                        }, 2000);
                    }
                });
            }, function() {
                alertify.error('คุณทำการยกเลิก');
            });
        });
        
        $('#saveBtn').click(function() {
            var product = $('#product').val();
            var customer = $('#customer').val();
            alertify.confirm("Do you want to save?", function () {
                $.ajax({
                    type: "POST",
                    url: "${SITE_URL}/ProductServlet",
                    data: {'product': product, 'customer': customer},
                    success: function(data) {
                        $('#saveBtn').attr("disabled", true).html('<i id="spinBtn" class="fa fa-circle-o-notch fa-spin"></i> กำลังบันทึก');
                        $('#product').attr('readonly', true);
                        $('#customer').attr('readonly', true);
                        
                        setTimeout(function() {
                            alertify.success('คุณทำการเลือกสินค้า ' + product);
                            setTimeout(function() {
                                window.location = "${SITE_URL}/index.jsp";
                            }, 2000);
                        }, 2000);
                    }
                });
                }, function() {
                    alertify.error('คุณทำการยกเลิก');
                });
        });
        
        $('.btn-unselect').click(function() {
            if (count > 3) {
                count = 1;
                allArea = '';
            }
            selectArea($(this).text());
            $('#showArea').text(allArea);
        });
        
        function selectArea(text) {
            switch (count) {
                case 1: allArea += text; break;
                default: allArea += ',' + text;
            }
            count++;
        }
        
        $('.btn-product').click(function() {
            $('#product').val($(this).text());
        });
    </script>
</body>

</html>