<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="SITE_URL" value="${pageContext.request.contextPath}" scope="request"/>

<html style="height:100%;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KMITL BIZ Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="${SITE_URL}/assets/css/Login-Form-Clean.css">
    <link rel="stylesheet" href="${SITE_URL}/assets/css/styles.css">
</head>

<body style="background-position:center;background-size:cover;background-repeat:no-repeat;height:100%;background-color:#424242;">
    <div class="login-clean" style="background-color:rgba(241,247,252,0);padding:auto o;">
        <form action="${SITE_URL}/Authentication" method="POST">
            <h1 style="color:#d84315;">KMITL Market</h1>
            <div class="illustration"><i class="icon ion-locked" style="color:#d84315;"></i></div>
            <div class="form-group">
                <input class="form-control" type="text" name="username" required="" placeholder="ชื่อบัญชีผู้ใช้">
            </div>
            <div class="form-group">
                <input class="form-control" type="password" name="password" required="" placeholder="รหัสผ่าน">
            </div>
            <div class="form-group">
                <button class="btn btn-primary btn-block" type="submit" style="background-color:#d84315;">เข้าสู่ระบบ </button>
            </div>
        </form>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>