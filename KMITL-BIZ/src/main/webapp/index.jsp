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
</head>

<body>
    <div>
        <div class="container" style="margin:20px auto;">
            <div class="row" style="background-color:#eeeeee;padding:15px;">
                <div class="col-md-9">
                    <c:forEach var="pro" items="${sessionScope.allProduct}">
                        <button class="btn btn-default btn-product" type="button" style="margin:2px 0;">${pro.getProduct_name()}</button>
                    </c:forEach>
                        
                </div>
                <div class="col-md-3">
                    <form action="${SITE_URL}/ProductServlet" method="POST">
                        <div class="form-group">
                            <input class="form-control" type="text" name="product" id="product" placeholder="ชื่อสินค้า">
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="text" name="customer" placeholder="รหัสรับบริการ">
                        </div>
                        <button class="btn btn-primary" type="submit" style="width:100%;">บันทึก </button>
                    </form>
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
            <div class="row" style="padding:15px;">
                <div class="col-md-9"><img class="img-responsive" src="assets/img/Caremal-Market.jpg"></div>
                <div class="col-md-3" style="margin:auto 0;">
                    <c:choose>
                        <c:when test="${sessionScope.status == 'RENT'}">
                          <div class="form-group">
                            <button class="btn btn-primary btn-block" type="button" style="width:100%;">A23 </button>
                        </div>

                        <div class="form-group">
                            <button class="btn btn-success" type="button" style="width:100%;">ยืนยัน/พิมพ์ใบเสร็จ</button>
                        </div>

                        <div class="form-group">
                            <button class="btn btn-default" type="button" style="width:100%;">เรียกดูข้อมูล </button>
                        </div>
                        </c:when>
                        <c:otherwise>
                            <div class="form-group">
                                <button class="btn btn-primary btn-block" type="button" style="width:100%;" disabled>A23 </button>
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
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $('.btn-product').click(function() {
            $('#product').val($(this).text());
        });
    </script>
</body>

</html>