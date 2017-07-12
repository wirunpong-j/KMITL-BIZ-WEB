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
    <div></div>
        <div>
            <div class="container">
                <c:forEach var="order" items="${sessionScope.allOrder}">
                    <div class="row" style="padding:15px;">
                        <div class="col-md-9"><img class="img-responsive" src="assets/img/Caremal-Market.jpg"></div>
                        <div class="col-md-3" style="margin:auto 0;">
                            <form>
                                <div class="form-group">
                                    <button class="btn btn-info" type="button" style="width:100%;">${order.getZone_id()} </button>
                                    <h5 style="color:rgb(189,189,189);">เลขออเดอร์ที่ </h5>
                                    <h4>${order.getOrder_id_str()} </h4>
                                    <h5 style="color:rgb(189,189,189);">เวลาที่ทำรายการ </h5>
                                    <h4>${order.getOrder_date()} </h4>
                                    <h5 style="color:rgb(189,189,189);">ราคา </h5>
                                    <h4>${order.getPrice()} </h4>
                                    <h5 style="color:rgb(189,189,189);">รหัสรับบริการ </h5>
                                    <h4>${sessionScope.customer.getCust_id_str()} </h4>
                                    <h5 style="color:rgb(189,189,189);">ชื่อผู้ใช้บริการ </h5>
                                    <h4>${sessionScope.customer.getFullname()}</h4>
                                    <h5 style="color:rgb(189,189,189);">ประเภท </h5>
                                    <h4>${sessionScope.customer.getCust_type_Str()} </h4>
                                    <h5 style="color:rgb(189,189,189);">รหัสประจำตัว </h5>
                                    <h4>${sessionScope.customer.getStudent_id()} </h4>
                                    <h5 style="color:rgb(189,189,189);">เบอร์โทรศัพท์ </h5>
                                    <h4>${sessionScope.customer.getTel()} </h4>
                                    <h5 style="color:rgb(189,189,189);">สินค้า </h5>
                                    <h4>${sessionScope.product.getProduct_name()} </h4>
                                    <h5 style="color:rgb(189,189,189);">ทะเบียนรถ </h5>
                                    <h4>${sessionScope.customer.getVehicle()}</h4>
                                    <button class="btn btn-danger" type="button" style="width:100%;">กลับสู่หน้าหลัก </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </c:forEach>
                
            </div>
        </div>
    </body>
</html>
