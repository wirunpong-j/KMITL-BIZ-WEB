<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="SITE_URL" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KMITL BIZ</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic">
    <link rel="stylesheet" href="${SITE_URL}/assets/css/style.css">

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
    
    <script src="https://use.fontawesome.com/211152dd25.js"></script>
    
</head>

<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">KMITL BIZ WAY</a>
            </div>
            <ul class="nav navbar-nav navbar-left">
                
                <c:set var="currentPath" scope="request" value="${fn:split(pageContext.request.servletPath, '/')}"></c:set>
                <c:choose>
                    <c:when test="${currentPath[0] == 'admin-customer'}">
                        <c:set var="active2" scope="request" value="active"></c:set>
                    </c:when>
                    <c:when test="${currentPath[0] == 'admin-staff'}">
                        <c:set var="active3" scope="request" value="active"></c:set>
                    </c:when>
                    <c:when test="${currentPath[0] == 'admin-data'}">
                        <c:set var="active4" scope="request" value="active"></c:set>
                    </c:when>
                    <c:otherwise>
                        <c:set var="active1" scope="request" value="active"></c:set>
                    </c:otherwise>
                </c:choose>
                
<!-------------------------------nav bar------------------------------->
                <li class="${active1}"><a href="${SITE_URL}/Authentication">หน้าหลัก</a></li>
                <c:choose>
                    <c:when test="${sessionScope.staff.getRole() == 'AD'}">
                        <li class="${active2}"><a href="${SITE_URL}/admin-customer/admin_cust.jsp">จัดการลูกค้า</a></li>
                        <li class="${active3}"><a href="${SITE_URL}/admin-staff/admin_staff.jsp">จัดการพนักงาน</a></li>
                        <li class="${active4}"><a href="${SITE_URL}/admin-data/admin_data.jsp">สรุปข้อมูลการใช้งาน</a></li>
                    </c:when>
                    <c:otherwise>
                        
                    </c:otherwise>
                </c:choose>
                
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="${SITE_URL}/LogoutServlet">ออกจากระบบ</a></li>
            </ul>
            
            <h3>${currentPath[0]}</h3>
        </div>
  </nav>