<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="SITE_URL" value="${pageContext.request.contextPath}" scope="request"/>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KMITL BIZ WAY Login</title>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${SITE_URL}/assets/css/style.css">
    
    <script src="https://code.jquery.com/jquery-3.2.1.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://use.fontawesome.com/211152dd25.js"></script>
</head>

<body id="login_body">
    <div id="login_panel">
        <div id="login_top_items">
            <h2>KMITL BIZ WAY</h2>
            <i class="fa fa-lock fa-5x" aria-hidden="true"></i>
        </div>
        <br>
        <form action="${SITE_URL}/Authentication" method="POST">
            <div class="form-group">
                <label for="username">ชื่อผู้ใช้งาน</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="ชื่อผู้ใช้งาน">
            </div>
            <div class="form-group">
                <label for="password">รหัสผ่าน</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="รหัสผ่าน">
            </div>
            <button type="submit" class="btn btn-primary">เข้าสู่ระบบ</button>
        </form>
        <a href="${SITE_URL}/Test" class="btn btn-primary">Test!!!!!</a>
    </div>
</body>

</html>