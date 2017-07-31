<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp" />

  <div class="container">
    <div class="jumbotron">
      <h1>จัดการพนักงาน</h1>
    </div>
    <div class="row">
      <a href="${SITE_URL}/admin/admin_staff_add.jsp">
        <div class="col-md-4">
          <div class="admin_panel" id="admin_staff_add">
            <i class="fa fa-user-plus fa-5x" aria-hidden="true"></i>
            <h3>เพิ่มพนักงานใหม่</h3>
          </div>
        </div>
      </a>
      <a href="${SITE_URL}/admin/admin_staff_edit.jsp">
        <div class="col-md-4">
          <div class="admin_panel" id="admin_staff_edit">
            <i class="fa fa-pencil fa-5x" aria-hidden="true"></i>
            <h3>ดู/แก้ไข ข้อมูลพนักงาน</h3>
          </div>
        </div>
      </a>
      <a href="${SITE_URL}/admin/admin_staff_list.jsp">  
        <div class="col-md-4">
          <div class="admin_panel" id="admin_staff_history">
            <i class="fa fa-file-text fa-5x" aria-hidden="true"></i>
            <h3>รายการพนักงาน</h3>
          </div>
        </div>
      </a>
    </div>
  </div>


<jsp:include page="/template/footer.jsp" />