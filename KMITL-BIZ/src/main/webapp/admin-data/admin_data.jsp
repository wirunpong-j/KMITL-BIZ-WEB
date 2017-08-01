<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp" />

  <div class="container">
    <div class="jumbotron">
      <h1>สรุปข้อมูลการใช้งาน</h1>
    </div>
    <div class="row">
      <a href="${SITE_URL}/ExportData">
        <div class="col-md-12">
          <div class="admin_panel" id="admin_data_export">
            <i class="fa fa-user-plus fa-5x" aria-hidden="true"></i>
            <h3>ส่งออกข้อมูลเป็น Excel</h3>
          </div>
        </div>
      </a>
    </div>
  </div>


<jsp:include page="/template/footer.jsp" />