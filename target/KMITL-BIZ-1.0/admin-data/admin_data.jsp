<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp" />

<div class="container">
    <div class="jumbotron">
        <h1>สรุปข้อมูลการใช้งาน</h1>
    </div>
    <div class="row">
        <a href="${SITE_URL}/ExportData/?action=1">
        <div class="col-md-4">
            <div class="admin_panel" id="admin_data_1">
                <i class="fa fa-users fa-5x" aria-hidden="true"></i>
                <h3>สรุปข้อมูลลูกค้า</h3>
            </div>
        </div>
        </a>
        <a href="${SITE_URL}/ExportData/?action=2">
        <div class="col-md-4">
            <div class="admin_panel" id="admin_data_2">
                <i class="fa fa-usd fa-5x" aria-hidden="true"></i>
                <h3>สรุปข้อมูลการชำระเงิน</h3>
            </div>
        </div>
        </a>
        <a href="${SITE_URL}/ExportData/?action=3">
        <div class="col-md-4">
            <div class="admin_panel" id="admin_data_3">
                <i class="fa fa-map-marker fa-5x" aria-hidden="true"></i>
                <h3>สรุปข้อมูลการจองพื้นที่ขาย</h3>
            </div>
        </div>
        </a>
    </div>
</div>


<jsp:include page="/template/footer.jsp" />